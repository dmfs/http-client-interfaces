package org.dmfs.httpclientinterfaces.headers;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import org.dmfs.httpclientinterfaces.ContentType;
import org.dmfs.httpclientinterfaces.headers.impl.LinkHeaderValueConverter;
import org.dmfs.httpclientinterfaces.headers.values.Link;
import org.junit.Test;


public class LinkHeaderValueConverterTest
{

	@Test
	public void test()
	{
		LinkHeaderValueConverter conv = new LinkHeaderValueConverter();

		Link l = conv.parseValue("<http://example.org/>;rel=\"start http://example.net/relation/other\" ; type=application/json; token=\"abcdef\"");

		assertEquals(URI.create("http://example.org/"), l.link());
		assertEquals(new HashSet<>(Arrays.asList(new String[] { "start", "http://example.net/relation/other" })), l.relationTypes());

		assertEquals(new ContentType("application/json"), l.mediaType());
		assertEquals(new HashSet<>(Arrays.asList(new String[] { "\"abcdef\"" })), l.rawParameter("token"));
	}

}
