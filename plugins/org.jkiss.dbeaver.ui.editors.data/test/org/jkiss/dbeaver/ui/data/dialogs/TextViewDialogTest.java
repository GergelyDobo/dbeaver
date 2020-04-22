package org.jkiss.dbeaver.ui.data.dialogs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TextViewDialogTest {
	
	private static String invalidJSONString1;
	private static String invalidJSONString2;
	private static String invalidJSONString3;
	
	private static String validJSONString1;
	private static String validJSONString2;
	private static String validJSONString3;
	private static String validJSONString4;
	
	private static String indentedJSONString1;
	private static String indentedJSONString2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		invalidJSONString1 = "test";
		invalidJSONString2 = "{test";
		invalidJSONString3 = "{test: [1,3}";
		
		validJSONString1 = "{}";
		validJSONString2 = "{test:this}";
		validJSONString3 = "{test: ['3','4','With an array']}";
		validJSONString4 = "{test :{ nested: test}}";
		
		indentedJSONString1 = "{\n  \"test\": \"this\"\n}";
		indentedJSONString2 = "{\n  \"test\": {\n    \"nested\": \"test\"\n  }\n}" ;
	}


	@Test
	void testIsJSONWithInvalidString() {		
		assertFalse(TextViewDialog.isJSON(invalidJSONString1));
		assertFalse(TextViewDialog.isJSON(invalidJSONString2));
		assertFalse(TextViewDialog.isJSON(invalidJSONString3));
	}
	
	@Test
	void testIsJSONWithValidString() {
		assertTrue(TextViewDialog.isJSON(validJSONString1));
		assertTrue(TextViewDialog.isJSON(validJSONString2));
		assertTrue(TextViewDialog.isJSON(validJSONString3));
		assertTrue(TextViewDialog.isJSON(validJSONString4));
	}
	
	@Test
	void testParseToJsonWithInvalidString() {
		assertEquals("Not Valid JSON String", TextViewDialog.parseToJson(invalidJSONString1));
		assertEquals("Not Valid JSON String", TextViewDialog.parseToJson(invalidJSONString2));
		assertEquals("Not Valid JSON String", TextViewDialog.parseToJson(invalidJSONString3));

	}
	
	@Test
	void testParseToJsonWithValidString() {
		assertNotEquals(validJSONString2, TextViewDialog.parseToJson(validJSONString2));
		assertNotEquals(validJSONString3, TextViewDialog.parseToJson(validJSONString3));
		assertNotEquals(validJSONString4, TextViewDialog.parseToJson(validJSONString4));

	}
	
	@Test
	void testParseToJsonIndentationWithValidString() {
		assertEquals(validJSONString1, TextViewDialog.parseToJson(validJSONString1));
		assertEquals(indentedJSONString1, TextViewDialog.parseToJson(validJSONString2));
		assertEquals(indentedJSONString2, TextViewDialog.parseToJson(validJSONString4));
		
	}
	
	

}
