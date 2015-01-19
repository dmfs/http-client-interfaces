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


/**
 * A simple {@link IRedirectionCallback} implementation that doesn't follow any redirects. To get an instance use {@link #getInstance()}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class NeverFollowRedirectionCallback implements IRedirectionCallback
{
	private final static class SingletonHolder
	{
		/**
		 * A static instance of the {@link NeverFollowRedirectionCallback}. It will be initialized when {@link NeverFollowRedirectionCallback#getInstance()} is
		 * called the first time.
		 */
		final static NeverFollowRedirectionCallback INSTANCE = new NeverFollowRedirectionCallback();
	}


	/**
	 * Returns an instance of {@link NeverFollowRedirectionCallback}.
	 * 
	 * @return
	 */
	public static NeverFollowRedirectionCallback getInstance()
	{
		return SingletonHolder.INSTANCE;
	}


	@Override
	public boolean onRedirect(int statusCode, URI redirectingLocation, URI newLocation)
	{
		return false;
	}
}
