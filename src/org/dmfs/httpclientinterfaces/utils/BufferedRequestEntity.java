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

package org.dmfs.httpclientinterfaces.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.IHttpRequestEntity;


/**
 * A buffered request entity wrapper. It buffers the content of another {@link IHttpRequestEntity}, if necessary, to determine the content length before sending
 * it to the client. Implementations can use this to avoid chunked encoding if the server doesn't support it.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class BufferedRequestEntity implements IHttpRequestEntity
{
	/**
	 * The chunk size of our buffer. 16kB should hold most of the smaller requests.
	 */
	private final static int CHUNK_SIZE = 16 * 1024;

	/**
	 * The buffered entity.
	 */
	private IHttpRequestEntity mBufferedEntity;

	/**
	 * The buffer.
	 */
	private ByteArrayOutputStream mBuffer;

	/**
	 * The buffer size limit.
	 */
	private int mMaxBufferSize;


	public BufferedRequestEntity(IHttpRequestEntity bufferedEntity)
	{
		this(bufferedEntity, Integer.MAX_VALUE);
	}


	public BufferedRequestEntity(IHttpRequestEntity bufferedEntity, int maxBufferSize)
	{
		mBufferedEntity = bufferedEntity;
		mMaxBufferSize = maxBufferSize;
	}


	@Override
	public ContentType getContentType()
	{
		return mBufferedEntity.getContentType();
	}


	@Override
	public long getContentLength() throws IOException
	{
		long len = mBufferedEntity.getContentLength();
		if (len >= 0)
		{
			// no need to buffer
			return len;
		}

		if (mBuffer == null)
		{
			mBuffer = new ByteArrayOutputStream(CHUNK_SIZE, mMaxBufferSize);
			mBufferedEntity.writeContent(mBuffer);
		}

		return mBuffer.length();
	}


	@Override
	public void writeContent(OutputStream out) throws IOException
	{
		if (mBuffer == null || mBuffer.bufferOverflow())
		{
			// the buffer is empty or there was a buffer overflow
			mBufferedEntity.writeContent(out);
		}
		else
		{
			// we already have everything in the buffer, just write it
			mBuffer.writeTo(out);
		}
	}
}
