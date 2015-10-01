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

import java.io.IOException;
import java.io.InputStream;


/**
 * Defines an interface of an HTTP response message body entity.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface HttpResponseEntity
{

	/**
	 * Returns the content type of the entity if known.
	 * 
	 * @return The content type or <code>null</code> if it's not known.
	 */
	public ContentType contentType();


	/**
	 * Returns the length of the content or a negative number if it's not known.
	 * 
	 * @return The content length or a negative number.
	 * @throws IOException
	 */
	public long contentLength() throws IOException;


	/**
	 * Returns the content {@link InputStream} of the entity.
	 * 
	 * @return An {@link InputStream} or null, if the entity doesn't have any content.
	 * 
	 * @throws IOException
	 */
	public InputStream contentStream() throws IOException;
}
