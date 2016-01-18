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

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.headers.HeaderValueConverter;
import org.dmfs.httpclientinterfaces.headers.values.Link;


/**
 * Converts between String and {@link Link} values.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class LinkHeaderValueConverter implements HeaderValueConverter<Link>
{
	private final static Pattern LINK_HEADER_URI_PATTERN = Pattern.compile("\\s*<([^>]+)>\\s*");
	private final static Pattern LINK_HEADER_PARAMS_PATTERN = Pattern.compile(";\\s*(\\w+)\\s*=\\s*([^\\;\\=\"\\s]+|\"[^\"]+\")\\s*");


	@Override
	public Link parseValue(String headerValueString)
	{
		java.util.regex.Matcher uriMatcher = LINK_HEADER_URI_PATTERN.matcher(headerValueString);
		if (!uriMatcher.lookingAt())
		{
			throw new IllegalArgumentException(String.format("Link header value '%s' is invalid", headerValueString));
		}

		List<LinkValue.Param> params = new ArrayList<LinkValue.Param>(10);
		java.util.regex.Matcher paramMatcher = LINK_HEADER_PARAMS_PATTERN.matcher(headerValueString);
		while (paramMatcher.find())
		{
			params.add(new LinkValue.Param(paramMatcher.group(1), paramMatcher.group(2)));
		}

		return new LinkValue(URI.create(uriMatcher.group(1)), params);
	}


	@Override
	public String valueString(Link headerValue)
	{
		throw new UnsupportedOperationException("serializing Links is not supported yet");
	}

	private final static class LinkValue implements Link
	{
		private final static class Param
		{
			private final String mName;
			private final String mValue;


			public Param(String name, String value)
			{
				mName = name;
				mValue = value;
			}
		}

		private final URI mUri;
		private final List<Param> mParams;


		private LinkValue(URI uri, List<Param> params)
		{
			mUri = uri;
			mParams = params;
		}


		@Override
		public URI link()
		{
			return mUri;
		}


		@Override
		public boolean hasParameter(String parameter)
		{
			for (Param param : mParams)
			{
				if (parameter.equals(param.mName))
				{
					return true;
				}
			}
			return false;
		}


		@Override
		public String firstRawParameter(String parameter)
		{
			for (Param param : mParams)
			{
				if (parameter.equals(param.mName))
				{
					return param.mValue;
				}
			}
			return null;
		}


		@Override
		public Set<String> rawParameter(String parameter)
		{
			Set<String> result = null;
			for (Param param : mParams)
			{
				if (parameter.equals(param.mName))
				{
					if (result == null)
					{
						result = new HashSet<String>(10);
					}
					result.add(param.mValue);
				}
			}
			Set<String> emptySet = Collections.emptySet();
			return result == null ? emptySet : result;
		}


		@Override
		public URI anchor()
		{
			String anchor = firstRawParameter("anchor");
			return anchor == null ? null : URI.create(unquote(anchor));
		}


		@Override
		public Set<Locale> hrefLang()
		{
			Set<String> langs = rawParameter("hreflang");
			if (langs.size() == 0)
			{
				return Collections.emptySet();
			}

			Set<Locale> locales = new HashSet<Locale>(langs.size());
			for (String lang : langs)
			{
				locales.add(Locale.forLanguageTag(lang));
			}
			return locales;
		}


		@Override
		public String title()
		{
			return unquote(firstRawParameter("title"));
		}


		@Override
		public ContentType mediaType()
		{
			String mediaType = firstRawParameter("type");
			return mediaType == null ? null : new ContentType(unquote(mediaType));
		}


		@Override
		public Set<String> relationTypes()
		{
			return new HashSet<String>(Arrays.asList(unquote(firstRawParameter("rel")).split(" ")));
		}


		@Override
		public Set<String> reverseRelationTypes()
		{
			return new HashSet<String>(Arrays.asList(unquote(firstRawParameter("rev")).split(" ")));
		}


		private String unquote(String rawValue)
		{
			if (rawValue.startsWith("\"") && rawValue.endsWith("\""))
			{
				return rawValue.substring(1, rawValue.length() - 1);
			}
			return rawValue;
		}
	}
}
