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
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;


//This class uses the SingleInstance design,as there should only be 1 search box per instance of the program.

public class Export extends JDialog{
        
        //Parent window objects
        private Window owner;
        private Document doc;

        //Swing components to be displayed
        private JTextComponent comp;
        private JTextField searcher;
                
        private static BufferedReader input;
        private static JFileChooser fc;
        
        
        public Export(Window window, JTextComponent pDoc) {
                
                comp = pDoc;
                doc = pDoc.getDocument();
                String save = pDoc.getText();
                JFileChooser chooser = new JFileChooser();
       
                 //chooser.setCurrentDirectory(new File("/home/me/Documents"));
         int retrival = chooser.showSaveDialog(null);
                if (retrival == JFileChooser.APPROVE_OPTION) {
                            try {
                                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                                fw.write(save.toString());
                         fw.close();
                            } catch (Exception ex) {
                         ex.printStackTrace();
                            }
                 }
                
        }
}
