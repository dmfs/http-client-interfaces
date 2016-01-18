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

package org.dmfs.httpclientinterfaces.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * An {@link InputStream} wrapper that tries to consume all unread content when it's about to be closed.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public final class ConsumingInputStream extends FilterInputStream
{

	private final static int BUFFER_SIZE = 16 * 1024;


	public ConsumingInputStream(InputStream input)
	{
		super(input);
	}


	@Override
	public void close() throws IOException
	{
		try
		{
			byte[] buffer = new byte[BUFFER_SIZE];
			while (read(buffer) >= 0)
			{
			}
		}
		finally
		{
			close();
		}
	}

}
