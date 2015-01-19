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
 * Represents an HTTP method. This class provides static members for HTTP methods defined in <a href="https://tools.ietf.org/html/rfc7231#section-4.3">RFC 7231,
 * section 4.3</a>.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class HttpMethod
{
	/**
	 * HTTP Method GET
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.1">RFC 7231, section 4.3.1</a>
	 */
	public final static HttpMethod GET = safeMethod("GET");

	/**
	 * HTTP Method HEAD
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.2">RFC 7231, section 4.3.2</a>
	 */
	public final static HttpMethod HEAD = safeMethod("HEAD");

	/**
	 * HTTP Method POST
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.3">RFC 7231, section 4.3.3</a>
	 */
	public final static HttpMethod POST = method("POST");

	/**
	 * HTTP Method PUT
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.4">RFC 7231, section 4.3.4</a>
	 */
	public final static HttpMethod PUT = idempotentMethod("PUT");

	/**
	 * HTTP Method DELETE
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.5">RFC 7231, section 4.3.5</a>
	 */
	public final static HttpMethod DELETE = idempotentMethod("DELETE");

	/**
	 * HTTP Method CONNECT
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.6">RFC 7231, section 4.3.6</a>
	 */
	public final static HttpMethod CONNECT = method("CONNECT");

	/**
	 * HTTP Method OPTIONS
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.7">RFC 7231, section 4.3.7</a>
	 */
	public final static HttpMethod OPTIONS = safeMethod("OPTIONS");

	/**
	 * HTTP Method TRACE
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.3.8">RFC 7231, section 4.3.8</a>
	 */
	public final static HttpMethod TRACE = safeMethod("TRACE");


	/**
	 * Creates a non-safe and non-idempotent {@link HttpMethod} for the given verb.
	 * 
	 * @param verb
	 *            The verb of the method.
	 * @return An {@link HttpMethod}.
	 */
	public final static HttpMethod method(String verb)
	{
		return Method(verb, false, false);
	}


	/**
	 * Creates an {@link HttpMethod} that's idempotent but not safe.
	 * 
	 * @param verb
	 *            The verb of the method.
	 * @return An {@link HttpMethod}.
	 */
	public final static HttpMethod idempotentMethod(String verb)
	{
		return Method(verb, false, true);
	}


	/**
	 * Creates a safe and idempotent {@link HttpMethod}.
	 * 
	 * @param verb
	 *            The verb of the method.
	 * @return An {@link HttpMethod}.
	 */
	public final static HttpMethod safeMethod(String verb)
	{
		return Method(verb, true, true);
	}


	/**
	 * Creates an {@link HttpMethod}.
	 * 
	 * @param verb
	 *            The verb of the method.
	 * @param safe
	 *            <code>true</code> if the method is safe, <code>false</code> otherwise.
	 * @param idempotent
	 *            <code>true</code> if the method is idempotent, <code>false</code> otherwise.
	 * @return An {@link HttpMethod}.
	 */
	public final static HttpMethod Method(String verb, boolean safe, boolean idempotent)
	{
		return new HttpMethod(verb, safe, idempotent);
	}

	/**
	 * The HTTP verb of this method.
	 */
	public final String verb;

	/**
	 * Indicates that a request method is safe, which means that the request is not intended and not expected to change any state on the server. In effect the
	 * semantics are to be considered <em>read-only</em>.
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.2.1">RFC 7231, Section 4.2.1</a>
	 */
	public final boolean safe;

	/**
	 * Indicates that a request method is idempotent, which means that sending multiple identical requests with that method has the same effect as sending one
	 * single request to the server.
	 * 
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.2.2">RFC 7231, Section 4.2.2</a>
	 */
	public final boolean idempotent;


	/**
	 * Constructor for an {@link HttpMethod}.
	 * 
	 * @param verb
	 *            The verb of the method.
	 * @param safe
	 *            <code>true</code> if the method is safe, <code>false</code> otherwise.
	 * @param idempotent
	 *            <code>true</code> if the method is idempotent, <code>false</code> otherwise.
	 */
	private HttpMethod(String verb, boolean safe, boolean idempotent)
	{
		this.verb = verb;
		this.safe = safe;
		this.idempotent = idempotent;
	}
}
