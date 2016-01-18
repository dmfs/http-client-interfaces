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

/**
 * Represents a header type. That is, it binds a header name to a value type.
 * <p />
 * Two {@link HeaderType}s are considered to equal if they have the same header name.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <ValueType>
 *            The type of the header value.
 */
public interface HeaderType<ValueType>
{

	/**
	 * Returns the name of the header.
	 * 
	 * @return The header name, never <code>null</code>.
	 */
	public String headerName();


	/**
	 * Factory method to create {@link Header}s of this type from the given {@link String} representation.
	 * 
	 * @param headerValueString
	 *            The String representation of the Header as transferred over the wire.
	 * @return A new {@link Header} instance.
	 */
	public Header<ValueType> headerFromString(String headerValueString);


	/**
	 * Factory method to create {@link Header}s of this type from a value.
	 * 
	 * @param header
	 *            The value of the header, must not be <code>null</code>
	 * @return A new {@link Header} instance.
	 */
	public Header<ValueType> header(ValueType value);


	/**
	 * Returns the string representation of the given value as defined for this {@link HeaderType}.
	 * 
	 * @param headerValue
	 *            The value to convert.
	 * @return A header value String representing the given value.
	 */
	public String valueToString(ValueType headerValue);

}
