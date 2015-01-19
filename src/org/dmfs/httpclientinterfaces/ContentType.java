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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Wrapper for content types as defined in <a href="http://tools.ietf.org/html/rfc2045#section-5">RFC 2045 Section 5</a>
 * <p>
 * At present this class doesn't support multiple parameters with the same name. Each occurrence of a key will override any previous occurrences of the same
 * key.
 * </p>
 * {@link ContentType}s are immutable. To derive a new {@link ContentType} with different parameters from an existing one use
 * {@link #ContentType(ContentType, Parameter...)}.
 * <p>
 * Note that {@link #equals(Object)} returns <code>true</code> for two ContentTypes if {@link #mainType} and {@link #subType} equal. Parameters are not taken
 * into account. Use {@link #same(ContentType)} to take parameters into account when comparing two {@link ContentType}s.
 * </p>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ContentType
{
	/**
	 * A pattern that matches the separator in content type strings.
	 */
	private final static Pattern SEMICOLON = Pattern.compile(";");

	/**
	 * The name of the charset parameter.
	 */
	private final static String PARAM_CHARSET = "charset";

	/**
	 * The content type as <code>maintype/subtype</code>.
	 */
	public final String type;

	/**
	 * The main type of the content type.
	 */
	public final String mainType;

	/**
	 * The sub-type of the content type.
	 */
	public final String subType;

	/**
	 * The map of parameters. May be <code>null</code> if there are no parameters.
	 */
	private Map<String, Parameter> mParams;


	/**
	 * Create a new {@link ContentType} from a content type string. If <code>contentType</code> is null {@link #type}, {@link #mainType} and {@link #subType}
	 * will be <code>null</code>.
	 * 
	 * @param contentType
	 *            The content type string to parse.
	 */
	public ContentType(String contentType)
	{
		if (contentType == null)
		{
			type = null;
			mainType = null;
			subType = null;
			mParams = null;
			return;
		}
		String parts[] = SEMICOLON.split(contentType, 0);
		// remove any white spaces from the actual type
		type = parts[0].trim();

		// split main type and sub-type
		int slashIndex = type.indexOf('/');
		if (slashIndex < 0)
		{
			throw new IllegalArgumentException("invalid content-type string (missing slash): '" + contentType + "'");
		}
		mainType = type.substring(0, slashIndex);
		subType = type.substring(slashIndex + 1);

		// parse parameters
		for (int i = 1, len = parts.length; i < len; ++i)
		{
			try
			{
				Parameter param = new Parameter(parts[i]);
				if (mParams == null)
				{
					mParams = new HashMap<String, Parameter>(8);
				}
				mParams.put(param.attribute, param);
			}
			catch (IllegalArgumentException e)
			{
				// ignore for now
			}
		}
	}


	public ContentType(String contentType, Parameter... params)
	{
		this(contentType);

		if (params != null && params.length > 0)
		{
			if (mParams == null)
			{
				mParams = new HashMap<String, Parameter>(8);
			}

			for (Parameter param : params)
			{
				mParams.put(param.attribute, param);
			}
		}
	}


	public ContentType(ContentType contentType, Parameter... params)
	{
		type = contentType.type;
		mainType = contentType.mainType;
		subType = contentType.subType;

		if (params != null && params.length > 0)
		{
			mParams = contentType.mParams == null ? new HashMap<String, Parameter>(8) : new HashMap<String, ContentType.Parameter>(contentType.mParams);

			for (Parameter param : params)
			{
				mParams.put(param.attribute, param);
			}
		}
		else
		{
			// just share the param map if no new parameters have been added
			mParams = contentType.mParams;
		}
	}


	/**
	 * Return the parameter value for a specific key.
	 * 
	 * @param key
	 *            The key of the parameter.
	 * @return The value {@link String}.
	 */
	public Parameter getParam(String key)
	{
		if (mParams == null)
		{
			return null;
		}

		Parameter value = mParams.get(key);

		return value != null ? value : mParams.get(key.toLowerCase(Locale.ENGLISH));
	}


	/**
	 * Returns the value of the charset parameter or <code>null</code> if there is no charset parameter.
	 * 
	 * @return A {@link String} containing the charset or <code>null</code>.
	 */
	public String getCharset()
	{
		Parameter charsetParam = getParam(PARAM_CHARSET);
		return charsetParam == null ? null : charsetParam.value;
	}


	/**
	 * Returns the value of the charset parameter or <code>default</code> if there is no charset parameter.
	 * 
	 * @return A {@link String} containing the charset.
	 */
	public String getCharset(String defaultCharset)
	{
		Parameter charsetParam = getParam(PARAM_CHARSET);
		return charsetParam == null || charsetParam.value.length() == 0 ? defaultCharset : charsetParam.value;
	}


	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(type);
		if (mParams != null)
		{
			for (Parameter param : mParams.values())
			{
				result.append("; ");

				// the code below is more efficient than just calling result.append(param.toString());
				result.append(param.attribute);
				result.append("=\"");
				result.append(param.value);
				result.append("\"");
			}
		}
		return result.toString();
	}


	public static Parameter CharSet(String charset)
	{
		return new Parameter(PARAM_CHARSET, charset);
	}


	@Override
	public int hashCode()
	{
		return type == null ? 0 : type.hashCode();
	}


	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof ContentType && type != null && type.equals(((ContentType) obj).type);
	}


	/**
	 * Indicates whether another {@link ContentType} is the same as this one. In contrase to {@link #equals(Object)} this also takes any parameters into
	 * account.
	 * 
	 * @param other
	 *            The other {@link ContentType}.
	 * @return <code>true</code> if maintype, subtype and parameters of both content-types equal.
	 */
	public boolean same(ContentType other)
	{
		return equals(other) && (mParams == null && other.mParams == null || mParams.equals(other.mParams));
	}

/**
 	 * A content type parameter as defined in <a href="http://tools.ietf.org/html/rfc2045#section-5">RFC 2045 Section 5</a>.
	 * 
	 * A parameter is a key/value pair with the following BNF:
	 *  
	 * <pre>
	 *     parameter := attribute "=" value
	 *     
	 *	   attribute := token
	 *                  ; Matching of attributes
	 *                  ; is ALWAYS case-insensitive.
	 * 
	 *     value := token / quoted-string
	 * 
	 *     token := 1*<any (US-ASCII) CHAR except SPACE, CTLs,
	 *                 or tspecials>
	 *
	 *     tspecials :=  "(" / ")" / "<" / ">" / "@" /
	 *                   "," / ";" / ":" / "\" / <">
	 *                   "/" / "[" / "]" / "?" / "="
	 *                   ; Must be in quoted-string,
	 *                   ; to use within parameter values
	 * </pre>
	 * 
	 * @author Marten Gajda <marten@dmfs.org>
	 *
	 */
	public final static class Parameter
	{
		public final String attribute;
		public final String value;


		/**
		 * Create a new parameter from parsing a parameter string that looks like.
		 * 
		 * <pre>
		 *     parameter := attribute "=" value
		 * </pre>
		 * 
		 * @param paramString
		 *            The parameter string.
		 */
		public Parameter(String paramString)
		{
			if (paramString == null)
			{
				throw new IllegalArgumentException("content-type parameters must not be null");
			}

			paramString = paramString.trim();

			int equalsIndex = paramString.indexOf('=');
			if (equalsIndex < 0)
			{
				throw new IllegalArgumentException("invalid parameter string (missing equals sign): '" + paramString + "'");
			}

			attribute = paramString.substring(0, equalsIndex).toLowerCase(Locale.ENGLISH);
			String val = paramString.substring(equalsIndex + 1).trim();
			if (val.length() > 1 && val.startsWith("\"") && val.endsWith("\""))
			{
				val = val.substring(1, val.length() - 1);
			}
			value = val;
		}


		/**
		 * Create a new parameter from a key value pair.
		 * 
		 * @param key
		 *            The key.
		 * @param value
		 *            The value.
		 */
		public Parameter(String key, String value)
		{
			this.attribute = key.toLowerCase(Locale.ENGLISH);
			this.value = value;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return attribute + "=\"" + value + "\"";
		}
	}
}
