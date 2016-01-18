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
import org.dmfs.httpclientinterfaces.headers.HeaderValueConverter;


/**
 * Simple header type class.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <ValueType>
 *            The type of the header value.
 */
public final class SimpleHeaderType<ValueType> implements HeaderType<ValueType>
{
	private final String mHeaderName;
	private final HeaderValueConverter<ValueType> mValueConverter;


	/**
	 * Creates a {@link HeaderType} for the given header name. Header values are converted using the provided {@link HeaderValueConverter}.
	 * 
	 * @param headerName
	 *            The name of the header.
	 * @param valueConverter
	 *            A {@link HeaderValueConverter} to convert header values from/to string.
	 */
	public SimpleHeaderType(String headerName, HeaderValueConverter<ValueType> valueConverter)
	{
		mHeaderName = headerName;
		mValueConverter = valueConverter;
	}


	@Override
	public String headerName()
	{
		return mHeaderName;
	}


	@Override
	public Header<ValueType> headerFromString(String headerValueString)
	{
		return new SimpleHeader<ValueType>(this, mValueConverter.parseValue(headerValueString));
	}


	@Override
	public Header<ValueType> header(ValueType value)
	{
		return new SimpleHeader<ValueType>(this, value);
	}


	@Override
	public String valueToString(ValueType headerValue)
	{
		return mValueConverter.valueString(headerValue);
	}


	@Override
	public int hashCode()
	{
		return mHeaderName.hashCode();
	}


	@Override
	public boolean equals(Object obj)
	{
		return this == obj || (obj instanceof HeaderType && mHeaderName.equals(((HeaderType<?>) obj).headerName()));
	}
}
