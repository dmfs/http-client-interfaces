/*
 * Copyright (C) 2015 Marten Gajda <marten@dmfs.org>
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

package org.dmfs.httpclientinterfaces;

/**
 * Interface of an instance that can modify the headers of a request.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface HeaderEditor
{
	/**
	 * Set a request header, overriding any other headers of the same name. Setting a <code>null</code> value will remove that header and has the same effect as
	 * calling {@link #removeHeaders(String)}
	 * 
	 * @param header
	 *            The name of the header to set.
	 * @param value
	 *            The value of the header to set.
	 */
	public void setHeader(String header, String value);


	/**
	 * Add a request header. The header will be appended, existing headers with the same name will be preserved.
	 * 
	 * @param header
	 *            The name of the header to append.
	 * @param value
	 *            The value of the header to append.
	 */
	public void addHeader(String header, String value);


	/**
	 * Remove a request header. This will remove all headers having the given name.
	 * 
	 * @param header
	 *            The name of the headers to remove.
	 */
	public void removeHeaders(String header);
}
