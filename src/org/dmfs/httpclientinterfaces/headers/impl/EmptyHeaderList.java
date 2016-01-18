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

package org.dmfs.httpclientinterfaces.headers.impl;

import java.util.Collections;
import java.util.Iterator;

import org.dmfs.httpclientinterfaces.headers.Header;
import org.dmfs.httpclientinterfaces.headers.HeaderList;
import org.dmfs.httpclientinterfaces.headers.HeaderType;


/**
 * A trivial {@link HeaderList} that doesn't contain any {@link Header}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class EmptyHeaderList implements HeaderList
{
	/**
	 * An instance of an {@link EmptyHeaderList}.
	 */
	public final static EmptyHeaderList INSTANCE = new EmptyHeaderList();


	@Override
	public Iterator<Header<?>> iterator()
	{
		return Collections.emptyIterator();
	}


	@Override
	public HeaderList append(Header<?>... newHeaders)
	{
		if (newHeaders.length == 0)
		{
			return this;
		}

		if (newHeaders.length == 1)
		{
			return new SingleHeaderList(newHeaders[0]);
		}

		return new ArrayHeaderList(newHeaders);
	}


	@Override
	public HeaderList append(HeaderList newHeaders)
	{
		return newHeaders;
	}


	@Override
	public HeaderList remove(HeaderType<?>... removedHeaderTypes)
	{
		return this;
	}


	@Override
	public boolean contains(HeaderType<?> headerType)
	{
		return false;
	}


	@Override
	public boolean contains(Header<?> header)
	{
		return false;
	}


	@Override
	public <T> Iterator<Header<T>> headersByType(HeaderType<T> headerType)
	{
		return Collections.emptyIterator();
	}


	@Override
	public int size()
	{
		return 0;
	}
}
