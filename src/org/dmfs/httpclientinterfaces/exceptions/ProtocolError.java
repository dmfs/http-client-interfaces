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
 * An {@link Exception} that's thrown when an error at application level occurred while handling a response. This is to be used for errors that are valid
 * responses within the scope of the application protocol.
 * <p>
 * An example of a ProtocolError is when you try to upload a file but the server refuses to store it for a specific reason.
 * </p>
 * This is different from a {@link ProtocolException} which is thrown when the response does not conform to the application protocol.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @see ProtocolException
 */
public class ProtocolError extends Exception
{
	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = 7175066856358461915L;


	/**
	 * Create a new {@link ProtocolError} with a message.
	 * 
	 * @param message
	 *            An error message.
	 */
	public ProtocolError(String message)
	{
		super(message);
	}


	/**
	 * Create a new {@link ProtocolError} with a message and a cause.
	 * 
	 * @param message
	 *            An error message.
	 * @param cause
	 *            The reaons for this error.
	 */
	public ProtocolError(String message, Throwable cause)
	{
		super(message, cause);
	}
}
