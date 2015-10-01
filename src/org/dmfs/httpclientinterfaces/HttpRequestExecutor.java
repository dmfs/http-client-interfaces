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
import java.net.URI;

import org.dmfs.httpclientinterfaces.exceptions.ProtocolError;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;
import org.dmfs.httpclientinterfaces.exceptions.RedirectionException;
import org.dmfs.httpclientinterfaces.exceptions.UnhandledStatusError;


/**
 * The interface of an instances that knows how to execute an {@link HttpRequest}.
 * <p />
 * It's worth noting, that the request {@link URI} is never determined by the request. All execute methods take a dedicated {@link URI} parameter. It's easy to
 * extend {@link HttpRequest} and write a wrapper around {@link HttpRequestExecutor} to let the request return the URI. However, that's left to the implementer.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface HttpRequestExecutor
{
	/**
	 * Sends the given {@link HttpRequest} to the given {@link URI} and returns the result. This method uses the default redirection handling policy as
	 * configured in the executer instance.
	 * 
	 * @param uri
	 *            The URI to send this request to.
	 * @param request
	 *            The request to execute.
	 * @return The result, i.e. the handled server response.
	 */
	public <T> T execute(URI uri, HttpRequest<T> request) throws IOException, ProtocolError, ProtocolException, RedirectionException, UnhandledStatusError;


	/**
	 * Sends the given {@link HttpRequest} to the given {@link URI} and returns the result. This method uses a custom {@link OnRedirectCallback} to handle any
	 * redirections.
	 * 
	 * @param uri
	 *            The URI to send this request to.
	 * @param request
	 *            The request to execute.
	 * @param redirectionCallback
	 *            An {@link OnRedirectCallback} that determines how to handle any redirections.
	 * @return The result, i.e. the handled server response.
	 */
	public <T> T execute(URI uri, HttpRequest<T> request, OnRedirectCallback redirectionCallback) throws IOException, ProtocolError, ProtocolException,
		RedirectionException, UnhandledStatusError;


	/**
	 * Sends the given {@link HttpRequest} to the given {@link URI} and calls an {@link OnResponseCallback} with the result. This method uses the default
	 * redirection handling policy as configured in the executer instance. This method may be executed asynchronously, so the caller must be aware that the
	 * result may not be available yet, when the method returns.
	 * 
	 * @param uri
	 *            The URI to send this request to.
	 * @param request
	 *            The request to execute.
	 * @param callback
	 *            An {@link OnResponseCallback} to call with the result of the request.
	 */
	public <T> void execute(URI uri, HttpRequest<T> request, OnResponseCallback<T> callback);


	/**
	 * Sends the given {@link HttpRequest} to the given {@link URI} and calls an {@link OnResponseCallback} with the result. This method uses a custom
	 * {@link OnRedirectCallback} to handle any redirections. This method may be executed asynchronously, so the caller must be aware that the result may not
	 * be available yet, when the method returns.
	 * 
	 * @param uri
	 *            The URI to send this request to.
	 * @param request
	 *            The request to execute.
	 * @param callback
	 *            An {@link OnResponseCallback} to call with the result of the request.
	 * @param redirectionCallback
	 *            An {@link OnRedirectCallback} that determines how to handle any redirections.
	 */
	public <T> void execute(URI uri, HttpRequest<T> request, OnResponseCallback<T> callback, OnRedirectCallback redirectionCallback);
}
