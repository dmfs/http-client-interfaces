package org.dmfs.httpclientinterfaces.headers;

import static org.junit.Assert.*;

import org.dmfs.httpclientinterfaces.headers.impl.SimpleHeaderType;
import org.dmfs.httpclientinterfaces.headers.impl.StringHeaderValueConverter;
import org.junit.Test;

public class SimpleHeaderTypeTest
{

	@Test
	public void test()
	{
		HeaderType<String> type1 = new SimpleHeaderType<String>("test1", new StringHeaderValueConverter());
		HeaderType<String> type1b = new SimpleHeaderType<String>("test1", new StringHeaderValueConverter());
		HeaderType<String> type2 = new SimpleHeaderType<String>("test2", new StringHeaderValueConverter());
		
		assertEquals("test1", type1.headerName());
		assertEquals("test1", type1b.headerName());
		assertEquals("test2", type2.headerName());
		
		assertEquals(type1.hashCode(), type1b.hashCode());
		assertEquals(type1, type1b);
		
		assertNotEquals(type1, type2);
	}

}
