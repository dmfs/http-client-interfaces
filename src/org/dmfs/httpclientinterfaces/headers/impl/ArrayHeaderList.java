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

import java.util.Iterator;

import org.dmfs.httpclientinterfaces.headers.Header;
import org.dmfs.httpclientinterfaces.headers.HeaderList;
import org.dmfs.httpclientinterfaces.headers.HeaderType;


/**
 * A {@link HeaderList} that contains {@link Header}s provided in an array.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class ArrayHeaderList extends AbstractComplexHeaderList
{

	private final Header<?>[] mHeaders;


	/**
	 * Creates a new {@link HeaderList} that contains the provided Headers. The array must not contain <code>null</code> values.
	 * 
	 * @param headers
	 *            The headers in this {@link HeaderList}.
	 */
	public ArrayHeaderList(Header<?>... headers)
	{
		this(true, headers);
	}


	/**
	 * Constructor for internal purposes that has an option to not clone the provided {@link Header} array. This is package private, because we can't trust
	 * external packages to not abuse it.
	 * 
	 * @param cloneList
	 *            <code>true</code> to clone the {@link Header} array, false to skip cloning.
	 * @param headers
	 *            The headers in this {@link HeaderList}.
	 */
	ArrayHeaderList(boolean cloneList, Header<?>... headers)
	{
		mHeaders = cloneList ? headers.clone() : headers;
	}


	@Override
	public Iterator<Header<?>> iterator()
	{
		return new Iterator<Header<?>>()
		{
			private int next = 0;


			@Override
			public boolean hasNext()
			{
				return next < mHeaders.length;
			}


			@Override
			public Header<?> next()
			{
				return mHeaders[next++];
			}


			@Override
			public void remove()
			{
				throw new UnsupportedOperationException("remove() not is supported by this iterator.");
			}
		};
	}


	@Override
	public boolean contains(HeaderType<?> headerType)
	{
		for (Header<?> header : mHeaders)
		{
			if (header.headerType().equals(headerType))
			{
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean contains(Header<?> header)
	{
		for (Header<?> h : mHeaders)
		{
			if (h.equals(header))
			{
				return true;
			}
		}
		return false;
	}


	@Override
	public int size()
	{
		return mHeaders.length;
	}
}
