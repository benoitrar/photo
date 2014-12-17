package hu.qben.balinthirling.client.presenter;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class MenuPresenterTest {

	@Test
	public void testRegex() {
		// GIVEN
		String input = "123krumpli_meg_123_alma";
		String expectedOutput = "KRUMPLI MEG 123 ALMA";
		
		// WHEN
		String actualOutput = input.replace('_', ' ').replaceAll("^\\d+", "").toUpperCase();
		
		// THEN
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}
