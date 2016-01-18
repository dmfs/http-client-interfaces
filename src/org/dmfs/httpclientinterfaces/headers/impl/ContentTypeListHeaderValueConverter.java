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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.headers.HeaderValueConverter;


/**
 * {@link HeaderValueConverter} for header values that consist of a list of {@link ContentType}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class ContentTypeListHeaderValueConverter implements HeaderValueConverter<List<ContentType>>
{
	@Override
	public List<ContentType> parseValue(String headerValueString)
	{
		if (headerValueString == null || headerValueString.length() == 0)
		{
			return Collections.emptyList();
		}

		List<ContentType> result = new ArrayList<ContentType>(8);
		int next = 0;
		int start = 0;
		while ((next = indexOfUnquotedComma(headerValueString, start)) >= 0)
		{
			result.add(new ContentType(headerValueString.substring(start, next - 1).trim()));
			start = next + 1;
		}
		if (start < headerValueString.length())
		{
			result.add(new ContentType(headerValueString.substring(start).trim()));
		}
		return result;
	}


	@Override
	public String valueString(List<ContentType> headerValue)
	{
		StringBuilder result = new StringBuilder(headerValue.size() * 30);
		boolean first = true;
		for (ContentType contentType : headerValue)
		{
			if (first)
			{
				first = false;
			}
			else
			{
				result.append(',');
			}
			result.append(contentType.toString());
		}
		return result.toString();
	}


	private int indexOfUnquotedComma(final String haystack, final int start)
	{
		boolean isQuoted = false;
		for (int i = start, count = haystack.length(); i < count; ++i)
		{
			char c = haystack.charAt(i);
			switch (c)
			{
				case '"':
				{
					isQuoted = !isQuoted;
					break;
				}
				case ',':
				{
					if (!isQuoted)
					{
						return i;
					}
				}
			}
		}
		return -1;
	}
}
