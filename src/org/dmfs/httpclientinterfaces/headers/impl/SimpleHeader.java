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

import org.dmfs.httpclientinterfaces.headers.Header;
import org.dmfs.httpclientinterfaces.headers.HeaderType;


/**
 * A simple implementation of a {@link Header}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <ValueType>
 *            The type of the header value.
 */
final class SimpleHeader<ValueType> implements Header<ValueType>
{

	private final HeaderType<ValueType> mHeaderType;
	private final ValueType mValue;


	/**
	 * Creates a simple header from {@link HeaderType} and a value.
	 * 
	 * @param headerType
	 *            The {@link HeaderType}.
	 * @param value
	 *            The header value.
	 */
	public SimpleHeader(HeaderType<ValueType> headerType, ValueType value)
	{
		mHeaderType = headerType;
		mValue = value;
	}


	@Override
	public HeaderType<ValueType> headerType()
	{
		return mHeaderType;
	}


	@Override
	public ValueType value()
	{
		return mValue;
	}


	@Override
	public String valueString()
	{
		return mHeaderType.valueToString(mValue);
	}
}
