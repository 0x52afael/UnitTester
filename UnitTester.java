/**
 * A Class that is used as a model that creates output for the view.
 * This class will reflect on a class and find and run the tests that the class
 * has.
 *
 * @author c14rdo
 *
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitTester {

    private Class<?> c = null;
    public boolean cInitialized;

    /**
     * This method will initialize the class to be reflected with the passing
     * String argument.
     *
     * @param cName
     *            A String representative of the Class name which is to be
     *            reflected.
     * @return Returns containing an error message if the class doesnt exist,
     *         else a new line.
     */
    public String setClassToReflect(String cName) {
	String err = new String();
	try {

	    c = Class.forName(cName);
	    cInitialized = true;
	    err = err + "\n";

	} catch (ClassNotFoundException e) {

	    err = err + "Non-Existing Class!";
	    cInitialized = false;
	}
	return err;
    }

    /**
     * We want to check the test class type if it is the test file that this
     * program can handle
     *
     *
     * @return type is boolean, which is true of class type is one which this
     *         program handles and false if not.
     */
    private boolean testIfInterface() {

	if (TestClass.class.isAssignableFrom(c)) {
	    return true;
	}

	return false;

    }

    /**
     * A simple name checking method that controlls the name of the passing
     * method.
     *
     * @param method
     *            A method to inspect the name of
     * @return True if the methods name starts with test, else false
     */
    private boolean checkIfNameStartsWithTest(Method method) {

	return method.getName().startsWith("test");
    }

    /**
     * A help function for testing if the classes method is of right type.
     *
     *
     * @param method
     *            the method from the class to inspect
     * @return a boolean, true if the method passes on the specific type that
     *         the program is made to test for.
     */

    private boolean checkMethodTypes(Method method) {

	if ((method.getReturnType().equals(Boolean.TYPE))) {
	    Class<?>[] param = method.getParameterTypes();

	    if (param.length == 0) {
		return true;
	    }
	}

	return false;
    }

    /**
     * Help method to find out number of Test methods.
     *
     * @return The number of Test Methods that exists within the class
     */
    private int nrOfTestMethods() {

	int nrOfMethods = 0;
	Method[] cMethods = c.getDeclaredMethods();
	nrOfMethods = cMethods.length;

	for (Method m : cMethods) {

	    if (checkIfNameStartsWithTest(m)) {
		nrOfMethods--;
	    }
	}
	return nrOfMethods;
    }

    /**
     * Help method to more easier find out if the class in inspection is a
     * correct one.
     *
     * @return true if all methods has 0 parameters, false if parameters are > 0
     */

    private boolean classMethodtest() {

	boolean correctFileTest = false;
	Class<?> test = c;
	int nrOfPassedTests = 0;
	int nrOfMethods = nrOfTestMethods();

	Method[] classMethods = test.getDeclaredMethods();

	for (Method method : classMethods) {

	    correctFileTest = checkMethodTypes(method);

	    if (correctFileTest == true) {
		nrOfPassedTests++;
		if (nrOfPassedTests == nrOfMethods) {
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Checks if the class has correct constructors, i.e the constructors takes
     * no parameters.
     *
     * @return true if the class has no parameter in its constructor, false
     *         else.
     */
    private boolean checkConstructor() {

	Constructor<?>[] construct = c.getConstructors();

	for (Constructor<?> construct2 : construct) {

	    Class<?>[] param = construct2.getParameterTypes();

	    if (param.length == 0) {
		return true;
	    }
	}
	return false;
    }

    /**
     * This method will find out if the class passed all the three tests so that
     * the program can run the main test.
     *
     * @return true if the class passed all the tests, else false.
     */
    public boolean testIfAcceptableFile() {

	int nrOfPassedTests = 0;
	if (checkConstructor() == true) {
	    nrOfPassedTests++;
	}
	if (classMethodtest() == true) {
	    nrOfPassedTests++;
	}
	if (testIfInterface() == true) {
	    nrOfPassedTests++;
	}

	if (nrOfPassedTests == 3) {
	    // the file passed the tests and we can now begin
	    // the testing.
	    return true;
	}

	return false;
    }

    /**
     * This method will run all the TestClass methods and save the Output of the
     * methods in A output String.
     *
     * @return A string containing the information of the run methods. The
     *         String will contain information such as, Method :Succeeded,
     *         Method :Failed or Method :Failed due to exception.
     */
    public String invokeMethods() {

	int succeded = 0;
	int failed = 0;
	int exceptionFail = 0;
	String outp = new String();

	try {
	    Method[] init = getSetUpandTearDown();
	    Method[] methods = c.getDeclaredMethods();
	    Object o = c.newInstance(); // An object of the class which is
					// sent into the program to test.

	    for (Method method : methods) {

		String mName = method.getName();
		if (mName.startsWith("test")) {

		    init[0].invoke(o);
		    try {
			boolean mReturn = (boolean) method.invoke(o);
			init[1].invoke(o);
			if (mReturn == true) {

			    outp = outp + (mName + ": SUCCESS\n");
			    succeded++;
			} else {
			    outp = outp + (mName + ": FAIL\n");
			    failed++;
			}

		    } catch (Exception e1) {

			exceptionFail++;
			outp = outp
				+ (mName + ": FAIL Generated a "
					+ e1.getCause() + '\n');

		    }
		}
	    }

	} catch (InstantiationException | IllegalAccessException e) {
	    outp = outp + (e + "failed to be made into object.\n");
	} catch (IllegalArgumentException e) {
	    // unneccesary but eclipse wants this. Already checked
	    // the method that it does not need parameters to function.

	} catch (InvocationTargetException e) {
	}

	outp = outp + (printResult(succeded, failed, exceptionFail));

	return outp;
    }

    /**
     * The method tests the class that setUp and tearDown are retrieved from
     *
     *
     * @return an method array with two methods. setUp and tearDown, which will
     *         initialize methods before invocation.
     */
    private Method[] getSetUpandTearDown() {

	Method[] methods = c.getDeclaredMethods();
	Method[] init = new Method[2];

	for (Method m : methods) {
	    String mName = m.getName();

	    if (mName.equals("setUp")) {

		init[0] = m;
	    } else if (mName.equals("tearDown")) {

		init[1] = m;
	    }

	}

	return init;
    }

    /**
     * This method saves the result of the invokeMethods in a string
     *
     * @param s
     *            - number of succeeded tests
     * @param f
     *            - number of failed tests
     * @param ef
     *            - number of tests failed by exception
     *
     * @return A string containing all the output of the method
     */
    private String printResult(int s, int f, int ef) {

	String outp = new String();
	outp = outp + ("\n");

	if (s > 1) {
	    outp = outp + (s + " tests succeded\n");
	} else if (s == 1) {
	    outp = outp + (s + " test succeded\n");
	}

	if (f > 1) {
	    outp = outp + (f + " tests failed\n");
	} else if (f == 1) {
	    outp = outp + (f + " test failed\n");
	}

	if (ef > 1) {

	    outp = outp + (ef + " tests failed because of an exception\n");
	} else if (ef == 1) {

	    outp = outp + (ef + " test failed because of an exception\n");
	}

	return outp;
    }

}
