import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class source
{
    private BufferedReader input;
    private JFileChooser fc;

    public source()
    {

        fc = new JFileChooser();        
    }

    private void displayGUI()
    {
    	
    	
        final JFrame frame = new JFrame("Import/Save file");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea tarea = new JTextArea(10, 10);      

        
        //load function
        JButton readButton = new JButton("LOAD FILE");
        readButton.addActionListener(new ActionListener()       
        {
            public void actionPerformed(ActionEvent ae)
            {
                int returnVal = fc.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    try
                    {
                        input = new BufferedReader(
                                new InputStreamReader(
                                new FileInputStream(
                                file)));
                        tarea.read(input, "Loading File...");      
                    }
                    catch(Exception e)
                    {       
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Load not complete : CANCELED");
                }
            }
        });
        
        
        //save function
        JButton saveButton = new JButton("SAVE FILE");
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            String save = tarea.getText();
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
        });

        frame.getContentPane().add(tarea, BorderLayout.CENTER);
        frame.getContentPane().add(readButton, BorderLayout.PAGE_END);
        frame.getContentPane().add(saveButton, BorderLayout.PAGE_START);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new source().displayGUI();
            }
        });
    }
}
