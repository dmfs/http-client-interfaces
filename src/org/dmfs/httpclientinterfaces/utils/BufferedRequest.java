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

import org.dmfs.httpclientinterfaces.HttpMethod;
import org.dmfs.httpclientinterfaces.IHeaderEditor;
import org.dmfs.httpclientinterfaces.IHttpRequest;
import org.dmfs.httpclientinterfaces.IHttpRequestEntity;
import org.dmfs.httpclientinterfaces.IHttpResponse;
import org.dmfs.httpclientinterfaces.IResponseHandler;
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
public class BufferedRequest<T> implements IHttpRequest<T>
{
	/**
	 * The wrapped request.
	 */
	private IHttpRequest<T> mRequest;

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
	public BufferedRequest(IHttpRequest<T> request)
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
	public BufferedRequest(IHttpRequest<T> request, int maxBufferSize)
	{
		mRequest = request;
		mEntity = new BufferedRequestEntity(mRequest.getRequestEntity(), maxBufferSize);
	}


	@Override
	public HttpMethod getMethod()
	{
		return mRequest.getMethod();
	}


	@Override
	public void updateHeaders(IHeaderEditor headerEditor)
	{
		mRequest.updateHeaders(headerEditor);
	}


	@Override
	public IHttpRequestEntity getRequestEntity()
	{
		return mEntity;
	}


	@Override
	public IResponseHandler<T> getResponseHandler(IHttpResponse response) throws IOException, ProtocolError, ProtocolException
	{
		return mRequest.getResponseHandler(response);
	}
}
