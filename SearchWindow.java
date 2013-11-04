import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;


//This class uses the SingleInstance design,as there should only be 1 search box per instance of the program.

public class searchWindow  extends JDialog implements ActionListener, DocumentListener/*, WindowListener*/{

	//Constants for the button texts
	final static String SEARCH = "Search";
	final static String NEXT = "Next";
	final static String PREVIOUS = "Prev";
	final static String CLOSE = "Close";
	final static String BLANK = "                                                                   ";


	//Parent window objects
	private Window owner;
	private Document doc;

	//Swing components to be displayed
	private JTextComponent comp;
	private JTextField searcher;
	private JButton search;
	private JButton next;
	private JButton close;
	private JLabel message;
	private JButton previous;//this currently is not added and has no functionality

	//other variables
	private String searchText = null;//The value of the current searchable text
	private boolean someFound;//used to flag whether a search yielded any results

	public searchWindow(Window window, JTextComponent pDoc) {

		//constructs window
		super(window, "Search Window");
		setSize(300,120);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//sets vars and initializes/adds components
		searchText = null;
		someFound = false;
		comp = pDoc;
		doc = pDoc.getDocument();

		setLayout(new FlowLayout());

		searcher = new JTextField(20);
		search = new JButton(SEARCH);
		next = new JButton(NEXT);
		previous = new JButton(PREVIOUS);
		close = new JButton(CLOSE);
		message = new JLabel(BLANK);

		add(searcher);
		add(search);
		add(next);
		//add(previous);
		add(close);
		add(message);

		//listeners for buttons and TextField
		search.addActionListener(this);
		next.addActionListener(this);
		previous.addActionListener(this);
		close.addActionListener(this);
		searcher.getDocument().addDocumentListener(this);


		//initial buton setup
		search.setEnabled(false);
		next.setEnabled(false);
		previous.setEnabled(false);

		setVisible(true);
	}

	//Performs appropriate action according to the pressed button
	//Note that the action command will be one of the declared constants
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(CLOSE)) {
			setVisible(false);
			dispose();
		}
		else if(e.getActionCommand().equals(SEARCH)) {
			searchText = searcher.getText();
			performSearch();
			updateButtons();
		}
		else if(e.getActionCommand().equals(NEXT)) {
			highlightNext();
		}
	}

	//documentListener methods
	public void changedUpdate(DocumentEvent d) {}

	public void insertUpdate(DocumentEvent d) {
		updateButtons();
	}
	public void removeUpdate(DocumentEvent d) {
		updateButtons();
	}


	private void performSearch(){

		String text = getDocumentText();
		String result = "The search text was not found";
		int found = 0;
		someFound = false;

		if(text != null && text.length() != 0) {
			boolean endFound = false;
			int index = 0;
			while(!endFound) {
				index = text.indexOf(searcher.getText(), index);
				if(index != -1) {
					index++;
					found++;
				}
				else {
					endFound = true;
				}
			}
			if(found > 0) {
				someFound = true;

				//sets result to found message, controlling for instance/instances if there are 1 or more occurances
				result = "Found " + found + " instance" + ((found == 1) ? "" : "s") + " of search text";
			}
		}

		highlightNext();

		message.setText(result);
	}

	private String getDocumentText() {

		String text;
		try {
			text = doc.getText(0, doc.getLength());
		}
		catch(BadLocationException e) {
			System.out.println("An unusual circumstance occurred");
			e.printStackTrace();
			return null;
		}
		return text;
	}

	private void highlightNext() {
		int location = comp.getCaretPosition();
		int size = searcher.getText().length();
		String docText = getDocumentText();

		//cover the fringe case of the cursor being in the middle of the search text
		//Move the cursor back n-1 spaces, where n is the length of the search term
		//if this is an invalid location, move it to the beginning of the text
		if(location >= size) {
			location -= size - 1;
		}
		else {
			location = 0;
		}

		location = docText.indexOf(searcher.getText(), location);


		//if the text does not exist after the carat, try from the beginning
		if(location == -1) {
			location = docText.indexOf(searcher.getText(), 0);
			message.setText("Wrapped around to beginning of document");
		}
		else {
			message.setText(BLANK);
		}
		//if we still did not find it (i.e. the user removed it since we last searched for it), inform the user
		//and do not move the carat
		if(location == -1) {
			message.setText("The text is not found");
			return;
		}


		int offset = searcher.getText().length();

		comp.setCaretPosition(location);
		comp.select(location, location + offset);

	}

	/*
	*Sets buttons as disabled or enabled based on a few rules:
	*Search enabled if and only if there is text in the searcher JTextField
	*Next and Previous enabled if this class has scanned the JTextArea for the text currently in the search box, and there were instances found
	*/
	private void updateButtons(){
		if(searcher.getText().equals("")) {
			search.setEnabled(false);
		}
		else {
			search.setEnabled(true);
		}

		if(searchText != null &&
						searchText.equals(searcher.getText()) &&
						someFound) {

			next.setEnabled(true);
			previous.setEnabled(true);
		}
		else {
			next.setEnabled(false);
			previous.setEnabled(false);
		}
	}

/*
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
		setVisible(false);
		this.dispose();
	}
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		this.dispose();
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
		setVisible(true);
	}
	public void windowIconified(WindowEvent e) {
		setVisible(false);
	}
	public void windowOpened(WindowEvent e) {
	}
*/
}
