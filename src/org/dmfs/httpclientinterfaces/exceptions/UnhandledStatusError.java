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
import org.dmfs.httpclientinterfaces.IResponseHandler;


/**
 * An exception that's thrown when a reponse was not handled by an {@link IResponseHandler}.
 * <p>
 * There are three major subclasses: {@link RedirectionException} for <code>3xx</code> response status codes, {@link ClientError} for <code>4xx</code> response
 * status codes and {@link ServerError} for <code>5xx</code> response status codes, some of them having subclasses themselves. Implementations should always
 * pick the most appropriate Error class to allow for proper exception handling.
 * </p>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class UnhandledStatusError extends Exception
{
	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = 220357617564499189L;

	/**
	 * The status that was returned by the server.
	 */
	private final int mStatus;


	/**
	 * Create a new {@link UnhandledStatusError}.
	 * 
	 * @param status
	 *            The status code returned by the server.
	 */
	public UnhandledStatusError(int status)
	{
		mStatus = status;
	}


	/**
	 * Create a new {@link UnhandledStatusError} with a message.
	 * 
	 * @param status
	 *            The status code returned by the server.
	 * @param message
	 *            An error message.
	 */
	public UnhandledStatusError(int status, String message)
	{
		super(message);
		mStatus = status;
	}


	/**
	 * Returns the status code as returned by the server.
	 * 
	 * @return The status code.
	 */
	public int getStatus()
	{
		return mStatus;
	}


	/**
	 * Returns an {@link UnhandledStatusError} or any subclass that's appropriate for the given status code.
	 * 
	 * @param status
	 *            The status code of the response.
	 * @param message
	 *            An optional message;
	 * @return An {@link UnhandledStatusError} or a subclass.
	 * @throws UnhandledStatusError
	 */
	public final static UnhandledStatusError dispatch(int status, String message) throws UnhandledStatusError
	{
		if (status < 400)
		{
			throw new UnhandledStatusError(status, message);
		}

		if (status < 500)
		{
			if (status == HttpStatus.NOT_FOUND)
			{
				throw new NotFoundError(message);
			}

			throw new ClientError(status, message);
		}

		if (status < 600)
		{
			throw new ServerError(status, message);
		}

		throw new UnhandledStatusError(status, message);
	}
}
