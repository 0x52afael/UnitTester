import javax.swing.SwingUtilities;

/**
 * @author c14rdo
 *
 */
public class MyUnitTester {

    public static void main(String[] argv) {

	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {

		UnitTesterView gui = new UnitTesterView();
		UnitTester test = new UnitTester();

		@SuppressWarnings("unused")
		UnitTestController utc = new UnitTestController(test, gui);

	    }
	});

    }
}
