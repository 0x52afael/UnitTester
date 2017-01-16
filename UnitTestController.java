import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller Class for the MVC design. This Class uses The UnitTesterView
 * together with the UnitTester
 *
 * @author c14rdo
 */
public class UnitTestController {

	public UnitTester ut;
	public UnitTesterView utv;
	private String endOfTest;

	/**
	 * Creates the object of UnitTestController with initialized attributes and
	 * a custom made ActionListener to the Run Button of the View.
	 *
	 * @param ut
	 *            an initialized UnitTester class, which is saved into the
	 *            public attribute that saves the passed argument.
	 *
	 * @param utv
	 *            an initialized UnitTesterView class, which is saved into the
	 *            public attribute that saves the passed argument.
	 *
	 */
	public UnitTestController(UnitTester ut, UnitTesterView utv) {

		this.ut = ut;
		this.utv = utv;
		this.utv.addRunListener(new ButtonAction());

	}

	/**
	 * This method is used to reduce the code for the ButtonAction. The method
	 * formats the output if the class is a acceptable one, i.e it passed all
	 * the requirements of the criteria for UnitTester.
	 *
	 * Else the output is "Not an acceptable class!"
	 */
	private void updateView() {

		endOfTest = "---------------------------" + "---------------------------" + "---------------------------"
				+ "----------------------------------";

		if (ut.testIfAcceptableFile()) {
			String out = ut.invokeMethods();
			utv.updateTextArea(out);
			utv.updateTextArea(endOfTest);

		} else {
			utv.updateTextArea("Not an acceptable class!\n");
		}

	}

	/**
	 * A nestled class, ButtonAction which implements the ActionListener of the
	 * Run button.
	 *
	 * The Method actionPerformed is the method called when Run is pressed. It
	 * takes the input of the TextField in the GUI, and sends it into the
	 * TextArea formated with additional information. It then proceeds to
	 * Initialize the private attribute of UnitTester with the setClassToReflect
	 * method. If the length of the String returned from setClassToReflect is
	 * larger than 1, then an error has occurred.
	 */
	public class ButtonAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

	    String input = utv.getTextFieldOutPut();

	    utv.updateTextArea("\nTesting Class: ");
	    utv.updateTextArea(input + "\n");
	    
	    try{
	    	String outp = ut.setClassToReflect(input);

		    utv.updateTextArea(outp + "\n");

		    if (outp.length() == 1) {
			updateView();

		    }
		    utv.clearTextField();
	    }
	    catch(NoClassDefFoundError err){
	    	
	    	utv.updateTextArea("\nNon-Existing Class!");
	    }
	    
	}

	}

}
