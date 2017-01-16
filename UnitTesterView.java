import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * This class is the view implementation of the MVC design pattern.
 *
 * UnitTesterView creates an GUI with the java swing library.
 *
 * @author c14rdo
 * @since 11-11-2015 //last update is the since date.
 */
public class UnitTesterView {

    private JFrame frame;
    private JButton entB;
    private JButton clearB;
    private JTextField tf;
    private JTextArea ta;
    private JButton help;

    /**
     * The constructor of UnitTesterView class. This constructor creates the
     * screen of the view in the MVC design pattern.
     */
    public UnitTesterView() {
	createScreen();
    }

    /**
     * This method will use the create panel methods to make a visible screen
     * with functioning parts that works together.
     */
    private void createScreen() {

    //frame creation
	frame = new JFrame("UnitTester");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(680, 480);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.setMinimumSize(new Dimension(480, 360));

	//Create panels
	JPanel up = createUpperPanel();
	JPanel lp = createLowerPanel();
	JPanel mp = createMiddlePanel();
	

	// Adds panels to the frame
	frame.add(up, BorderLayout.NORTH);
	frame.add(lp, BorderLayout.SOUTH);
	frame.add(mp, BorderLayout.CENTER);

	frame.pack();
    }

    /**
     * This method creates the lower panel which contains the clear button and
     * the Help button, which displays information of how use the program.
     *
     * @return The value returned is a JPanel which contains all the components
     *         that will be shown and used in the frame.
     */

    private JPanel createLowerPanel() {

	JPanel lPanel = new JPanel(new BorderLayout());

	clearB = new JButton("Clear");
	help = new JButton("Help");
	help.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		ta.append("To run this program you must first:\n"
			+ "1.  Type a Class "
			+ "into the text field and press run.\n"
			+ "After running the program, one can either\n"
			+ "choose to run another Class, or clear the "
			+ "text area.\n\n");

	    }
	});

	lPanel.add(help, BorderLayout.WEST);
	clearB.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent act) {

		ta.setText(null);
	    }
	});

	lPanel.add(clearB, BorderLayout.EAST);
	return lPanel;

    }

    /**
     * This method will create the middle panel which contains the text area and
     * the scroll.
     *
     * @return The value returned is a JPanel which contains all the components
     *         that will be shown and used in the frame.
     */
    private JPanel createMiddlePanel() {

	ta = new JTextArea(30, 60);
	ta.setLineWrap(true);
	ta.setBackground(Color.DARK_GRAY);
	ta.setEditable(false);
	ta.setForeground(Color.white);

	JPanel mp = new JPanel();
	mp.setLayout(new BorderLayout(100, 10));

	JScrollPane jsp = new JScrollPane(ta,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JLabel clearLabel = new JLabel("Need help?");
	mp.add(clearLabel, BorderLayout.SOUTH);
	mp.add(jsp, BorderLayout.CENTER);

	return mp;
    }

    /**
     * The method will create the upper panel for the GUI, which contains the
     * Run button and the Text Field.
     *
     * @return The value returned is a JPanel which contains all the components
     *         that will be shown and used in the frame.
     */
    private JPanel createUpperPanel() {

	JPanel uPanel = new JPanel();
	tf = new JTextField();

	uPanel.setBorder(BorderFactory.createTitledBorder("Enter the "
		+ "class to test here"));

	uPanel.setLayout(new BorderLayout());
	entB = new JButton("Run");

	frame.getRootPane().setDefaultButton(entB);

	uPanel.add(entB, BorderLayout.EAST);
	uPanel.add(tf, BorderLayout.CENTER);

	return uPanel;
    }

    /**
     * This method is used to set the ActionListener of the run button to Run
     * the model of the MVC. Which is UnitTester.
     *
     * @param action
     *            is an ActionListener which will be of a implemented type of
     *            ActionListener.
     */
    public void addRunListener(ActionListener action) {

	entB.addActionListener(action);
    }

    /**
     * This method will update the text are with the passed string data.
     *
     * @param data
     *            data is a String with any information which is to be sent to
     *            the JTextArea.
     */
    public void updateTextArea(String data) {

	ta.append(data);
    }

    /**
     * This method gets the text field input.
     *
     * @return The returned value is the string inputed into the text field of
     *         the GUI.
     *
     */
    public String getTextFieldOutPut() {
	return tf.getText();
    }

    /**
     * A simple method to clear the text field of the GUI.
     *
     */
    public void clearTextField() {
	tf.setText(null);
    }

}
