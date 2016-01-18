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

package org.dmfs.httpclientinterfaces;

import java.io.IOException;

import org.dmfs.httpclientinterfaces.exceptions.ProtocolError;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;


/**
 * An interface of a handler for responses.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @param <T>
 *            The type of the response object returned.
 */
public interface HttpResponseHandler<T>
{
	/**
	 * Actually handles the {@link HttpResponse}. This method "converts" the response into an object of type T.
	 * 
	 * @param response
	 *            The response object.
	 * @return An object that represents the result, may be <code>null</code> if no result is expected.
	 * @throws IOException
	 * @throws ProtocolError
	 * @throws ProtocolException
	 */
	public T handleResponse(HttpResponse response) throws IOException, ProtocolError, ProtocolException;
}
