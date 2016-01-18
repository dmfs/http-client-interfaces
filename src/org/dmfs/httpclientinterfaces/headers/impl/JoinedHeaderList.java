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
import org.dmfs.httpclientinterfaces.utils.SerialIterator;


/**
 * A {@link HeaderList} that concatenates the {@link Header}s of two other {@link HeaderList}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class JoinedHeaderList extends AbstractComplexHeaderList
{
	private final HeaderList mList1;
	private final HeaderList mList2;

	/**
	 * Caches the size of this, so we don't have to traverse the tree of {@link HeaderList}s on each call to {@link #size()}. The default value -1 means
	 * "undetermined".
	 */
	private int mSize = -1;


	/**
	 * Create a {@link HeaderList} that contains the {@link Header}s of the two given {@link HeaderList}s.
	 * 
	 * @param list1
	 * @param list2
	 */
	public JoinedHeaderList(HeaderList list1, HeaderList list2)
	{
		mList1 = list1;
		mList2 = list2;
	}


	@Override
	public Iterator<Header<?>> iterator()
	{
		return new SerialIterator<Header<?>>(mList1.iterator(), mList2.iterator());
	}


	@Override
	public boolean contains(HeaderType<?> headerType)
	{
		return mList1.contains(headerType) || mList2.contains(headerType);
	}


	@Override
	public boolean contains(Header<?> header)
	{
		return mList1.contains(header) || mList2.contains(header);
	}


	@Override
	public int size()
	{
		if (mSize < 0)
		{
			mSize = mList1.size() + mList2.size();
		}

		return mSize;
	}
}
