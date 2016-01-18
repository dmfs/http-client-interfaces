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
 * An {@link Iterator} that serializes the results of other iterators.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class SerialIterator<T> implements Iterator<T>
{

	private final Iterator<T>[] mIterators;

	private int mCurrentIterator = 0;


	@SafeVarargs
	public SerialIterator(Iterator<T>... iterators)
	{
		mIterators = iterators;
	}


	@Override
	public boolean hasNext()
	{
		while (mCurrentIterator < mIterators.length && !mIterators[mCurrentIterator].hasNext())
		{
			++mCurrentIterator;
		}

		return mCurrentIterator < mIterators.length;
	}


	@Override
	public T next()
	{
		if (!hasNext())
		{
			throw new ArrayIndexOutOfBoundsException("No more elements to iterate");
		}

		return mIterators[mCurrentIterator].next();
	}


	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("remove() not is supported by this iterator.");
	}

}