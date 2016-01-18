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

package org.dmfs.httpclientinterfaces.utils;

import java.util.Iterator;


/**
 * An {@link Iterator} that returns the elements of another Iterator, possibly filtering out some of the elements.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <E>
 */
public final class FilteringIterator<E> implements Iterator<E>
{
	public interface IteratorFilter<E>
	{
		boolean iterate(E element);
	}

	private final Iterator<E> mIterator;
	private final IteratorFilter<E> mFilter;

	private E mNext;
	private boolean mHasNext;


	public FilteringIterator(Iterator<E> iterator, IteratorFilter<E> filter)
	{
		mIterator = iterator;
		mFilter = filter;
		moveToNext();
	}


	@Override
	public boolean hasNext()
	{
		return mHasNext;
	}


	@Override
	public E next()
	{
		if (!mHasNext)
		{
			throw new ArrayIndexOutOfBoundsException("No more elements to iterate");
		}

		E result = mNext;
		moveToNext();
		return result;
	}


	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("remove() not is supported by this iterator.");
	}


	private void moveToNext()
	{
		while (mIterator.hasNext())
		{
			E next = mIterator.next();
			if (mFilter.iterate(next))
			{
				mNext = next;
				mHasNext = true;
				return;
			}
		}

		mHasNext = false;
	}
}
