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
import java.util.ArrayList;
import java.util.List;


/**
 * An {@link OutputStream} that buffers all written content in byte arrays. In contrast to {@link java.io.ByteArrayOutputStream}, it allows to set a maximum
 * buffer size, switching to a count-only mode when the maximum buffer size will be exceeded.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ByteArrayOutputStream extends OutputStream
{
	/**
	 * The default minimum chunk size.
	 */
	public final static int MIN_CHUNK_SIZE = 8 * 1024;

	/**
	 * The list of chunks.
	 */
	private final List<byte[]> mChunkList = new ArrayList<byte[]>(16);

	/**
	 * The chunk we're currently writing to.
	 */
	private byte[] mCurrentChunk;

	/**
	 * The index of {@link #mCurrentChunk} in {@link #mChunkList}.
	 */
	private int mCurrentChunkIndex = -1;

	/**
	 * The minimal size of chunks.
	 */
	private final int mMinChunkSize;

	/**
	 * The next position to write to in {@link #mCurrentChunk}.
	 */
	private int mPosInChunk;

	/**
	 * The overall lenght of the written content.
	 */
	private int mLength;

	/**
	 * The overal buffer size limit.
	 */
	private final int mBufferSizeLimit;


	/**
	 * Creates an unlimited {@link ByteArrayOutputStream} using #MIN_CHUNK_SIZE as minimal chunk size.
	 */
	public ByteArrayOutputStream()
	{
		this(MIN_CHUNK_SIZE);
	}


	/**
	 * Creates an unlimited {@link ByteArrayOutputStream} using the given minimal chunk size.
	 * 
	 * @param minChunkSize
	 *            The minimal chunk size.
	 */
	public ByteArrayOutputStream(int minChunkSize)
	{
		this(minChunkSize, Integer.MAX_VALUE);
	}


	/**
	 * Creates a {@link ByteArrayOutputStream} that's limited in size using the given minimal chunk size. If the buffer size is exceeded this instance will stop
	 * buffering and only cound the number of bytes written.
	 * <p>
	 * Note that the actually used memory might be larger by the chunk size than the given buffer size.
	 * </p>
	 * 
	 * @param minChunkSize
	 *            The minimal chunk size.
	 * @param bufferSizeLimit
	 *            The maximum buffer size used by this {@link ByteArrayOutputStream}.
	 */
	public ByteArrayOutputStream(int minChunkSize, int bufferSizeLimit)
	{
		mMinChunkSize = minChunkSize;
		requestChunk(minChunkSize);
		mLength = 0;
		mBufferSizeLimit = bufferSizeLimit;
	}


	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		mLength += len;

		if (mLength > mBufferSizeLimit)
		{
			// we have a buffer overflow, stop buffering
			return;
		}

		byte[] currentChunk = mCurrentChunk;

		while (len > 0)
		{
			int count = Math.min(len, currentChunk.length - mPosInChunk);
			System.arraycopy(b, off, currentChunk, mPosInChunk, count);
			off += count;
			mPosInChunk += count;
			len -= count;
			if (len > 0)
			{
				currentChunk = requestChunk(len);
			}
		}
	}


	@Override
	public void write(int b) throws IOException
	{
		++mLength;

		if (mLength > mBufferSizeLimit)
		{
			// we have a buffer overflow, stop buffering
			return;
		}

		byte[] currentChunk = mCurrentChunk;
		if (mPosInChunk == currentChunk.length)
		{
			currentChunk = requestChunk(mMinChunkSize);
		}
		currentChunk[mPosInChunk++] = (byte) b;
	}


	/**
	 * Request a new current chunk.
	 * 
	 * @param requestedSize
	 *            The minimal size of the new buffer.
	 * @return The new or recycled buffer.
	 */
	private byte[] requestChunk(int requestedSize)
	{
		mPosInChunk = 0;
		if (mCurrentChunkIndex++ < mChunkList.size() - 1)
		{
			// there is one more unused buffer in the list, return it
			return mCurrentChunk = mChunkList.get(mCurrentChunkIndex);
		}

		mChunkList.add(mCurrentChunk = new byte[Math.max(requestedSize, mMinChunkSize)]);
		return mCurrentChunk;
	}


	/**
	 * Write the buffered content to the given {@link OutputStream}. Call {@link #bufferOverflow()} before calling this to check if all written content was
	 * buffered or not.
	 * 
	 * @param out
	 *            The {@link OutputStream} to write to.
	 * @throws IOException
	 * @throws {@link IllegalStateException} if there was a buffer overflow and not all content was buffered.
	 * 
	 */
	public void writeTo(OutputStream out) throws IOException
	{
		if (mLength > mBufferSizeLimit)
		{
			throw new IllegalStateException("Can't write after a buffer overflow.");
		}

		if (mLength == 0)
		{
			// nothing to do
			return;
		}

		// write all previous chunks
		int count = mCurrentChunkIndex;
		for (int i = 0; i < count; ++i)
		{
			out.write(mChunkList.get(i));
		}

		// write the current partial chunk
		out.write(mCurrentChunk, 0, mPosInChunk);
	}


	/**
	 * Clear all buffers.
	 */
	public void reset()
	{
		mCurrentChunkIndex = -1;
		mPosInChunk = 0;
		mCurrentChunk = requestChunk(mMinChunkSize);
		mLength = 0;
	}


	/**
	 * Reduce the memory allocated by this object by freeing all unused chunks.
	 */
	public void trim()
	{
		List<byte[]> chunkList = mChunkList;
		while (chunkList.size() - 1 > mCurrentChunkIndex)
		{
			chunkList.remove(chunkList.size() - 1);
		}
	}


	/**
	 * Get the overall lenght of the written content.
	 * 
	 * @return The number of bytes written to this {@link ByteArrayOutputStream}.
	 */
	public int length()
	{
		return mLength;
	}


	/**
	 * Returns whether there was a buffer overflow, i.e. the content written was larger than the maximum buffer size.
	 * 
	 * @return <code>true</code> if there was a buffer overflow and not all content was buffered, <code>false</code> otherwise.
	 */
	public boolean bufferOverflow()
	{
		return mLength > mBufferSizeLimit;
	}
}