package hu.qben.balinthirling.presenter;

import org.junit.Assert;
import org.junit.Test;

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
