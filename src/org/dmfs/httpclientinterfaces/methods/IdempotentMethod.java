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

package org.dmfs.httpclientinterfaces.methods;

import org.dmfs.httpclientinterfaces.HttpMethod;


/**
 * An idempotent {@link HttpMethod} that's not safe. For idempotent Methods that are also safe use {@link SafeMethod}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.2.1">RFC 7231, section 4.2.1</a>
 * @see <a href="https://tools.ietf.org/html/rfc7231#section-4.2.2">RFC 7231, section 4.2.2</a>
 */
public final class IdempotentMethod extends AbstractMethod
{
	public IdempotentMethod(final String verb)
	{
		super(verb);
	}


	@Override
	public boolean idempotent()
	{
		// idempotent methods are idempotent
		return true;
	}
}
