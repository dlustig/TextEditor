import java.awt.event.*;                                                                                               
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

public class Text {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Text Editor");
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
     
    
    // add the action as a new anonymous object
    exit.addActionListener(new ActionListener( ) {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }   
    }); 
    
    file.add(exit);
    edit.add(cut);
    edit.add(copy);
    edit.add(paste);

    // add file to the menubar
    menubar.add(file);
    menubar.add(edit);

    // add the menubar to the window
    frame.setJMenuBar(menubar);
    
    // set up the text area
    JTextArea text = new JTextArea();
    text.setSize(500,700);
    
    text.setLineWrap(true);
    text.setEditable(true);
    text.setVisible(true);
    
    // add a scroll bar
    JScrollPane  scroll = new JScrollPane(text);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    frame.add(scroll);

    // launch the window
    frame.setVisible(true);
  }
}
