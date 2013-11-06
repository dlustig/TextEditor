import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


//This class uses the SingleInstance design,as there should only be 1 search box per instance of the program.

public class Import  extends JDialog/*, WindowListener*/{
	
	//Parent window objects
	private Window owner;
	private Document doc;

	//Swing components to be displayed
	private JTextComponent comp;
	private JTextField searcher;
		
	private static BufferedReader input;
	private static JFileChooser fc;
	
	
	public Import(Window window, JTextComponent pDoc) {
		
		comp = pDoc;
		doc = pDoc.getDocument();
		fc = new JFileChooser();
		
		
		int returnVal = fc.showOpenDialog(window);

        	if (returnVal == JFileChooser.APPROVE_OPTION) {
            		File file = fc.getSelectedFile();
            		//This is where a real application would open the file.
            		try
            			{
                			input = new BufferedReader(
                        		new InputStreamReader(
                			new FileInputStream(
                        		file)));
                			pDoc.read(input, "Loading File...");      
        			 }
            			catch(Exception e)
        			{       
                			e.printStackTrace();
        			} 
        	}
        	else {
        		System.out.println("Load not complete : CANCELED");
        	}
		
		
	}
}
