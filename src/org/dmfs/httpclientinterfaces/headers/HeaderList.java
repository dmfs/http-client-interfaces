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

package org.dmfs.httpclientinterfaces.headers;

import java.util.Iterator;


/**
 * Interface of an immutable instance that holds a list of {@link Header}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface HeaderList extends Iterable<Header<?>>
{

	/**
	 * Returns a new {@link HeaderList} that also contains the given {@link Header}s.
	 * 
	 * @param newHeaders
	 *            The {@link Header}s to add.
	 * @return A {@link HeaderList} that contains the new {@link Header}s in addition to the {@link Header}s of this list.
	 */
	public HeaderList append(Header<?>... newHeaders);


	/**
	 * Returns a new {@link HeaderList} that also contains the {@link Header}s in the given {@link HeaderList}.
	 * 
	 * @param newHeaders
	 *            The {@link HeaderList} containing the headers to add.
	 * @return A {@link HeaderList} that contains the new {@link Header}s in addition to the {@link Header}s of this list.
	 */
	public HeaderList append(HeaderList newHeaders);


	/**
	 * Returns a new {@link HeaderList} that doesn't contain any headers of the given types.
	 * 
	 * @param removedHeaderTypes
	 * @return
	 */
	public HeaderList remove(HeaderType<?>... removedHeaderTypes);


	/**
	 * Returns whether this HeaderSet contains a {@link Header} of the given {@link HeaderType}.
	 * 
	 * @param headerType
	 * @return
	 */
	public boolean contains(HeaderType<?> headerType);


	/**
	 * Returns whether this HeaderSet contains the given {@link Header}. This is only true if the set contains a Header having the same {@link HeaderType} and
	 * value.
	 * 
	 * @param header
	 * @return
	 */
	public boolean contains(Header<?> header);


	/**
	 * Get an {@link Iterator} of all {@link Header}s of the given {@link HeaderType} in this list.
	 * 
	 * @param headerType
	 *            The {@link HeaderType} to look for.
	 * @return An {@link Iterator} of {@link Header}s, may be empty, but never <code>null</code>.
	 */
	public <T> Iterator<Header<T>> headersByType(HeaderType<T> headerType);


	/**
	 * Returns the number of {@link Header}s in this {@link HeaderList}.
	 * 
	 * @return
	 */
	public int size();
}
