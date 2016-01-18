package org.dmfs.httpclientinterfaces.headers;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.dmfs.httpclientinterfaces.headers.impl.SimpleHeaderType;
import org.dmfs.httpclientinterfaces.headers.impl.SingleHeaderList;
import org.dmfs.httpclientinterfaces.headers.impl.StringHeaderValueConverter;
import org.junit.Test;


public class SingleHeaderListTest
{

	@Test
	public void test()
	{
		HeaderType<String> type = new SimpleHeaderType<String>("bogus", new StringHeaderValueConverter());
		HeaderType<String> otherType = new SimpleHeaderType<String>("other", new StringHeaderValueConverter());
		Header<String> header = type.headerFromString("testing");
		Header<String> otherHeader = type.headerFromString("testing2");

		HeaderList headers = new SingleHeaderList(header);

		assertTrue(headers.contains(type));
		assertFalse(headers.contains(otherType));

		assertTrue(headers.contains(header));
		assertFalse(headers.contains(otherHeader));
		
		Iterator<Header<?>> iterator = headers.iterator();
		assertTrue(iterator.hasNext());
		assertSame(header, iterator.next());
		assertFalse(iterator.hasNext());
		
		assertFalse(headers.headersByType(otherType).hasNext());

		Iterator<Header<String>> iterator2 = headers.headersByType(type);
		assertTrue(iterator2.hasNext());
		assertSame(header, iterator2.next());
		assertFalse(iterator2.hasNext());
		
		assertEquals(1, headers.size());
	}

}
