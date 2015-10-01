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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dmfs.httpclientinterfaces.exceptions.UnhandledStatusError;
import org.dmfs.httpclientinterfaces.exceptions.UnknownStatusError;


/**
 * Represents an HTTP status. Instances are immutable. To receive an {@link HttpStatus} for a specific status code call {@link #fromStatusCode(int)}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class HttpStatus
{
	/**
	 * A map of codes to their respective reasons.
	 */
	private final static Map<Integer, HttpStatus> STATUS_CODES = Collections.synchronizedMap(new HashMap<Integer, HttpStatus>(50));

	/*
	 * 1xx Informational status codes, see http://tools.ietf.org/html/rfc7231#section-6.2
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.2.1">100 Continue</a>
	 */
	public final static HttpStatus CONTINUE = new HttpStatus(100, "Continue");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.2.2">101 Switching Protocols</a>
	 */
	public final static HttpStatus SWITCHING_PROTOCOLS = new HttpStatus(101, "Switching Protocols");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc2518#section-10.1">102 Processing</a> (WebDAV)
	 * <p>
	 * Note that this has been removed from the WebDAV specification in RFC 4918, see <a href="http://tools.ietf.org/html/rfc4918#section-21.4">RFC 4918,
	 * section 21.4</a
	 * </p>
	 */
	public final static HttpStatus PROCESSING = new HttpStatus(102, "Processing");

	/*
	 * 2xx Successful status codes, see: http://tools.ietf.org/html/rfc7231#section-6.3
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.1">200 OK</a>
	 */
	public final static HttpStatus OK = new HttpStatus(200, "OK");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.2">201 Created</a>
	 */
	public final static HttpStatus CREATED = new HttpStatus(201, "CREATED");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.3">202 Accepted</a>
	 */
	public final static HttpStatus ACCEPTED = new HttpStatus(202, "Accepted");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.4">203 Non-Authoritative Information</a>
	 */
	public final static HttpStatus NON_AUTHORITATIVE_INFORMATION = new HttpStatus(203, "Non-Authoritative Information");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.5">204 No Content</a>
	 */
	public final static HttpStatus NO_CONTENT = new HttpStatus(204, "No Content");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.6">205 Reset Content</a>
	 */
	public final static HttpStatus RESET_CONTENT = new HttpStatus(205, "Reset Content");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7233#section-4.1">206 Partial Content</a>
	 */
	public final static HttpStatus PARTIAL_CONTENT = new HttpStatus(206, "Partial Content");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.1">207 Multistatus</a> (WebDAV)
	 */
	public final static HttpStatus MULTISTATUS = new HttpStatus(207, "Multistatus");

	/*
	 * 3xx Redirection status codes, see: http://tools.ietf.org/html/rfc2068#section-10.3
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.1">300 Multiple Choices</a>
	 */
	public final static HttpStatus MULTIPLE_CHOICES = new HttpStatus(300, "Multiple Choices");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.2">301 Moved Permanently</a>
	 */
	public final static HttpStatus MOVED_PERMANENTLY = new HttpStatus(301, "Moved Permanently");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.3">302 Moved Temporarily</a>
	 */
	public final static HttpStatus FOUND = new HttpStatus(302, "Found");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.4">303 See Other</a>
	 */
	public final static HttpStatus SEE_OTHER = new HttpStatus(303, "See Other");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7232#section-4.1">304 Not Modified</a>
	 */
	public final static HttpStatus NOT_MODIFIED = new HttpStatus(304, "Not Modified");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.5">305 Use Proxy</a>
	 */
	public final static HttpStatus USE_PROXY = new HttpStatus(305, "Use Proxy");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.7">307 Temporary Redirect</a>
	 */
	public final static HttpStatus TEMPORARY_REDIRECT = new HttpStatus(307, "Temporary Redirect");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7238#section-3">308 Permanent Redirect</a>
	 */
	public final static HttpStatus PERMANENT_REDIRECT = new HttpStatus(308, "Permanent Redirect");

	/*
	 * 4xx Client Error status codes, see: http://tools.ietf.org/html/rfc2068#section-10.4
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.1">400 Bad Request</a>
	 */
	public final static HttpStatus BAD_REQUEST = new HttpStatus(400, "Bad Request");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7235#section-3.1">401 Unauthorized</a>
	 */
	public final static HttpStatus UNAUTHORIZED = new HttpStatus(401, "Unauthorized");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.2">402 Payment Required</a>
	 */
	public final static HttpStatus PAYMENT_REQUIRED = new HttpStatus(402, "Payment Required");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.3">403 Forbidden</a>
	 */
	public final static HttpStatus FORBIDDEN = new HttpStatus(403, "Forbidden");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">404 Not Found</a>
	 */
	public final static HttpStatus NOT_FOUND = new HttpStatus(404, "Not Found");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.5">405 Method Not Allowed</a>
	 */
	public final static HttpStatus METHOD_NOT_ALLOWED = new HttpStatus(405, "Method Not Allowed");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.6">406 Not Acceptable</a>
	 */
	public final static HttpStatus NOT_ACCEPTABLE = new HttpStatus(406, "Not Acceptable");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7235#section-3.2">407 Proxy Authentication Required</a>
	 */
	public final static HttpStatus PROXY_AUTHENTICATION_REQUIRED = new HttpStatus(407, "Proxy Authentication Required");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.7">408 Request Timeout</a>
	 */
	public final static HttpStatus REQUEST_TIMEOUT = new HttpStatus(408, "Request Timeout");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.8">409 Conflict</a>
	 */
	public final static HttpStatus CONFLICT = new HttpStatus(409, "Conflict");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.10">410 Gone</a>
	 */
	public final static HttpStatus GONE = new HttpStatus(410, "Gone");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-10.4.12">411 Length Required</a>
	 */
	public final static HttpStatus LENGTH_REQUIRED = new HttpStatus(411, "Length Required");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7232#section-4.2">412 Precondition Failed</a>
	 */
	public final static HttpStatus PRECONDITION_FAILED = new HttpStatus(412, "Precondition Failed");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.11">413 Payload Too Large</a>
	 */
	public final static HttpStatus PAYLOAD_TOO_LARGE = new HttpStatus(413, "Payload Too Large");

	/**
	 * Old name of {@link #PAYLOAD_TOO_LARGE}.
	 */
	@Deprecated
	public final static HttpStatus REQUEST_ENTITY_TOO_LARGE = PAYLOAD_TOO_LARGE;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.12">414 URI Too Long</a>
	 */
	public final static HttpStatus URI_TOO_LONG = new HttpStatus(414, "URI Too Long");

	/**
	 * Old name of {@link #URI_TOO_LONG}.
	 */
	@Deprecated
	public final static HttpStatus REQUEST_URI_TOO_LONG = URI_TOO_LONG;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.13">415 Unsupported Media Type</a>
	 */
	public final static HttpStatus UNSUPPORTED_MEDIA_TYPE = new HttpStatus(415, "Unsupported Media Type");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.14">417 Expectation Failed</a>
	 */
	public final static HttpStatus EXPECTATION_FAILED = new HttpStatus(417, "Expectation Failed");
	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.2">422 Unprocessable Entity</a> (WebDAV)
	 */
	public final static HttpStatus UNPROCESSABLE_ENTITY = new HttpStatus(422, "Unprocessable Entity");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.3">423 Locked</a> (WebDAV)
	 */
	public final static HttpStatus LOCKED = new HttpStatus(423, "Locked");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.4">424 Failed Dependency</a> (WebDAV)
	 */
	public final static HttpStatus FAILED_DEPENDENCY = new HttpStatus(424, "Failed Dependency");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.15">426 Upgrade Required</a>
	 */
	public final static HttpStatus UPGRADE_REQUIRED = new HttpStatus(426, "Upgrade Required");

	/*
	 * 5xx Server Error status codes, see: http://tools.ietf.org/html/rfc2068#section-10.5
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">500 Internal Server Error</a>
	 */
	public final static HttpStatus INTERNAL_SERVER_ERROR = new HttpStatus(500, "Internal Server Error");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.2">501 Not Implemented</a>
	 */
	public final static HttpStatus NOT_IMPLEMENTED = new HttpStatus(501, "Not Implemented");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.3">502 Bad Gateway</a>
	 */
	public final static HttpStatus BAD_GATEWAY = new HttpStatus(502, "Bad Gateway");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.4">503 Service Unavailable</a>
	 */
	public final static HttpStatus SERVICE_UNAVAILABLE = new HttpStatus(503, "Service Unavailable");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.5">504 Gateway Timeout</a>
	 */
	public final static HttpStatus GATEWAY_TIMEOUT = new HttpStatus(504, "Gateway Timeout");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.6">505 HTTP Version Not Supported</a>
	 */
	public final static HttpStatus HTTP_VERSION_NOT_SUPPORTED = new HttpStatus(505, "HTTP Version Not Supported");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc2295#section-8.1">506 Variant Also Negotiates</a> (experimental)
	 */
	public final static HttpStatus VARIANT_ALSO_NEGOTIATES = new HttpStatus(506, "Variant Also Negotiates");

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.5">507 Insufficient Storage</a> (WebDAV)
	 */
	public final static HttpStatus INSUFFICIENT_STORAGE = new HttpStatus(507, "Insufficient Storage");

	/**
	 * The actual status code.
	 */
	public final int statusCode;

	/**
	 * An HTTP/1.1 status line of this status code.
	 */
	public final String http11StatusLine;


	/**
	 * Initialize an HttpStatus object.
	 * 
	 * @param statusCode
	 *            The status code.
	 * @param reasonPhrase
	 *            The reason phrase of this status.
	 */
	private HttpStatus(int statusCode, String reasonPhrase)
	{
		this.statusCode = statusCode;
		this.http11StatusLine = http11StatusLine(statusCode, reasonPhrase);
		STATUS_CODES.put(statusCode, this);
	}


	/**
	 * Parse an HTTP status line for the status code. The status line must comply to <a href="https://tools.ietf.org/html/rfc7230#section-3.1.2">RFC 7230
	 * section 3.1.2</a>.
	 * <p />
	 * This method is rather tolerant. It doesn't actually verify the HTTP version nor the reason phrase.
	 * 
	 * @param statusLine
	 *            A {@link String} containing an HTTP status line.
	 * @return The {@link HttpStatus} object.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given status line doesn't contain a known status code.
	 * @throws UnhandledStatusError
	 *             if the status code is unknown
	 */
	public static HttpStatus fromStatusLine(final String statusLine) throws IllegalArgumentException, UnknownStatusError
	{
		/*
		 * According to RFC 7230, a valid HTTP status line always looks like
		 * 
		 * HTTP-Version SP Status-Code SP Reason-Phrase CRLF
		 * 
		 * This method method scans for the two spaces and tries to convert the string in between to a number.
		 */

		// the status code starts one character after the first space
		final int start = statusLine.indexOf(' ') + 1;
		if (start > 0)
		{

			final int end = statusLine.indexOf(' ', start);
			if (end > 0)
			{

				try
				{
					final int status = Integer.parseInt(statusLine.substring(start, end));

					// valid status codes are between 100 and 599
					if (status >= 100 && status <= 599)
					{
						return fromStatusCode(status);
					}
					throw new UnknownStatusError(status, "Unknown status code");
				}
				catch (NumberFormatException e)
				{
					throw new IllegalArgumentException("could not parse status code", e);
				}
			}
		}
		throw new IllegalArgumentException("Malformed status line: " + statusLine);
	}


	/**
	 * Returns the {@link HttpStatus} having the given status code.
	 * 
	 * @param statusCode
	 *            An HTTP status code integer.
	 * @return The {@link HttpStatus} having the given status code.
	 * @throws UnhandledStatusError
	 *             if the status code is unknown.
	 */
	public static HttpStatus fromStatusCode(int statusCode) throws UnknownStatusError
	{
		HttpStatus result = STATUS_CODES.get(statusCode);
		if (result == null)
		{
			throw new UnknownStatusError(statusCode, "Unknown status code");
		}
		return result;
	}


	/**
	 * Returns the reason phrase of this status code.
	 * <p>
	 * <strong>Note:</strong> the reason phrase doesn't contain the status code itself.
	 * </p>
	 * 
	 * @return The reason phrase or <code>null</code> if the status code is unknown.
	 */
	public String reason()
	{
		return http11StatusLine.substring(13);
	}


	/**
	 * Returns an HTTP/1.1 status line of this status code.
	 * 
	 * @return The status line.
	 */
	private static String http11StatusLine(int code, String reason)
	{
		StringBuilder result = new StringBuilder(48);
		result.append("HTTP/1.1 ");
		result.append(code);
		result.append(' ');
		result.append(reason);
		return result.toString();
	}


	/**
	 * Returns whether this represents an informational status code.
	 * 
	 * @return <code>true</code> if this represents an informational status code, <code>false</code> otherwise.
	 */
	public boolean isInformational()
	{
		return statusCode >= 100 && statusCode < 200;
	}


	/**
	 * Returns whether this status represents a success status code.
	 * 
	 * @return <code>true</code> if this represents a success status code, <code>false</code> otherwise.
	 */
	public boolean isSuccess()
	{
		return statusCode >= 200 && statusCode < 300;
	}


	/**
	 * Returns whether this status represents a redirection status code.
	 * 
	 * @return <code>true</code> if this represents a redirection status code, <code>false</code> otherwise.
	 */
	public boolean isRedirect()
	{
		return statusCode >= 300 && statusCode < 400;
	}


	/**
	 * Returns whether this status represents a client error status code.
	 * 
	 * @return <code>true</code> if this represents a client error status code, <code>false</code> otherwise.
	 */
	public boolean isClientError()
	{
		return statusCode >= 400 && statusCode < 500;
	}


	/**
	 * Returns whether this status represents a server error status code.
	 * 
	 * @return <code>true</code> if this represents a server error status code, <code>false</code> otherwise.
	 */
	public boolean isServerError()
	{
		return statusCode >= 500 && statusCode < 600;
	}


	@Override
	public int hashCode()
	{
		return statusCode;
	}


	@Override
	public boolean equals(Object obj)
	{
		// since the constructor is private, we can safely assume that there is only one instance for each status code
		return this == obj;
	}
}
