package org.jkiss.dbeaver.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jface.resource.DataFormatException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UIUtilsTest {

	
	@Test
	void testGetColorByRGBWithValid() {
		String rgbString = "254,254,254";
		SharedTextColors sharedTextColors = Mockito.mock(SharedTextColors.class);
		Device device = Display.getDefault().getCurrent();
		Color connectionColor = new Color(device, 254, 254, 254);
		Mockito.when(sharedTextColors.getColor(rgbString)).thenReturn(connectionColor);
		Color result = UIUtils.getColorByRGB(rgbString);
		assertEquals(connectionColor, result);
	}
	
	@Test
	void testGetColorByRGBWithNull() {
		String rgbString = "255,255,255";
		assertNull(UIUtils.getColorByRGB(rgbString));
	}
	
	@Test
	void testGetColorByRGBWithInvalid() {
		String rgbString = "254,254,-254";
		DataFormatException e = assertThrows(DataFormatException.class , () -> UIUtils.getColorByRGB(rgbString));
		String expected = "Argument not valid";
		assertEquals(expected , e.getMessage());
	}
	
	@Test
	void testgetSharedColorWithNull() {
		RGB rgb = null;
		assertNull(UIUtils.getSharedColor(rgb));
	}
	
	@Test
	void testgetSharedColorWithValid() {
		RGB rgb = new RGB(250,250,250);
		SharedTextColors sharedTextColors = Mockito.mock(SharedTextColors.class);
		Device device = Display.getDefault().getCurrent();
		Color color = new Color(device, 250, 250, 250);
		Mockito.when(sharedTextColors.getColor(rgb)).thenReturn(color);
		Color result = UIUtils.getSharedColor(rgb);
		assertEquals(color, result);
	}
	
	//Rossz valamiért
//	@Test()
//	void testgetSharedColorWithInvalid() {
//		RGB rgb = new RGB(250,-250,250);
//		IllegalArgumentException e = assertThrows(IllegalArgumentException.class , () -> UIUtils.getSharedColor(rgb));
//		//String expected = "Argument not valid";
//		IllegalArgumentException ex = new IllegalArgumentException("Argument not valid");
//		assertEquals(ex.getMessage() , e.getMessage());
//	}
	
	@Test
	void testgreyLevelEqual() {
		RGB rgb = new RGB(250,250,250);
		assertEquals(rgb.red, UIUtils.greyLevel(rgb));
	}
	
	@Test
	void testgreyLevelWithValid() {
		RGB rgb = new RGB(252,254,250);
		double result = (0.299 * rgb.red + 0.587 * rgb.green + 0.114 * rgb.blue + 0.5);
		assertEquals(result , UIUtils.greyLevel(rgb));
	}
	
	@Test
	void testgetContrastColorWithNull() {
		Color color = null;
		Color expected = new Color(null, 0, 0, 0);
		assertEquals(expected , UIUtils.getContrastColor(color));
	}
	
	@Test
	void testgetContrastColorWithValid() {
		Device device = Display.getDefault().getCurrent();
		Color color = new Color(device, 250, 250, 250);
		double luminance = 0.1;
		int c = (luminance > 0.5) ? 255 : 0;
		Color expected = new Color(null, c, c, c);
		assertEquals(expected , UIUtils.getContrastColor(color));
	}
	
	@Test
	void testgetContrastColorWithValid2() {
		Device device = Display.getDefault().getCurrent();
		Color color = new Color(device, 1, 1, 1);
		double luminance = 0.9;
		int c = (luminance > 0.5) ? 255 : 0;
		Color expected = new Color(null, c, c, c);
		assertEquals(expected , UIUtils.getContrastColor(color));
	}
	
}
