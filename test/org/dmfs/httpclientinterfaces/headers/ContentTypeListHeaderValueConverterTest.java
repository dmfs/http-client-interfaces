package org.dmfs.httpclientinterfaces.headers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.headers.impl.ContentTypeListHeaderValueConverter;
import org.junit.Test;


public class ContentTypeListHeaderValueConverterTest
{

	@Test
	public void test()
	{
		ContentTypeListHeaderValueConverter conv = new ContentTypeListHeaderValueConverter();

		assertEquals(Arrays.asList(new ContentType[] { new ContentType("test/test; param1=\"123,456\"; q=0.8"), }),
			conv.parseValue("test/test; param1=\"123,456\"; q=0.8"));

		assertTrue(new ContentType("test/test; param1=\"123,456\"; q=0.8").same(conv.parseValue("test/test; param1=\"123,456\"; q=0.8").get(0)));

		assertEquals(
			Arrays.asList(new ContentType[] { new ContentType("test/test; param1=\"123,456\"; q=0.8"),
				new ContentType("application/binary; type=\"aasds\"; q=0.1") }),
			conv.parseValue("test/test; param1=\"123,456\"; q=0.8, application/binary; type=\"aasds\"; q=0.1"));

		assertEquals(
			Arrays.asList(new ContentType[] { new ContentType("test/test; param1=\"123,456\"; q=0.8"),
				new ContentType("application/binary; type=\"aasds\"; q=0.1"), new ContentType("application/binary2") }),
			conv.parseValue("test/test; param1=\"123,456\"; q=0.8, application/binary; type=\"aasds\"; q=0.1, application/binary2"));

	}

}
