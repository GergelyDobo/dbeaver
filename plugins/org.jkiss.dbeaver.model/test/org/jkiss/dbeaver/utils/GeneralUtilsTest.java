package org.jkiss.dbeaver.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.core.runtime.Platform;
import org.junit.jupiter.api.Test;

class GeneralUtilsTest {

	@Test
	void testConvertToString() {
		byte[] bytes = {(byte)67,(byte)72,(byte)69,(byte)69,(byte)83,(byte)69};
		int offset = 0;
		int length = bytes.length;
		assertEquals("CHEESE", GeneralUtils.convertToString(bytes,offset,length));
	}
	
	@Test
	void testConvertToString2() {
		byte[] bytes = {(byte)-60,(byte)67,(byte)72,(byte)69,(byte)69,(byte)83,(byte)69};
		int offset = 1;
		int length = bytes.length-2;
		assertEquals("CHEES", GeneralUtils.convertToString(bytes,offset,length));
	}

	@Test
	void testConvertToBytes() {
		byte[] bytes = {(byte)67,(byte)72,(byte)69,(byte)69,(byte)83,(byte)69};
		String strvalue = "CHEESE";
		assertArrayEquals(bytes, GeneralUtils.convertToBytes(strvalue));
	}
	
	@Test
	void testConvertToBytes2() {
		byte[] bytes = {(byte)60,(byte)67,(byte)72,(byte)69,(byte)69,(byte)83,(byte)69};
		String strvalue = "<CHEESE";
		assertArrayEquals(bytes, GeneralUtils.convertToBytes(strvalue));
	}

	@Test
	void testVariablePattern() {
		String name = "CHEESE";
		assertEquals("${CHEESE}",GeneralUtils.variablePattern(name));
	}
	
	@Test
	void testISVariablePattern() {
		String name = "${CHEESE}";
		assertTrue(GeneralUtils.isVariablePattern(name));
	}
	
	@Test
	void testISVariablePattern2() {
		String name = "$CHEESE}";
		assertFalse(GeneralUtils.isVariablePattern(name));
	}
	
	@Test
	void testMakeDisplayString() {
		assertEquals("",GeneralUtils.makeDisplayString(null));
	}
	
	@Test
	void testMakeDisplayString2() {
		String string = "CHEESE";
		assertEquals(string,GeneralUtils.makeDisplayString(string));
	}
	
	@Test
	void testGetRootCause() {
		Throwable e = new Throwable("Asd happened");
		assertEquals(e,GeneralUtils.makeDisplayString(e));
	}
	
	
	

}
