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

/**
 * Callback for asynchronous response handling. This will be called by {@link HttpRequestExecutor#execute(java.net.URI, IHttpRequest, IResponseCallback)} or
 * {@link HttpRequestExecutor#execute(java.net.URI, IHttpRequest, IResponseCallback, IRedirectionCallback)} when the response has been handled or an error
 * occurred.
 * <p>
 * Note that exactly one of {@link #onResponse(Object)} and {@link #onError(Exception)} gets called per request, never both.
 * </p>
 * <p>
 * Also note that this may be called in a background thread, depending on the actual implementation of the {@link HttpRequestExecutor}.
 * </p>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <T>
 *            The type of the expected response value.
 */
public interface OnResponseCallback<T>
{

	/**
	 * Called when the response object has been parsed and handled successfully. The parameter passed to this method is the result of
	 * {@link ResponseHandler#handleResponse(IHttpResponse)}.
	 * 
	 * @param response
	 *            The response object as returned by {@link ResponseHandler#handleResponse(IHttpResponse)}.
	 */
	public void onResponse(T response);


	/**
	 * Called if any error occurred during the execution of the request.
	 * 
	 * @param e
	 *            The {@link Exception} that was thrown.
	 */
	public void onError(Exception e);
}
