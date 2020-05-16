package org.jkiss.dbeaver.ui.editors.sql.util;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SQLWordFinderTest {

	@Test
	void findWordWhenDocumentIsEmpty() {
		IDocument document = Mockito.mock(IDocument.class);
		Mockito.when(document.get()).thenReturn("");
		IRegion actualRegion = SQLWordFinder.findWord(document, 0);
		assertNull(actualRegion);
	}

	@Test
	void findWordWhenStartAndEndIsEquals() {
		IDocument document = Mockito.mock(IDocument.class);
		Mockito.when(document.get()).thenReturn("dummy text");
		IRegion actualRegion = SQLWordFinder.findWord(document, 5);
		Region expectedResult = new Region(5, 0);
		assertEquals(expectedResult, actualRegion);
	}

	@Test
	void findWordWhenStartAndOffsetIsNotEquals() {
		IDocument document = Mockito.mock(IDocument.class);
		Mockito.when(document.get()).thenReturn("dummy text");
		IRegion actualRegion = SQLWordFinder.findWord(document, 6);
		Region expectedResult = new Region(6, 4);
		assertEquals(expectedResult, actualRegion);
	}

	@Test
	void testGetWordStartOffsetWhenTextIsNull() {
		int actualResult = SQLWordFinder.getWordStartOffset(null, 0);
		int expectedResult = -1;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testGetWordStartOffsetWhenStartIndexIsInvalid() {
		int actualResult = SQLWordFinder.getWordStartOffset("dummytext", 99);
		int expectedResult = -1;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testGetWordStartOffsetWhenTextHasWhiteSpace() {
		int actualResult = SQLWordFinder.getWordStartOffset("dummy text", 9);
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void testGetWordStartOffsetWhenTextHasNoWhiteSpace() {
		int actualResult = SQLWordFinder.getWordStartOffset("dummytext", 8);
		int expectedResult = -1;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void getWordEndOffsetWhenTextHasWhiteSpace() {
		int actualResult = SQLWordFinder.getWordEndOffset("dummy text", 0);
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void getWordEndOffsetWhenTextHasNoWhiteSpace() {
		int actualResult = SQLWordFinder.getWordEndOffset("dummytext", 0);
		int expectedResult = 9;
		assertEquals(expectedResult, actualResult);
	}
}
