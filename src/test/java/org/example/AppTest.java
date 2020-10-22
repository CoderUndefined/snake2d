package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void anotherTest() {
        Multiclass multiclass = new Multiclass(2);

        assertEquals(4, multiclass.doubleNum());
        assertNotEquals(3, multiclass.doubleNum());
        assertTrue(multiclass.b());


    }

    @Test
    public void passwordTest() {
        assertFalse(App.checkPassword("123456"));
        assertTrue(App.checkPassword("1234ABc!"));
        assertFalse(App.checkPassword(""));
        assertFalse(App.checkPassword("12345678"));
        assertFalse(App.checkPassword("1234abcd"));
        assertFalse(App.checkPassword("1234Abcd"));
        assertTrue(App.checkPassword("1234567890abcdefhgjkwrtwoitgjsdfg"));
        assertFalse(App.checkPassword("a"));
        assertTrue(App.checkPassword("1234ABCDef!!2ß0349582390ß5t6jewweweij"));

        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void lengthTest() {
        assertEquals(App.getBigChars("abc"),0);
        assertEquals(App.getBigChars("Abc"),1);
        assertEquals(App.getBigChars("ABC"),3);
    }

}



// First iteration
//    @Test
//    public void passwordTest() {
//        assertFalse(App.checkPassword("123456"));
//    }
//
// Second iteration
// @Test
// public void passwordTest() {
//     assertFalse(App.checkPassword("123456"));
//     assertTrue(App.checkPassword("1234ABc!"));
//     assertFalse(App.checkPassword())
// }
//
// Third iteration
//    @Test
//    public void passwordTest() {
//        assertFalse(App.checkPassword("123456"));
//        assertTrue(App.checkPassword("1234ABc!"));
//        assertFalse(App.checkPassword(""));
//        assertFalse(App.checkPassword("12345678"));
//        assertFalse(App.checkPassword("1234abcd"));
//        assertFalse(App.checkPassword("1234Abcd"));
//        assertTrue(App.checkPassword("1234567890abcdefhgjkwrtwoitgjsdfg"));
//    }