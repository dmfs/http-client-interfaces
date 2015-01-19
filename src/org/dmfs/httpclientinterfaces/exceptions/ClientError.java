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

package org.dmfs.httpclientinterfaces.exceptions;

/**
 * This is a special {@link UnhandledStatusError} for <code>4xx</code> status codes (i.e. client errors).
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5">RFC 7231, section 6.5</a>
 */
public class ClientError extends UnhandledStatusError
{
	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = 3375512006017392390L;


	/**
	 * Create a new {@link ClientError}.
	 * 
	 * @param status
	 *            The status code returned by the server.
	 */
	public ClientError(int status)
	{
		super(status);
	}


	/**
	 * Create a new {@link ClientError} with a message.
	 * 
	 * @param status
	 *            The status code returned by the server.
	 * @param message
	 *            An error message.
	 */
	public ClientError(int status, String message)
	{
		super(status, message);
	}
}
