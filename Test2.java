public class Test2 implements TestClass {
    private MyInt myInt;

    public Test2() {
    }

    public void setUp() {
	myInt = new MyInt();
    }

    public void tearDown() {
	myInt = null;
    }

    // Test that should succeed
    public boolean testInitialisation() {
	return myInt.value() == 0;
    }

    // Test that should succeed
    public boolean testIncrementTo10() {
	for(int i = 0; i < 10; i++){

	    myInt.increment();
	}

	return myInt.value() == 10;

    }

    // Test that should succeed
    public boolean testDecrement() {
	myInt.increment();
	myInt.decrement();
	return myInt.value() == 0;
    }
    //should always succeed
    public boolean testPass(){
	return true;
    }

    // Test that should fail
    public boolean testFailingByException() {
	myInt = null;
	myInt.decrement();
	return true;

    }

    public boolean testFail(){
	return false;
    }

    // Test that should fail
    public boolean testFailing() {
	return false;

    }

}
