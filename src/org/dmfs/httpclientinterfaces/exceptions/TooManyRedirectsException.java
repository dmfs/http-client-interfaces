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

package org.dmfs.httpclientinterfaces.exceptions;

import java.net.URI;

import org.dmfs.httpclientinterfaces.HttpStatus;


/**
 * An {@link Exception} that's thrown when the client has been redirected too often.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class TooManyRedirectsException extends RedirectionException
{
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 0;

	private final int mCount;


	/**
	 * Create a new {@link TooManyRedirectsException} for the given status, source and destination.
	 * 
	 * @param statusCode
	 *            The status of the redirect.
	 * @param count
	 *            The number of redirects to this point.
	 * @param redirectingLocation
	 *            The source of the redirect.
	 * @param newLocation
	 *            The destination of the redirect.
	 */
	public TooManyRedirectsException(HttpStatus statusCode, int count, URI redirectingLocation, URI newLocation)
	{
		super(statusCode, redirectingLocation, newLocation);
		mCount = count;
	}


	/**
	 * Get the number of redirects that has led to this exception.
	 * 
	 * @return The number of redirects.
	 */
	public int count()
	{
		return mCount;
	}
}
