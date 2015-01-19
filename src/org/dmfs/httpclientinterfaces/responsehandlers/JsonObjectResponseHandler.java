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

package org.dmfs.httpclientinterfaces.responsehandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.IHttpResponse;
import org.dmfs.httpclientinterfaces.IHttpResponseEntity;
import org.dmfs.httpclientinterfaces.IResponseHandler;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link IResponseHandler} for JSON object responses.
 * 
 * <p>
 * <strong>Note:</strong> Don't use this response handler when you expect a JSON array to be the root element of the response.
 * </p>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class JsonObjectResponseHandler implements IResponseHandler<JSONObject>
{
	/**
	 * The buffer size to use when reading the response.
	 */
	private static final int BUFFER_SIZE = 1024 * 8;


	@Override
	public JSONObject handleResponse(IHttpResponse response) throws IOException, ProtocolException
	{
		IHttpResponseEntity contentEntity = response.getContentEntity();
		if (contentEntity == null)
		{
			throw new ProtocolException("did not receive any content");
		}

		InputStream input = contentEntity.getContentStream();

		if (input == null)
		{
			throw new ProtocolException("did not receive any content");
		}

		ContentType contentType = contentEntity.getContentType();
		if (contentType == null)
		{
			throw new ProtocolException("response didn't contain a content-type");
		}

		String charset = contentType.getCharset("UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(input, charset));

		StringBuilder responseBody = new StringBuilder(BUFFER_SIZE);
		char[] charArray = new char[BUFFER_SIZE];
		int readChars;
		while ((readChars = in.read(charArray)) > 0)
		{
			responseBody.append(charArray, 0, readChars);
		}

		in.close();

		try
		{
			return new JSONObject(responseBody.toString());
		}
		catch (JSONException e)
		{
			throw new ProtocolException("can not parse response JSON", e);
		}
	}
}
