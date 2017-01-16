import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitUnitTester {
	
	/**
	 * This test is to assert that initialization of
	 * a class works correctly.
	 */
    @Test
    public void testClassInitialization() {

	UnitTester ut = new UnitTester();
	ut.setClassToReflect("Test1");
	assertTrue(ut.cInitialized);
    }

    /**
     * This Test will test the following methods: checkConstructor() @return
     * boolean, true if there exists a constructor that has 0 parameters else
     * checkConstructor returns false, if there are no constructors with 0
     * parameters.
     *
     * classMethodTest() @return boolean, true if every method has no
     * parameters, else if a method has parameters it returns false.
     *
     * testIfInterface() @return boolean, true if the class which is reflected
     * implements the interface TestClass, else false.
     */
    @Test
    public void testAcceptableClass() {
	UnitTester ut = new UnitTester();
	ut.setClassToReflect("Test2");
	assertTrue(ut.testIfAcceptableFile());
    }

    /**
     * This Test will test the following methods: checkConstructor() @return
     * boolean, true if there exists a constructor that has 0 parameters else
     * checkConstructor returns false, if there are no constructors with 0
     * parameters.
     *
     * classMethodTest() @return boolean, true if every method has no
     * parameters, else if a method has parameters it returns false.
     *
     * testIfInterface() @return boolean, true if the class which is reflected
     * implements the interface TestClass, else false.
     */
    @Test
    public void testNotAcceptableClass() {
	UnitTester ut = new UnitTester();
	ut.setClassToReflect("java.lang.Boolean");
	assertFalse(ut.testIfAcceptableFile());

    }
    
    /**
     * This test is to assert that the output from the invokeMethod
     * is not null.
     */
    @Test
    public void testNonNullStringOutPut() {
	UnitTester ut = new UnitTester();
	ut.setClassToReflect("Test2");

	assertNotNull(ut.invokeMethods());

    }

}
