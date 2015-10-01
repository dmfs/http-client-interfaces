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

import java.net.URI;


/**
 * An interface of an HTTP response object.
 * 
 * <p />
 * Find a better way to handle/return/iterate headers.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface HttpResponse
{
	/**
	 * Returns the status of the response.
	 * 
	 * @see HttpStatus
	 * @return The status.
	 */
	public HttpStatus status();


	/**
	 * Returns the number of headers of the given name.
	 * 
	 * @param header
	 *            The name of the header.
	 * @return The number of occurrences of the given header.
	 */
	public int headerCount(String header);


	/**
	 * Returns the first header of the given name.
	 * 
	 * @param header
	 *            The name of the header.
	 * @return The value of the header or <code>null</code> if no header with that name exists.
	 */
	public String header(String header);


	/**
	 * Returns a header identified by its name and position, i.e. it returns the n-th header of that name.
	 * 
	 * @param header
	 *            The name of the header.
	 * @param n
	 *            The position of the header to return.
	 * @return The value of the header.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if n < 0 or n >= {@link #getHeaderCount(String)} for the same header.
	 */
	public String header(String header, int n);


	/**
	 * Returns an {@link HttpResponseEntity} representing the data in the response. This is guaranteed to be non-<code>null</code>.
	 * 
	 * @return An {@link HttpResponseEntity}.
	 */
	public HttpResponseEntity responseEntity();


	/**
	 * Returns the {@link URI} the request was originally sent to.
	 * 
	 * @return The URI of the request.
	 * 
	 * @see #responseUri()
	 */
	public URI requestUri();


	/**
	 * Returns the {@link URI} of the server that handled the instance. If no redirects have been followed this equals the URI passed to the execute methods of
	 * {@link HttpRequestExecutor} otherwise it's the URI of the last location that didn't return a redirect.
	 * 
	 * @return The URI of the responding instance.
	 * 
	 * @see #requestUri()
	 */
	public URI responseUri();
}
