package org.dmfs.httpclientinterfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * Test {@link HttpStatus}.
 * <p />
 * For now we only test a few of non-error conditions.
 * 
 * TODO: add more tests, also test error conditions
 */
public class HttpStatusTest
{

	@Test
	public void testFromStatusLine()
	{
		assertEquals(HttpStatus.OK, HttpStatus.fromStatusLine("HTTP/1.1 200 OK"));
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.fromStatusLine("HTTP/1.1 400 Bad Request"));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.fromStatusLine("HTTP/1.1 500 Internal Server Error"));
	}


	@Test
	public void testFromStatusCode()
	{
		assertEquals(HttpStatus.OK, HttpStatus.fromStatusCode(200));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.fromStatusCode(500));
	}


	@Test
	public void testReason()
	{
		assertEquals("OK", HttpStatus.OK.reason());
	}


	@Test
	public void testIsSuccess()
	{
		assertFalse(HttpStatus.CONTINUE.isSuccess());
		assertTrue(HttpStatus.OK.isSuccess());
		assertFalse(HttpStatus.SEE_OTHER.isSuccess());
		assertFalse(HttpStatus.UNAUTHORIZED.isSuccess());
		assertFalse(HttpStatus.BAD_GATEWAY.isSuccess());
	}


	@Test
	public void testIsInformational()
	{
		assertTrue(HttpStatus.CONTINUE.isInformational());
		assertFalse(HttpStatus.OK.isInformational());
		assertFalse(HttpStatus.SEE_OTHER.isInformational());
		assertFalse(HttpStatus.UNAUTHORIZED.isInformational());
		assertFalse(HttpStatus.BAD_GATEWAY.isInformational());
	}


	@Test
	public void testIsRedirect()
	{
		assertFalse(HttpStatus.CONTINUE.isRedirect());
		assertFalse(HttpStatus.OK.isRedirect());
		assertTrue(HttpStatus.SEE_OTHER.isRedirect());
		assertFalse(HttpStatus.UNAUTHORIZED.isRedirect());
		assertFalse(HttpStatus.BAD_GATEWAY.isRedirect());
	}


	@Test
	public void testIsClientError()
	{
		assertFalse(HttpStatus.CONTINUE.isClientError());
		assertFalse(HttpStatus.OK.isClientError());
		assertFalse(HttpStatus.SEE_OTHER.isClientError());
		assertTrue(HttpStatus.UNAUTHORIZED.isClientError());
		assertFalse(HttpStatus.BAD_GATEWAY.isClientError());
	}


	@Test
	public void testIsServerError()
	{
		assertFalse(HttpStatus.CONTINUE.isServerError());
		assertFalse(HttpStatus.OK.isServerError());
		assertFalse(HttpStatus.SEE_OTHER.isServerError());
		assertFalse(HttpStatus.UNAUTHORIZED.isServerError());
		assertTrue(HttpStatus.BAD_GATEWAY.isServerError());
	}
}
