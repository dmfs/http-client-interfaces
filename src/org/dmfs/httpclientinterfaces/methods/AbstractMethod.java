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

package org.dmfs.httpclientinterfaces.methods;

import org.dmfs.httpclientinterfaces.HttpMethod;


/**
 * An abstract {@link HttpMethod}. By default this method is not safe and not idempotent.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public abstract class AbstractMethod implements HttpMethod
{
	/**
	 * The method verb.
	 */
	private final String mVerb;


	/**
	 * Instantiates a new {@link HttpMethod} with the given verb.
	 * 
	 * @param verb
	 *            The method verb.
	 */
	public AbstractMethod(final String verb)
	{
		if (verb == null)
		{
			throw new IllegalArgumentException("HTTP method verb must not be null");
		}
		mVerb = verb;
	}


	@Override
	public final String verb()
	{
		return mVerb;
	}


	@Override
	public boolean safe()
	{
		return false;
	}


	@Override
	public boolean idempotent()
	{
		return false;
	}


	@Override
	public int hashCode()
	{
		return mVerb.hashCode();
	}


	@Override
	public boolean equals(final Object obj)
	{
		if (!(obj instanceof HttpMethod))
		{
			return false;
		}
		HttpMethod other = (HttpMethod) obj;
		return obj == this || mVerb.equals(other.verb()); // two methods are considered to equal when their verbs equal
	}
}
