/*
 * Copyright (C) 2015 Marten Gajda <marten@dmfs.org>
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

package org.dmfs.httpclientinterfaces.utils;

import java.io.IOException;

import org.dmfs.httpclientinterfaces.HeaderEditor;
import org.dmfs.httpclientinterfaces.HttpMethod;
import org.dmfs.httpclientinterfaces.HttpRequest;
import org.dmfs.httpclientinterfaces.HttpRequestEntity;
import org.dmfs.httpclientinterfaces.HttpResponse;
import org.dmfs.httpclientinterfaces.ResponseHandler;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolError;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;


/**
 * A buffered request wrapper. You can use this to wrap request with entities that are generated on demand to avoid chunked encoding, which is not well
 * supported by many servers.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <T>
 *            The generic type of the wrapped request.
 */
public final class BufferedRequest<T> implements HttpRequest<T>
{
	/**
	 * The wrapped request.
	 */
	private HttpRequest<T> mRequest;

	/**
	 * The buffered request entity.
	 */
	private final BufferedRequestEntity mEntity;


	/**
	 * Creates a {@link BufferedRequest} wrapper for the given request with an unlimited buffer size.
	 * 
	 * @param request
	 *            The request to buffer.
	 */
	public BufferedRequest(HttpRequest<T> request)
	{
		this(request, Integer.MAX_VALUE);
	}


	/**
	 * Creates a {@link BufferedRequest} wrapper for the given request using the given buffer size.
	 * 
	 * @param request
	 *            The request to buffer.
	 * @param maxBufferSize
	 *            The maximum buffer size.
	 */
	public BufferedRequest(HttpRequest<T> request, int maxBufferSize)
	{
		mRequest = request;
		mEntity = new BufferedRequestEntity(mRequest.requestEntity(), maxBufferSize);
	}


	@Override
	public HttpMethod method()
	{
		return mRequest.method();
	}


	@Override
	public void updateHeaders(HeaderEditor headerEditor)
	{
		mRequest.updateHeaders(headerEditor);
	}


	@Override
	public HttpRequestEntity requestEntity()
	{
		return mEntity;
	}


	@Override
	public ResponseHandler<T> responseHandler(HttpResponse response) throws IOException, ProtocolError, ProtocolException
	{
		return mRequest.responseHandler(response);
	}
}
