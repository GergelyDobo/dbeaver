package org.jkiss.utils.xml;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class XMLUtilsTest {
	
	private static String validXMLString;
	private static String invalidXMLString;
	private static String validXMLWithChildString;
	private static String validXMLWithBodyString;
	private static String validXMLWithChildBodyString;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		validXMLString = "<test></test>";
		invalidXMLString = "<invalid></input>";
		validXMLWithChildString = "<test><teszt></teszt></test>";
		validXMLWithBodyString = "<test>teszt</test>";
		validXMLWithChildBodyString = "<test><teszt>mytest</teszt></test>";
	}

	@Test
	void testCreateDocument() {
		try {
		Document doc = XMLUtils.createDocument();
		assertNotNull(doc);
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testParseDocument() {
		InputStream stream = new ByteArrayInputStream(validXMLString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();
			assertEquals("test", rootElement.getNodeName());
			
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testParseDocumentExceptionThrow() {
		InputStream stream = new ByteArrayInputStream(invalidXMLString.getBytes());
		assertThrows(XMLException.class,() -> XMLUtils.parseDocument(stream));
	}
	
	@Test
	void testGetChildElementWithValidChild() {
		InputStream stream = new ByteArrayInputStream(validXMLWithChildString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();
			Element childElement = XMLUtils.getChildElement(rootElement, "teszt");
			assertNotNull(childElement);
			assertEquals("teszt", childElement.getNodeName());			
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testGetChildElementWithInvalidChild() {
		InputStream stream = new ByteArrayInputStream(validXMLString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();
			Element childElement = XMLUtils.getChildElement(rootElement, "teszt");
			assertNull(childElement);		
			
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testGetElementBodyWithBody() {
		InputStream stream = new ByteArrayInputStream(validXMLWithBodyString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();
			String rootElementBody = XMLUtils.getElementBody(rootElement);
			assertEquals("teszt", rootElementBody);
			
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testGetElementBodyWithoutBody() {
		InputStream stream = new ByteArrayInputStream(validXMLString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();
			String rootElementBody = XMLUtils.getElementBody(rootElement);
			assertEquals("", rootElementBody);
			
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}
	}
	
	@Test
	void testGetChildElementBodyWithBody() {
		InputStream stream = new ByteArrayInputStream(validXMLWithChildBodyString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();	
			String childElementBody = XMLUtils.getChildElementBody(rootElement, "teszt");
			assertEquals("mytest", childElementBody);	
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}

	}
	
	@Test
	void testGetChildElementBodyWithoutBody() {
		InputStream stream = new ByteArrayInputStream(validXMLWithChildString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();	
			String childElementBody = XMLUtils.getChildElementBody(rootElement, "teszt");
			assertEquals("", childElementBody);	
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}

	}
	
	@Test
	void testGetChildElementBodyWithoutChild() {
		InputStream stream = new ByteArrayInputStream(validXMLString.getBytes());
		try {
			Document doc = XMLUtils.parseDocument(stream);
			assertNotNull(doc);
			Element rootElement = doc.getDocumentElement();	
			String childElementBody = XMLUtils.getChildElementBody(rootElement, "teszt");
			assertNull(childElementBody);	
		} catch (XMLException e) {
			e.printStackTrace();
			fail("XMLException");
		}

	}

}
