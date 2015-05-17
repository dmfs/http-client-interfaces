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
import java.util.Map;


/**
 * Provides HTTP status codes and some static helper methods.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class HttpStatus
{
	/*
	 * 1xx Informational status codes, see http://tools.ietf.org/html/rfc7231#section-6.2
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.2.1">100 Continue</a>
	 */
	public final static int CONTINUE = 100;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.2.2">101 Switching Protocols</a>
	 */
	public final static int SWITCHING_PROTOCOLS = 101;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc2518#section-10.1">102 Processing</a> (WebDAV)
	 * <p>
	 * Note that this has been removed from the WebDAV specification in RFC 4918, see <a href="http://tools.ietf.org/html/rfc4918#section-21.4">RFC 4918,
	 * section 21.4</a
	 * </p>
	 */
	public final static int PROCESSING = 102;

	/*
	 * 2xx Successful status codes, see: http://tools.ietf.org/html/rfc7231#section-6.3
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.1">200 OK</a>
	 */
	public final static int OK = 200;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.2">201 Created</a>
	 */
	public final static int CREATED = 201;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.3">202 Accepted</a>
	 */
	public final static int ACCEPTED = 202;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.4">203 Non-Authoritative Information</a>
	 */
	public final static int NON_AUTHORITATIVE_INFORMATION = 203;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.5">204 No Content</a>
	 */
	public final static int NO_CONTENT = 204;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.3.6">205 Reset Content</a>
	 */
	public final static int RESET_CONTENT = 205;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7233#section-4.1">206 Partial Content</a>
	 */
	public final static int PARTINAL_CONTENT = 206;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.1">207 Multistatus</a> (WebDAV)
	 */
	public final static int MULTISTATUS = 207;

	/*
	 * 3xx Redirection status codes, see: http://tools.ietf.org/html/rfc2068#section-10.3
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.1">300 Multiple Choices</a>
	 */
	public final static int MULTIPLE_CHOICES = 300;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.2">301 Moved Permanently</a>
	 */
	public final static int MOVED_PERMANENTLY = 301;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.3">302 Moved Temporarily</a>
	 */
	public final static int FOUND = 302;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.4">303 See Other</a>
	 */
	public final static int SEE_OTHER = 303;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7232#section-4.1">304 Not Modified</a>
	 */
	public final static int NOT_MODIFIED = 304;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.5">305 Use Proxy</a>
	 */
	public final static int USE_PROXY = 305;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.4.7">307 Temporary Redirect</a>
	 */
	public final static int TEMPORARY_REDIRECT = 307;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7238#section-3">308 Permanent Redirect</a>
	 */
	public final static int PERMANENT_REDIRECT = 308;

	/*
	 * 4xx Client Error status codes, see: http://tools.ietf.org/html/rfc2068#section-10.4
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.1">400 Bad Request</a>
	 */
	public final static int BAD_REQUEST = 400;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7235#section-3.1">401 Unauthorized</a>
	 */
	public final static int UNAUTHORIZED = 401;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.2">402 Payment Required</a>
	 */
	public final static int PAYMENT_REQUIRED = 402;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.3">403 Forbidden</a>
	 */
	public final static int FORBIDDEN = 403;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">404 Not Found</a>
	 */
	public final static int NOT_FOUND = 404;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.5">405 Method Not Allowed</a>
	 */
	public final static int METHOD_NOT_ALLOWED = 405;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.6">406 Not Acceptable</a>
	 */
	public final static int NOT_ACCEPTABLE = 406;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7235#section-3.2">407 Proxy Authentication Required</a>
	 */
	public final static int PROXY_AUTHENTICATION_REQUIRED = 407;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.7">408 Request Timeout</a>
	 */
	public final static int REQUEST_TIMEOUT = 408;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.8">409 Conflict</a>
	 */
	public final static int CONFLICT = 409;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.10">410 Gone</a>
	 */
	public final static int GONE = 410;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-10.4.12">411 Length Required</a>
	 */
	public final static int LENGTH_REQUIRED = 411;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7232#section-4.2">412 Precondition Failed</a>
	 */
	public final static int PRECONDITION_FAILED = 412;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.11">413 Payload Too Large</a>
	 */
	public final static int PAYLOAD_TOO_LARGE = 413;

	/**
	 * Old name of {@link #PAYLOAD_TOO_LARGE}.
	 */
	public final static int REQUEST_ENTITY_TOO_LARGE = PAYLOAD_TOO_LARGE;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.12">414 URI Too Long</a>
	 */
	public final static int URI_TOO_LONG = 414;

	/**
	 * Old name of {@link #URI_TOO_LONG}.
	 */
	public final static int REQUEST_URI_TOO_LONG = URI_TOO_LONG;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.13">415 Unsupported Media Type</a>
	 */
	public final static int UNSUPPORTED_MEDIA_TYPE = 415;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.14">417 Expectation Failed</a>
	 */
	public final static int EXPECTATION_FAILED = 417;
	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.2">422 Unprocessable Entity</a> (WebDAV)
	 */
	public final static int UNPROCESSABLE_ENTITY = 422;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.3">423 Locked</a> (WebDAV)
	 */
	public final static int LOCKED = 423;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.4">424 Failed Dependency</a> (WebDAV)
	 */
	public final static int FAILED_DEPENDENCY = 424;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.5.15">426 Upgrade Required</a>
	 */
	public final static int UPGRADE_REQUIRED = 426;
	/*
	 * 5xx Server Error status codes, see: http://tools.ietf.org/html/rfc2068#section-10.5
	 */

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">500 Internal Server Error</a>
	 */
	public final static int INTERNAL_SERVER_ERROR = 500;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.2">501 Not Implemented</a>
	 */
	public final static int NOT_IMPLEMENTED = 501;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.3">502 Bad Gateway</a>
	 */
	public final static int BAD_GATEWAY = 502;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.4">503 Service Unavailable</a>
	 */
	public final static int SERVICE_UNAVAILABLE = 503;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.5">504 Gateway Timeout</a>
	 */
	public final static int GATEWAY_TIMEOUT = 504;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc7231#section-6.6.6">505 HTTP Version Not Supported</a>
	 */
	public final static int HTTP_VERSION_NOT_SUPPORTED = 505;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc2295#section-8.1">506 Variant Also Negotiates</a> (experimental)
	 */
	public final static int VARIANT_ALSO_NEGOTIATES = 506;

	/**
	 * HTTP status: <a href="http://tools.ietf.org/html/rfc4918#section-11.5">507 Insufficient Storage</a> (WebDAV)
	 */
	public final static int INSUFFICIENT_STORAGE = 507;

	/**
	 * A map of codes to their respectice reasons.
	 */
	private final static Map<Integer, String> REASONS = buildReasonMap();


	private static Map<Integer, String> buildReasonMap()
	{
		Map<Integer, String> result = new HashMap<Integer, String>(48);
		result.put(CONTINUE, "Continue");
		result.put(SWITCHING_PROTOCOLS, "Switching Protocols");
		result.put(PROCESSING, "Processing");
		result.put(OK, "OK");
		result.put(CREATED, "CREATED");
		result.put(ACCEPTED, "Accepted");
		result.put(NON_AUTHORITATIVE_INFORMATION, "Non-Authoritative Information");
		result.put(NO_CONTENT, "No Content");
		result.put(RESET_CONTENT, "Reset Content");
		result.put(PARTINAL_CONTENT, "Partial Content");
		result.put(MULTISTATUS, "Multistatus");
		result.put(MULTIPLE_CHOICES, "Multiple Choices");
		result.put(MOVED_PERMANENTLY, "Moved Permanently");
		result.put(FOUND, "Found");
		result.put(SEE_OTHER, "See Other");
		result.put(NOT_MODIFIED, "Not Modified");
		result.put(USE_PROXY, "Use Proxy");
		result.put(TEMPORARY_REDIRECT, "Temporary Redirect");
		result.put(PERMANENT_REDIRECT, "Permanent Redirect");
		result.put(BAD_REQUEST, "Bad Request");
		result.put(UNAUTHORIZED, "Unauthorized");
		result.put(PAYMENT_REQUIRED, "Payment Required");
		result.put(FORBIDDEN, "Forbidden");
		result.put(NOT_FOUND, "Not Found");
		result.put(METHOD_NOT_ALLOWED, "Method Not Allowed");
		result.put(NOT_ACCEPTABLE, "Not Acceptable");
		result.put(PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required");
		result.put(REQUEST_TIMEOUT, "Request Timeout");
		result.put(CONFLICT, "Conflict");
		result.put(GONE, "Gone");
		result.put(LENGTH_REQUIRED, "Length Required");
		result.put(PRECONDITION_FAILED, "Precondition Failed");
		result.put(PAYLOAD_TOO_LARGE, "Payload Too Large");
		result.put(URI_TOO_LONG, "URI Too Long");
		result.put(UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
		result.put(EXPECTATION_FAILED, "Expectation Failed");
		result.put(UNPROCESSABLE_ENTITY, "Unprocessable Entity");
		result.put(LOCKED, "Locked");
		result.put(FAILED_DEPENDENCY, "Failed Dependency");
		result.put(UPGRADE_REQUIRED, "Upgrade Required");
		result.put(INTERNAL_SERVER_ERROR, "Internal Server Error");
		result.put(NOT_IMPLEMENTED, "Not Implemented");
		result.put(BAD_GATEWAY, "Bad Gateway");
		result.put(SERVICE_UNAVAILABLE, "Service Unavailable");
		result.put(GATEWAY_TIMEOUT, "Gateway Timeout");
		result.put(HTTP_VERSION_NOT_SUPPORTED, "HTTP Version Not Supported");
		result.put(VARIANT_ALSO_NEGOTIATES, "Variant Also Negotiates");
		result.put(INSUFFICIENT_STORAGE, "Insufficient Storage");
		return result;
	}


	/**
	 * Parse an HTTP status line for the status code. The status line must comply to <a href="https://tools.ietf.org/html/rfc7230#section-3.1.2">RFC 7230
	 * section 3.1.2</a>.
	 * 
	 * @param statusLine
	 *            A {@link String} containing an HTTP status line.
	 * @return The status code or {@code -1} if no valid status code has been found.
	 */
	public static int parseCode(final String statusLine)
	{
		/*
		 * A valid HTTP status line always looks like
		 * 
		 * HTTP-Version SP Status-Code SP Reason-Phrase CRLF
		 * 
		 * This method method scans for the two spaces and tries to convert the string in between to a number.
		 */

		if (statusLine == null)
		{
			return -1;
		}

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
						return status;
					}
				}
				catch (NumberFormatException e)
				{
					return -1;
				}
			}
		}
		return -1;
	}


	/**
	 * Returns the reason phrase for the given status code.
	 * <p>
	 * <strong>Note:</strong> the reason phrase doesn't contain the status code itself.
	 * </p>
	 * 
	 * @param status
	 *            The status code.
	 * @return The reason phrase or <code>null</code> if the status code is unknown.
	 */
	public static String getReason(int status)
	{
		return REASONS.get(status);
	}


	/**
	 * Returns an HTTP/1.1 status line for the given status code.
	 * 
	 * @param status
	 *            The status code.
	 * @return The reason phrase or <code>null</code> if the status code is unknown.
	 */
	public static String getStatusLine(int status)
	{
		String reason = REASONS.get(status);

		if (reason == null)
		{
			return null;
		}

		StringBuilder result = new StringBuilder(48);
		result.append("HTTP/1.1 ");
		result.append(status);
		result.append(' ');
		result.append(reason);
		return result.toString();
	}


	/**
	 * Private constructor - no instances allowed.
	 */
	private HttpStatus()
	{
	}
}
