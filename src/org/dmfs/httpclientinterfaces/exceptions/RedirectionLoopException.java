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

import java.net.URI;

import org.dmfs.httpclientinterfaces.HttpStatus;


/**
 * An {@link Exception} that's thrown when a redirection loop has been detected.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class RedirectionLoopException extends RedirectionException
{
	/**
	 * Generated serial UID.
	 */
	private static final long serialVersionUID = -4178950794884142208L;


	/**
	 * Create a new redirection loop exception. for the given status, source and destination.
	 * 
	 * @param statusCode
	 *            The status of the redirect.
	 * @param redirectingLocation
	 *            The source of the redirect.
	 * @param newLocation
	 *            The destination of the redirect.
	 */
	public RedirectionLoopException(HttpStatus statusCode, URI redirectingLocation, URI newLocation)
	{
		super(statusCode, redirectingLocation, newLocation);
	}
}
