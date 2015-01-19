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

package org.dmfs.httpclientinterfaces;

import java.io.IOException;

import org.dmfs.httpclientinterfaces.exceptions.ProtocolError;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;
import org.dmfs.httpclientinterfaces.exceptions.UnhandledStatusError;


/**
 * Defines a simple interface of an HTTP request. The request is typed to the class of the expected response.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <T>
 *            The type of the response.
 */
public interface IHttpRequest<T>
{

	/**
	 * Returns the HTTP method of this request.
	 * 
	 * @return The {@link HttpMethod} this request uses.
	 */
	public HttpMethod getMethod();


	/**
	 * Modifies the headers in the request. This method sets all the headers this request needs to be executed successfully.
	 * 
	 * @param headerEditor
	 *            An {@link IHeaderEditor} to modify the headers of the request.
	 */
	public void updateHeaders(IHeaderEditor headerEditor);


	/**
	 * Returns an {@link IHttpRequestEntity} that contains the body of this request. The result may be <code>null</code> if this request doesn't have any body
	 * (like in case of a {@link HttpMethod#GET} or an {@link HttpMethod#OPTIONS} request).
	 * 
	 * @return An {@link IHttpRequestEntity} object or <code>null</code> if this request doesn't have any body.
	 */
	public IHttpRequestEntity getRequestEntity();


	/**
	 * Returns a handler for the response. The implementation can return <code>null</code> to indicate that it can not handle that response. In that case the
	 * {@link IHttpRequestExecutor} must handle the response by throwing an {@link UnhandledStatusError} or any subclass of it.
	 * 
	 * @param response
	 *            The {@link IHttpResponse} that needs to be handled.
	 * @return An {@link IResponseHandler} or <code>null</code> to not handle the response.
	 * @throws IOException
	 * @throws ProtocolError
	 *             If the response is an error response as specified by the application protocol.
	 * @throws ProtocolException
	 *             If the response is invalid or malformed and can not be handled properly.
	 */
	public IResponseHandler<T> getResponseHandler(IHttpResponse response) throws IOException, ProtocolError, ProtocolException;
}
