package org.dmfs.httpclientinterfaces.headers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dmfs.httpclientinterfaces.headers.impl.EmptyHeaderList;
import org.dmfs.httpclientinterfaces.headers.impl.SimpleHeaderType;
import org.dmfs.httpclientinterfaces.headers.impl.StringHeaderValueConverter;
import org.junit.Test;


public class EmptyHeaderListTest
{

	@Test
	public void test()
	{
		HeaderList list = EmptyHeaderList.INSTANCE;

		HeaderType<String> type = new SimpleHeaderType<String>("bogus", new StringHeaderValueConverter());

		Header<String> header = type.headerFromString("testing");

		// the header list should be really empty
		assertEquals(0, list.size());
		assertFalse(list.iterator().hasNext());
		assertFalse(list.headersByType(type).hasNext());
		assertFalse(list.contains(header));
		assertFalse(list.contains(type));

		assertEquals(0, list.remove(type).size());

		// appending a single header should return this header only.
		assertEquals(1, list.append(header).size());
		assertTrue(list.append(header).contains(header));
		assertEquals(header, list.append(header).iterator().next());

		// the original list should still be empty
		assertEquals(0, list.size());
	}

}
