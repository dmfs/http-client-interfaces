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

package org.dmfs.httpclientinterfaces.requests;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.HttpMethod;
import org.dmfs.httpclientinterfaces.HttpStatus;
import org.dmfs.httpclientinterfaces.IHeaderEditor;
import org.dmfs.httpclientinterfaces.IHttpRequest;
import org.dmfs.httpclientinterfaces.IHttpRequestEntity;
import org.dmfs.httpclientinterfaces.IHttpResponse;
import org.dmfs.httpclientinterfaces.IHttpResponseEntity;
import org.dmfs.httpclientinterfaces.IResponseHandler;
import org.dmfs.httpclientinterfaces.Utils;
import org.dmfs.httpclientinterfaces.exceptions.ProtocolException;
import org.dmfs.httpclientinterfaces.responsehandlers.JsonObjectResponseHandler;
import org.json.JSONObject;


/**
 * An {@link IHttpRequest} that loads a JSON document using a GET request. This method won't send any headers. Override {@link #updateHeaders(IHeaderEditor)} if
 * you need to send any.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class GetJsonRequest implements IHttpRequest<JSONObject>
{
	private static final ContentType CONTENT_TYPE_APPLICATION_JSON = new ContentType("application/json");

	private final JsonObjectResponseHandler mResponseHandler = new JsonObjectResponseHandler();

	private final ContentType mExpectedContentType;


	/**
	 * Creates a GET request for a JSON document with the default content type "application/json".
	 */
	public GetJsonRequest()
	{
		mExpectedContentType = CONTENT_TYPE_APPLICATION_JSON;
	}


	/**
	 * Creates a GET request for a JSON document with a custom content type.
	 * 
	 * @param expectedContentType
	 */
	public GetJsonRequest(ContentType expectedContentType)
	{
		mExpectedContentType = expectedContentType;
	}


	@Override
	public HttpMethod getMethod()
	{
		return HttpMethod.GET;
	}


	@Override
	public void updateHeaders(IHeaderEditor headerEditor)
	{
		// don't need any headers
	}


	@Override
	public IHttpRequestEntity getRequestEntity()
	{
		// this is a GET request, no need for an entity
		return null;
	}


	@Override
	public IResponseHandler<JSONObject> getResponseHandler(IHttpResponse response) throws ProtocolException
	{
		if (response.getStatusCode() == HttpStatus.OK)
		{
			IHttpResponseEntity entity = response.getContentEntity();
			if (!Utils.verifyContentType(entity, mExpectedContentType))
			{
				Utils.consumeEntitySilently(entity);
				throw new ProtocolException("invalid content type: " + entity.getContentType());
			}

			return mResponseHandler;
		}
		// we don't handle this, the executor will take care of throwing an execption
		return null;
	}
}
