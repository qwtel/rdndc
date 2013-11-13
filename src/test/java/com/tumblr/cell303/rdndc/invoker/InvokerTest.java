package com.tumblr.cell303.rdndc.invoker;

import com.tumblr.cell303.rdndc.invoker.helper.Formatter;
import com.tumblr.cell303.rdndc.invoker.helper.Formatter2;
import com.tumblr.cell303.rdndc.invoker.helper.Formatter3;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class InvokerTest {

    private static Formatter f;
    private static Formatter2 f2;
    private static Formatter2 f3;

    @BeforeClass
    public static void setUp() throws Exception {
        f = new Formatter();
        f2 = new Formatter2();
        f3 = new Formatter3();
    }

    @Test
    public void testFormat() throws Exception {
        for (int i = 0; i < 3; i++) {
            assertEquals("format 3 digits zip code", "00123", f.format("123"));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals("format 4 digits zip code", "01234", f.format("1234"));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals("do not format 5 digit zip code", "12345", f.format("12345"));
        }
    }

    @Test
    public void testOverriddenFormat() throws Exception {
        for (int i = 0; i < 2; i++) {
            assertEquals("format 3 digits zip code", "000123", f.format(6, "123"));
        }
        for (int i = 0; i < 2; i++) {
            assertEquals("format 4 digits zip code", "001234", f.format(6, "1234"));
        }
        for (int i = 0; i < 2; i++) {
            assertEquals("format 5 digits zip code", "012345", f.format(6, "12345"));
        }
        for (int i = 0; i < 2; i++) {
            assertEquals("do not format 6 digit zip code", "123456", f.format(6, "123456"));
        }
    }

    @Test
    public void testInheritedFormat() throws Exception {
        for (int i = 0; i < 3; i++) {
            assertEquals("format 3 digits zip code", "00123", f2.format("123"));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals("format 4 digits zip code", "01234", f2.format("1234"));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals("do not format 5 digit zip code", "12345", f2.format("12345"));
        }
    }

    @Test
    public void testFormatter3() throws Exception {
        assertEquals("format 3 digits zip code", "00123", f3.format("123"));
        assertEquals("format 4 digits zip code", "01234", f3.format("1234"));
        assertEquals("do not format 5 digit zip code", "12345", f3.format("12345"));
    }
}
