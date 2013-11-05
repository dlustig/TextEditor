import java.awt.event.*;                                                                                               
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
 
public class Text {
	public static BufferedReader input;
	public static JFileChooser fc;

  	public static void main(String[] args) {
	  
		fc = new JFileChooser();
	  
    		final JFrame frame = new JFrame("Text Editor");
    		frame.setSize(600,800);
      
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the menu bar
    		JMenuBar menubar = new JMenuBar( );

		// add the file menu
    		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		// add exit menu item
		JMenuItem exit = new JMenuItem("Quit", null);
		exit.setMnemonic(KeyEvent.VK_Q);
		exit.setToolTipText("Quit the program");
    
		// add the exit action as a new anonymous object
		exit.addActionListener(new ActionListener( ) {
      			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			 }   
		}); 
    
    		// add save menu item
    		JMenuItem save = new JMenuItem("Save", null);
    		save.setMnemonic(KeyEvent.VK_S);
    		save.setToolTipText("Save the program");
    
    		// add import menu item
		JMenuItem importFile = new JMenuItem("Import", null);
		importFile.setMnemonic(KeyEvent.VK_I);
		importFile.setToolTipText("Import file into program");
    
    

		// add the edit menu
		JMenu edit = new JMenu("Edit");
    		edit.setMnemonic(KeyEvent.VK_E);
    
		// add edit menu items
    		JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
		cut.setText("Cut");
		cut.setMnemonic(KeyEvent.VK_X);
    		cut.setToolTipText("Cut text");
  
		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		copy.setMnemonic(KeyEvent.VK_C);
		copy.setToolTipText("Copy text");

    		JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		paste.setText("Paste");
		paste.setMnemonic(KeyEvent.VK_P);
    		paste.setToolTipText("Paste text");
    
    
 		// add undo menu item
		JMenuItem undo = new JMenuItem("Undo", null);
		undo.setMnemonic(KeyEvent.VK_Z);
		undo.setToolTipText("Undo your last action");
  
    
		// add redo menu item
		JMenuItem redo = new JMenuItem("Redo", null);
		redo.setMnemonic(KeyEvent.VK_Y);
		redo.setToolTipText("Redo your last action");
		     
		// add search menu item
		JMenuItem search = new JMenuItem("Search", null);
		search.setMnemonic(KeyEvent.VK_F);
		search.setToolTipText("Search through text");
		  
		//add the menu items to the menu
		file.add(exit);
		file.add(save);
		file.add(importFile);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(undo);
		edit.add(redo);
		edit.add(search);
		    
		    
		
		// add file to the menubar
		menubar.add(file);
		menubar.add(edit);
		
		// add the menubar to the window
		frame.setJMenuBar(menubar);
		    
		// set up the text area
		final JTextArea text = new JTextArea();
		text.setSize(500,700);
		text.setLineWrap(true);
		text.setEditable(true);
		text.setVisible(true);
		    
		final EditList edits = new EditList(text);
		    
		    
		    
		// add a scroll bar
		JScrollPane  scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll);
		    
		//load action listener
		importFile.addActionListener(new ActionListener()       
		{
			public void actionPerformed(ActionEvent ae)
		        {
		        	Import Importing = new Import(frame, text);
		        }
		});
		    
		    
		//save action listener
		save.addActionListener(new ActionListener()
		{
		        public void actionPerformed(ActionEvent ae)
		        {
		        	
		        	Export Exporting = new Export(frame, text);
		
		        }
		});
		    
		    
		// add the undo action as a new anonymous object
		undo.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
		    		edits.undo();
		        }   
		}); 
		    
		// add the redo action as a new anonymous object
		redo.addActionListener(new ActionListener( ) {
		        public void actionPerformed(ActionEvent e) {
				edits.redo();
		        }   
		}); 
		    
		// add the search action as a new anonymous object
		search.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				final searchWindow searches = new searchWindow(frame, text);
		        }   
		}); 
		    
		    
    		// launch the window
    		frame.setVisible(true);
    
  	}
}
