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
 * A trivial {@link HeaderList} that contains a single {@link Header} only.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class SingleHeaderList implements HeaderList
{
	private final Header<?> mSoleHeader;


	/**
	 * Creates a {@link HeaderList} containing a single {@link Header} only.
	 * 
	 * @param header
	 */
	public SingleHeaderList(Header<?> header)
	{
		mSoleHeader = header;
	}


	@Override
	public Iterator<Header<?>> iterator()
	{
		return new HeaderIterator(mSoleHeader);
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
			return new ArrayHeaderList(false /* no need to clone the parameter array */, mSoleHeader, newHeaders[0]);
		}

		return append(new ArrayHeaderList(newHeaders));
	}


	@Override
	public HeaderList append(HeaderList newHeaders)
	{
		return newHeaders.size() == 0 ? this : new JoinedHeaderList(this, newHeaders);
	}


	@Override
	public HeaderList remove(HeaderType<?>... removedHeaderTypes)
	{
		for (HeaderType<?> headerType : removedHeaderTypes)
		{
			if (mSoleHeader.headerType().equals(headerType))
			{
				return EmptyHeaderList.INSTANCE;
			}
		}
		return this;
	}


	@Override
	public boolean contains(HeaderType<?> headerType)
	{
		return mSoleHeader.headerType().equals(headerType);
	}


	@Override
	public boolean contains(Header<?> header)
	{
		return mSoleHeader.equals(header);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> Iterator<Header<T>> headersByType(HeaderType<T> headerType)
	{
		return (Iterator<Header<T>>) (mSoleHeader.headerType().equals(headerType) ? iterator() : Collections.emptyIterator());
	}


	@Override
	public int size()
	{
		return 1;
	}

	/**
	 * An {@link Iterator} that iterates a single header only.
	 */
	private final static class HeaderIterator implements Iterator<Header<?>>
	{
		private Header<?> mHeader;
		private boolean mHasNext;


		public HeaderIterator(Header<?> header)
		{
			mHeader = header;
			mHasNext = true;
		}


		@Override
		public boolean hasNext()
		{
			return mHasNext;
		}


		@Override
		public Header<?> next()
		{
			if (!hasNext())
			{
				throw new ArrayIndexOutOfBoundsException("Sole Header already iterated");
			}
			mHasNext = false;
			return mHeader;
		}


		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("remove() not is supported by this iterator.");
		}
	}

}
