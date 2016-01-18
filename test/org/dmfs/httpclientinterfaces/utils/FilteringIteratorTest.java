package org.dmfs.httpclientinterfaces.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dmfs.httpclientinterfaces.utils.FilteringIterator;
import org.dmfs.httpclientinterfaces.utils.FilteringIterator.IteratorFilter;
import org.junit.Test;


public class FilteringIteratorTest
{

	@Test
	public void test()
	{
		List<String> emptyList = Collections.emptyList();
		List<String> list1 = Arrays.asList(new String[] { "1", "2", "3", "4", "3", "2", "1" });

		// test trivial cases with empty iterators
		assertIteratesSame(emptyList.iterator(), new FilteringIterator<String>(emptyList.iterator(), new IteratorFilter<String>()
		{
			@Override
			public boolean iterate(String element)
			{
				return true;
			}
		}));

		assertIteratesSame(emptyList.iterator(), new FilteringIterator<String>(emptyList.iterator(), new IteratorFilter<String>()
		{
			@Override
			public boolean iterate(String element)
			{
				return false;
			}
		}));

		// filter no elements
		assertIteratesSame(list1.iterator(), new FilteringIterator<String>(list1.iterator(), new IteratorFilter<String>()
		{
			@Override
			public boolean iterate(String element)
			{
				return true;
			}
		}));

		// filter all elements
		assertIteratesSame(emptyList.iterator(), new FilteringIterator<String>(list1.iterator(), new IteratorFilter<String>()
		{
			@Override
			public boolean iterate(String element)
			{
				return false;
			}
		}));

		// filter one element
		assertIteratesSame(Arrays.asList(new String[] { "1", "2", "3", "3", "2", "1" }).iterator(), new FilteringIterator<String>(list1.iterator(),
			new IteratorFilter<String>()
			{
				@Override
				public boolean iterate(String element)
				{
					return !"4".equals(element);
				}
			}));

		// filter some elements
		assertIteratesSame(Arrays.asList(new String[] { "2", "3", "3", "2" }).iterator(), new FilteringIterator<String>(list1.iterator(),
			new IteratorFilter<String>()
			{
				@Override
				public boolean iterate(String element)
				{
					return !"1".equals(element) && !"4".equals(element);
				}
			}));

		// filter most elements
		assertIteratesSame(Arrays.asList(new String[] { "4" }).iterator(), new FilteringIterator<String>(list1.iterator(), new IteratorFilter<String>()
		{
			@Override
			public boolean iterate(String element)
			{
				return "4".equals(element);
			}
		}));
	}


	/**
	 * Assert that two iterators return equal results.
	 * 
	 * @param iterator1
	 * @param iterator2
	 */
	private <E> void assertIteratesSame(Iterator<E> iterator1, Iterator<E> iterator2)
	{
		while (iterator1.hasNext())
		{
			assertEquals(iterator1.next(), iterator2.next());
		}

		assertFalse(iterator2.hasNext());
	}
}
