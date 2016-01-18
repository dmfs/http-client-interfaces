/*
 * Copyright (C) 2016 Marten Gajda <marten@dmfs.org>
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.httpclientinterfaces.requestutils;

import java.io.IOException;

import org.dmfs.httpclientinterfaces.HttpResponse;
import org.dmfs.httpclientinterfaces.HttpResponseHandler;
import org.dmfs.httpclientinterfaces.HttpStatus;
import org.dmfs.httpclientinterfaces.exceptions.ClientErrorException;
import org.dmfs.httpclientinterfaces.exceptions.NotFoundException;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolError;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;
import org.dmfs.httpclientinterfaces.exceptions.ServerErrorException;
import org.dmfs.httpclientinterfaces.exceptions.UnauthorizedException;
import org.dmfs.httpclientinterfaces.exceptions.UnexpectedResponseException;
import org.dmfs.httpclientinterfaces.utils.ConsumingInputStream;


/**
 * An default response handler that doesn't handle the response but throws the most appropriate Exception, based on the response status code. Use
 * {@link #getInstance()} to get an instance.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <T>
 *            The type of the expected response.
 */
public final class DefaultResponseHandler<T> implements HttpResponseHandler<T>
{

	private final static HttpResponseHandler<?> INSTANCE = new DefaultResponseHandler<Object>();


	@SuppressWarnings("unchecked")
	public static <V> HttpResponseHandler<V> getInstance()
	{
		// this HttpResponseHandler will always throw an Exception, so the actual response type doesn't matter at all.
		return (HttpResponseHandler<V>) INSTANCE;
	}


	@Override
	public T handleResponse(HttpResponse response) throws IOException, ProtocolError, ProtocolException
	{
		try
		{
			HttpStatus status = response.status();
			if (status.isClientError())
			{
				if (HttpStatus.NOT_FOUND.equals(status))
				{
					throw new NotFoundException(response.responseUri(), String.format("Resource at '%s' not found.", response.responseUri().toASCIIString()));
				}

				if (HttpStatus.UNAUTHORIZED.equals(status))
				{
					throw new UnauthorizedException(String.format("Authentication at '%s' failed.", response.responseUri().toASCIIString()));
				}

				throw new ClientErrorException(status, String.format("'%s' returned a client error: '%d %s'", status.statusCode(), status.reason(), response
					.responseUri().toASCIIString()));
			}

			if (status.isServerError())
			{
				throw new ServerErrorException(status, String.format("'%s' returned a server error: '%d %s'", status.statusCode(), status.reason(), response
					.responseUri().toASCIIString()));
			}

			throw new UnexpectedResponseException(status, String.format("Unexpected status code '%d %s' returned from '%s'", status.statusCode(),
				status.reason(), response.responseUri().toASCIIString()));
		}
		finally
		{
			new ConsumingInputStream(response.responseEntity().contentStream()).close();
		}
	}
}
