package com.dumbster.smtp;

import org.junit.*;
import static org.junit.Assert.*;

public class MailMessageTest {

	private MailMessage message;

	@Before
	public void setup() {
		this.message = new MailMessage();
	}

	@Test
	public void testConstructor() {
		assertEquals("", message.getBody());
		assertFalse(message.getHeaderNames().hasNext());
		assertEquals("\n\n", message.toString());
	}

	@Test
	public void testAddHeader() {
		message.addHeader("foo", "bar1");
		assertEquals("bar1", message.getFirstHeaderValue("foo"));
		assertEquals("foo", message.getHeaderNames().next());
		assertEquals("foo: bar1\n\n\n", message.toString());
	}

	@Test
	public void testAddTwoSameHeaders() {
		message.addHeader("foo", "bar1");
		message.addHeader("foo", "bar2");
		assertEquals("bar1", message.getFirstHeaderValue("foo"));
		assertEquals("bar2", message.getHeaderValues("foo")[1]);
		assertEquals("foo: bar1\nfoo: bar2\n\n\n", message.toString());
	}

	@Test
	public void testAppendBody() {
		message.appendBody("Should I have shut the server down before disconnecting the power?");
		assertEquals(
				"\nShould I have shut the server down before disconnecting the power?\n",
				message.toString());
	}

	@Test
	public void headersAndBody() {
		message.addHeader("foo", "bar1");
		message.addHeader("foo", "bar2");
		message.appendBody("Should I have shut the server down before disconnecting the power?");
		assertEquals(
				"foo: bar1\nfoo: bar2\n\nShould I have shut the server down before disconnecting the power?\n",
				message.toString());
	}

}