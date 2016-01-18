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
 * Helper convert header values to Objects and back to their String representations.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <ValueType>
 *            The value type that's converted.
 */
public interface HeaderValueConverter<ValueType>
{

	/**
	 * Parses the given string representation of a header value into an Object of type <code>ValueType</code>.
	 * 
	 * @param header
	 *            The string representation of the header value.
	 * @return The header value as an object.
	 */
	public ValueType parseValue(String headerValueString);


	/**
	 * Renders the given header value into its string reperesentation.
	 * 
	 * @param headerValue
	 *            The header value.
	 * @return The string representation of the value.
	 */
	public String valueString(ValueType headerValue);
}
