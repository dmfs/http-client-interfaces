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
 * An abstract base class for complex {@link HeaderList}s. It implements methods that are common to non-trivial HeaderList implementations.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public abstract class AbstractComplexHeaderList implements HeaderList
{

	/**
	 * Empty constructor.
	 */
	public AbstractComplexHeaderList()
	{
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
			return append(new SingleHeaderList(newHeaders[0]));
		}

		return append(new ArrayHeaderList(newHeaders));
	}


	@Override
	public HeaderList append(HeaderList newHeaders)
	{
		return new JoinedHeaderList(this, newHeaders);
	}


	@Override
	public HeaderList remove(HeaderType<?>... removedHeaderTypes)
	{
		if (removedHeaderTypes.length == 0)
		{
			return this;
		}

		return new RemoveHeaderList(this, removedHeaderTypes);
	}


	@Override
	public <T> Iterator<Header<T>> headersByType(final HeaderType<T> headerType)
	{
		/*
		 * We have a casting problem here. Since we can't cast `resultIterator` to `Iterator<Header<T>>` (due to the unknown generic type) we need to wrap it
		 * into another iterator and cast inside of `next()`.
		 * 
		 * However, the generic types of Header and HeaderType guarantee that we can safely cast Header<?> to Header<T>.
		 */
		final Iterator<?> resultIterator = new FilteringIterator<Header<?>>(iterator(), new IteratorFilter<Header<?>>()
		{
			@Override
			public boolean iterate(Header<?> element)
			{
				return element.headerType().equals(headerType);
			}
		});

		return new Iterator<Header<T>>()
		{
			@Override
			public boolean hasNext()
			{
				return resultIterator.hasNext();
			}


			@SuppressWarnings("unchecked")
			@Override
			public Header<T> next()
			{
				// we can safely cast here, because we know that next will return a Header of Type T
				return (Header<T>) resultIterator.next();
			}


			@Override
			public void remove()
			{
				resultIterator.remove();
			}
		};
	}

}