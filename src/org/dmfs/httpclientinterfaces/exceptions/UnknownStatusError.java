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
 * An exception that's thrown when an unknown status code has been parsed.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class UnknownStatusError extends IllegalArgumentException
{
	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = -6837115661126045047L;

	/**
	 * The status that was returned by the server.
	 */
	private final int mStatus;


	/**
	 * Create a new {@link UnknownStatusError}.
	 * 
	 * @param status
	 *            The status returned by the server.
	 */
	public UnknownStatusError(int status)
	{
		this(status, null);
	}


	/**
	 * Create a new {@link UnknownStatusError} with a message.
	 * 
	 * @param status
	 *            The status returned by the server.
	 * @param message
	 *            An error message.
	 */
	public UnknownStatusError(int status, String message)
	{
		super(message);
		mStatus = status;
	}


	/**
	 * Returns the unknown status code as returned by the server.
	 * 
	 * @return The status code.
	 */
	public int getStatus()
	{
		return mStatus;
	}
}
