package org.dmfs.httpclientinterfaces.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;

import org.dmfs.httpclientinterfaces.utils.ByteArrayOutputStream;
import org.junit.Test;


/**
 * Test {@link ByteArrayOutputStream}.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 * 
 */
public class ByteArrayOutputStreamTest
{

	private class VerifyingOutputStream extends OutputStream
	{
		int count = 0;


		@Override
		public void write(int b) throws IOException
		{
			count++;
			assertEquals((byte) count, b);
		}
	}


	/**
	 * Fill the buffer with chunks of various sizes and set a size limit.
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException
	{
		ByteArrayOutputStream testStream = new ByteArrayOutputStream(3, 30);

		// write chunks of different size,
		testStream.write(new byte[] { 1, 2, 3 });
		testStream.write(new byte[] { 4, 5, 6 });

		// write chunks that are smaller than the min chunk size
		testStream.write(new byte[] { 7 });
		testStream.write(new byte[] { 8 });
		testStream.write(new byte[] { 9 });
		testStream.write(new byte[] { 10 });

		// write single bytes
		testStream.write(11);
		testStream.write(12);
		testStream.write(13);
		testStream.write(14);

		// at this point the last chunk is not filled completely, so the first byte of the next buffer should go the last chunk
		testStream.write(new byte[] { 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 });
		testStream.write(new byte[] { 25, 26, 27, 28, 29 });

		// verify length and buffer content
		assertEquals(29, testStream.length());
		assertFalse(testStream.bufferOverflow());
		VerifyingOutputStream verifier = new VerifyingOutputStream();
		testStream.writeTo(verifier);

		// ensure everything has been written to the verifier stream.
		assertEquals(29, verifier.count);

		// write some more bytes to let the buffer overflow
		testStream.write(new byte[] { 30, 31, 32, 33, 34, 35 });

		assertTrue(testStream.bufferOverflow());

		assertEquals(35, testStream.length());

		// start over again, but with different chunk sizes
		testStream.reset();

		testStream.write(new byte[] { 1, 2, 3, 4 });
		testStream.write(new byte[] { 5, 6, 7 });
		testStream.write(new byte[] { 8 });
		testStream.write(new byte[] { 9 });
		testStream.write(new byte[] { 10 });
		testStream.write(new byte[] { 11 });
		testStream.write(12);
		testStream.write(13);
		testStream.write(14);
		testStream.write(15);
		testStream.write(16);
		testStream.write(17);
		testStream.write(new byte[] { 18, 19, 20, 21, 22, 23, 24, 25 });
		testStream.write(new byte[] { 26, 27, 28, 29 });

		assertEquals(29, testStream.length());
		assertFalse(testStream.bufferOverflow());

		verifier = new VerifyingOutputStream();
		testStream.writeTo(verifier);

		assertEquals(29, verifier.count);

		testStream.write(new byte[] { 30, 31, 32, 33, 34, 35 });

		assertTrue(testStream.bufferOverflow());

		assertEquals(35, testStream.length());

		testStream.close();
	}


	/**
	 * Write blocks that are smaller than the minimal chunk size.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSmallerChunkSize() throws IOException
	{
		ByteArrayOutputStream testStream = new ByteArrayOutputStream(1024);
		for (int i = 0; i < 1024; ++i)
		{
			testStream.write(getTestBuffer(i * 1023 + 1, 1023));
		}
		VerifyingOutputStream verifyer = new VerifyingOutputStream();
		testStream.writeTo(verifyer);
		assertEquals(1023 * 1024, verifyer.count);
		testStream.close();
	}


	/**
	 * Write blocks that are larger than the minimal chunk size.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLargerChunkSize() throws IOException
	{
		ByteArrayOutputStream testStream = new ByteArrayOutputStream(1024);
		for (int i = 0; i < 1024; ++i)
		{
			testStream.write(getTestBuffer(i * 1025 + 1, 1025));
		}
		VerifyingOutputStream verifyer = new VerifyingOutputStream();
		testStream.writeTo(verifyer);
		assertEquals(1025 * 1024, verifyer.count);
		testStream.close();
	}


	/**
	 * Fill the buffer byte by byte.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSingleByte() throws IOException
	{
		ByteArrayOutputStream testStream = new ByteArrayOutputStream(1024);
		for (int i = 0; i < 1024 * 1024; ++i)
		{
			testStream.write((byte) (i + 1));
		}
		VerifyingOutputStream verifyer = new VerifyingOutputStream();
		testStream.writeTo(verifyer);
		assertEquals(1024 * 1024, verifyer.count);
		testStream.close();
	}


	private byte[] getTestBuffer(int offset, int len)
	{
		byte[] result = new byte[len];
		for (int i = 0; i < len; ++i)
		{
			result[i] = (byte) (offset + i);
		}
		return result;
	}
}
