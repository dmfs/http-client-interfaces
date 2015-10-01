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

import org.dmfs.httpclientinterfaces.exceptions.RedirectionException;


/**
 * The interface of a redirection handler that knows how to handle a redirection response.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface OnRedirectCallback
{
	/**
	 * This is called when a server has sent some sort of redirect. This method determines whether to follow the redirect or not. In the later case a
	 * {@link RedirectionException} is thrown by the {@link HttpRequestExecutor}.
	 * 
	 * @param status
	 *            One of {@link HttpStatus#MOVED_PERMANENTLY}, {@link HttpStatus#FOUND}, {@link HttpStatus#SEE_OTHER}, {@link HttpStatus#TEMPORARY_REDIRECT} or
	 *            {@link HttpStatus#PERMANENT_REDIRECT}.
	 * @param redirectingLocation
	 *            The absolute {@link URI} of the resource that returned the redirect.
	 * @param newLocation
	 *            The absolute {@link URI} that was returned by the server as the new location.
	 * @return <code>true</code> to follow that redirect and resent the request to the new location, <code>false</code> to not follow it.
	 */
	public boolean onRedirect(HttpStatus status, URI redirectingLocation, URI newLocation);
}
