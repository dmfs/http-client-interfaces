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
 * A collection of static helper methods for handling responses.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class Utils
{
	/**
	 * No instances constructor.
	 */
	private Utils()
	{
	}


	/**
	 * Consumes the given {@link IHttpResponseEntity} without throwing an {@link IOException}.
	 * 
	 * @param entity
	 *            The {@link IHttpResponseEntity} to consume.
	 * 
	 * @see #consumeEntity(IHttpResponseEntity)
	 */
	public static void consumeEntitySilently(IHttpResponseEntity entity)
	{
		try
		{
			consumeEntity(entity);
		}
		catch (IOException e)
		{
			// ignore
		}
	}


	/**
	 * Consumes the given {@link IHttpResponseEntity}. In essence that means the input stream is closed properly.
	 * 
	 * @param entity
	 *            The {@link IHttpResponseEntity} to consume.
	 * @throws IOException
	 */
	public static void consumeEntity(IHttpResponseEntity entity) throws IOException
	{
		if (entity == null)
		{
			// nothing to do
			return;
		}

		InputStream in = entity.getContentStream();
		if (in == null)
		{
			// nothing to do
			return;
		}

		in.close();
	}


	/**
	 * Verifies if the {@link ContentType} of the given {@link IHttpResponseEntity} matches one of the given valid content-types.
	 * 
	 * @param entity
	 *            The entity to verify.
	 * @param validContentTypes
	 *            The allowed content-types.
	 * @return <code>true</code> if the entity has one of the given content-types, <code>false</code> otherwise.
	 */
	public static boolean verifyContentType(IHttpResponseEntity entity, ContentType... validContentTypes)
	{
		if (entity == null)
		{
			throw new NullPointerException("entity must not be null");
		}

		if (validContentTypes == null || validContentTypes.length == 0)
		{
			throw new IllegalArgumentException("no content types given");
		}

		ContentType contentType = entity.getContentType();
		if (contentType == null)
		{
			// the content type is null, which never matches
			return false;
		}

		for (ContentType validContentType : validContentTypes)
		{
			if (contentType.equals(validContentType))
			{
				return true;
			}
		}
		return false;
	}
}
