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

package org.dmfs.httpclientinterfaces.headers.values;

import java.net.URI;
import java.util.Locale;
import java.util.Set;

import org.dmfs.httpclientinterfaces.ContentType;


/**
 * Interface of a link value as specified in <a href="https://tools.ietf.org/html/rfc5988">RFC 5988</a>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public interface Link
{

	/**
	 * Returns the actual link of this Link object.
	 * 
	 * @return
	 */
	public URI link();


	/**
	 * Checks if a specific parameter is present in the Link.
	 * 
	 * @param parameter
	 *            The parameter to check.
	 * @return <code>true</code> if the parameter is present, <code>false</code> otherwise.
	 */
	public boolean hasParameter(String parameter);


	/**
	 * Returns the {@link String} representation of the first occurrence of the given parameter. The returned value may still contain any quotes.
	 * 
	 * @return The value or <code>null</code> if the parameter is not present.
	 */
	public String firstRawParameter(String parameter);


	/**
	 * Returns the {@link String} representations of all occurrence of the given parameter. The returned values may still contain any quotes.
	 * 
	 * @return A Set of Strings, may be empty if the parameter is not present.
	 */
	public Set<String> rawParameter(String parameter);


	/**
	 * Returns the anchor if the link has any.
	 * 
	 * @return The anchor {@link URI} or <code>null</code> if there is no anchor.
	 */
	public URI anchor();


	/**
	 * Return an iterator of all hreflang parameters present in the link.
	 * 
	 * @return A {@link Set} of {@link Locale}s, may be empty, never null.
	 */
	public Set<Locale> hrefLang();


	/**
	 * Returns the value of the title parameter, if there is any or <code>null</code> if there is none.
	 * 
	 * @return
	 */
	public String title();


	/**
	 * Returns the media-type of the link target.
	 * 
	 * @return A {@link ContentType} or <code>null</code> if there is no media-type parameter.
	 */
	public ContentType mediaType();


	/**
	 * Returns the set of relation types of this link.
	 * <p />
	 * Note: According to <a href="https://tools.ietf.org/html/rfc5988#section-5">RFC 5988, Section 5</a> relation types can be Strings or URIs. This
	 * implementation returns all relation types as strings, since it's mostly used as an opaque token.
	 * 
	 * @return
	 */
	public Set<String> relationTypes();


	public Set<String> reverseRelationTypes();
}
