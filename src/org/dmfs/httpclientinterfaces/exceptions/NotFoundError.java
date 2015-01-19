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

import org.dmfs.httpclientinterfaces.HttpStatus;


/**
 * This Exception is thrown when an unhandled {@link HttpStatus#NOT_FOUND} status code occurs.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.4">RFC 7231, section 6.5.4</a>
 */
public class NotFoundError extends ClientError
{

	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = -820625709820025814L;


	/**
	 * Create a new {@link NotFoundError}.
	 */
	public NotFoundError()
	{
		super(HttpStatus.NOT_FOUND);
	}


	/**
	 * Create a new {@link NotFoundError} with a message.
	 * 
	 * @param message
	 *            An error message.
	 */
	public NotFoundError(String message)
	{
		super(HttpStatus.NOT_FOUND, message);
	}
}
