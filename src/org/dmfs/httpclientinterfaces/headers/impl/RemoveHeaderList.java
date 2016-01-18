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
import org.dmfs.httpclientinterfaces.utils.FilteringIterator;
import org.dmfs.httpclientinterfaces.utils.FilteringIterator.IteratorFilter;


/**
 * A {@link HeaderList} that contains all {@link Header}s of another {@link HeaderList} except for those of specific {@link HeaderType}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class RemoveHeaderList extends AbstractComplexHeaderList
{
	private final HeaderList mOriginalHeaders;
	private final HeaderType<?>[] mRemovedHeaderTypes;

	/**
	 * Caches the size of this, so we don't have to traverse the tree of {@link HeaderList}s on each call to {@link #size()}. The default value -1 means
	 * "undetermined".
	 */
	private int mSize = -1;


	/**
	 * Creates a {@link HeaderList} from another {@link HeaderList} but doesn't contain {@link Header}s of the given {@link HeaderType}s
	 * 
	 * @param originalHeaders
	 *            The original {@link HeaderList}.
	 * @param removedHeaderTypes
	 *            The {@link HeaderType}s to remove from the original Headers.
	 */
	public RemoveHeaderList(HeaderList originalHeaders, HeaderType<?>... removedHeaderTypes)
	{
		mOriginalHeaders = originalHeaders;
		mRemovedHeaderTypes = removedHeaderTypes;
	}


	@Override
	public Iterator<Header<?>> iterator()
	{
		return new FilteringIterator<Header<?>>(mOriginalHeaders.iterator(), new IteratorFilter<Header<?>>()
		{
			@Override
			public boolean iterate(Header<?> element)
			{
				return !isRemoved(element.headerType());
			}
		});
	}


	@Override
	public boolean contains(HeaderType<?> headerType)
	{
		return !isRemoved(headerType) && mOriginalHeaders.contains(headerType);
	}


	@Override
	public boolean contains(Header<?> header)
	{
		return !isRemoved(header.headerType()) && mOriginalHeaders.contains(header);
	}


	@Override
	public int size()
	{
		if (mSize < 0)
		{
			// size not known yet, we need to count the iterated headers and cache the result
			int size = 0;
			Iterator<Header<?>> iterator = iterator();
			while (iterator.hasNext())
			{
				++size;
				iterator.next();
			}

			mSize = size;
		}
		return mSize;
	}


	/**
	 * Returns whether a Header of the given type may be in this set or if it's been removed explicitly.
	 * 
	 * @param headerType
	 * @return
	 */
	private boolean isRemoved(HeaderType<?> headerType)
	{
		for (HeaderType<?> type : mRemovedHeaderTypes)
		{
			if (type.equals(headerType))
			{
				return true;
			}
		}
		return false;
	}

}
