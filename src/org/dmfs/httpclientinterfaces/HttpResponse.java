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

package org.dmfs.httpclientinterfaces;

import java.net.URI;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.dmfs.httpclientinterfaces.headers.Header;
import org.dmfs.httpclientinterfaces.headers.HeaderType;


/**
 * An interface of an HTTP response object.
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
	 * Checks whether the response contains at least one header of the given {@link HeaderType}.
	 * 
	 * @param headerType
	 *            The {@link HeaderType} of the header to check.
	 * @return <code>true</code> if there is at least one such header, <code>false</code> otherwise.
	 */
	public <T> boolean hasHeader(HeaderType<T> headerType);


	/**
	 * Returns the first {@link Header} of the given {@link HeaderType}. Use {@link #hasHeader(HeaderType)} before you call this to make sure such a
	 * {@link Header} actually exists.
	 * 
	 * @param headerType
	 *            The {@link HeaderType} of the {@link Header} to return.
	 * @return The first {@link Header} of the given type.
	 * 
	 * @throws NoSuchElementException
	 *             if no such {@link Header} exists.
	 */
	public <T> Header<T> firstHeader(HeaderType<T> headerType) throws NoSuchElementException;


	/**
	 * Returns an {@link Iterator} that iterates all {@link Header}s of the given {@link HeaderType}.
	 * 
	 * @param headerType
	 *            The {@link HeaderType} of the {@link Header}s to return.
	 * @return An {@link Iterator} of {@link Header}s, may be empty (not <code>null</code>).
	 */
	public <T> Iterator<Header<T>> headers(HeaderType<T> headerType);


	/**
	 * Returns an {@link HttpResponseEntity} representing the data in the response.
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
