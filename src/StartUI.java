import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class StartUI
{
   public static String name = "Enter name here ...";
   public static boolean isEditor = false;
   public static boolean connect = false;

   public static JFrame mainFrame = null;
   public static JTextField statusColor = null;
   public static JTextField nameField = null;;
   public static JRadioButton eOption = null;
   public static JRadioButton rOption = null;
   public static JButton connectButton = null;


   private static JPanel initOptionsPane() {
      JPanel pane = null;
      JPanel pane1 = null;
      ActionAdapter buttonListener = null;

      // Create an options pane
      JPanel optionsPane = new JPanel(new GridLayout(4, 1));
      
      pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
      pane1.add(new JLabel("Name:"));
      nameField = new JTextField(10);
      nameField.setText(name);
      nameField.setEnabled(false);
      nameField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                nameField.selectAll();
                name = nameField.getText();
               }
            });
      pane1.add(nameField);
      pane1.setBackground(new Color(0,0,0,65));
      optionsPane.add(pane1);


      // Host/guest option
      buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                  isEditor = e.getActionCommand().equals("editor");
                  if (isEditor) {
                      nameField.setEnabled(true);
                      nameField.setText(name);
                  }
                  else {
                	  nameField.setEnabled(false);
                      nameField.setText("Reader...");
                      name = "Reader";
                  }
            }
         };
      ButtonGroup bg = new ButtonGroup();   
      rOption = new JRadioButton("Reader   ", true);
      rOption.setMnemonic(KeyEvent.VK_H);
      rOption.setActionCommand("reader");
      rOption.addActionListener(buttonListener);
      rOption.setOpaque(false);
      eOption = new JRadioButton("Editor   ", false);
      eOption.setMnemonic(KeyEvent.VK_G);
      eOption.setActionCommand("editor");
      eOption.addActionListener(buttonListener);
      eOption.setOpaque(false);
      bg.add(rOption);
      bg.add(eOption);
      pane = new JPanel(new GridLayout(1, 2));
      pane.add(rOption);
      pane.add(eOption);
      pane.setOpaque(true);

      optionsPane.add(pane);

      JPanel buttonPane = new JPanel(new GridLayout(1, 2));
      buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
            	 if (e.getActionCommand().equals("connect")) {
                     connect = true ;
                  }
              
            }
         };
      connectButton = new JButton("Connect");
      connectButton.setMnemonic(KeyEvent.VK_C);
      connectButton.setActionCommand("connect");
      connectButton.addActionListener(buttonListener);
      connectButton.setEnabled(true);
      buttonPane.add(connectButton);
      optionsPane.add(buttonPane);
      
      return optionsPane;
   }

   private static void initGUI() throws IOException 
   {

      JPanel optionsPane = initOptionsPane();
      
      JPanel mainPane = new JPanel(new BorderLayout());
      optionsPane.setBackground(new Color(0,0,0,65));
      mainPane.add(optionsPane, BorderLayout.WEST);
      mainFrame = new JFrame("Welcome");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Adelina\\workspace\\NewsSistemPCBE\\Resources\\news.jpg")));
      mainFrame.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridwidth = GridBagConstraints.REMAINDER;   

      mainFrame.add(mainPane,gbc);

      mainFrame.setIconImage(new ImageIcon("Jface.png").getImage());
      mainFrame.setResizable(false);
      mainFrame.setLocation(200, 200);
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

	public static void main(String args[]) throws IOException 
	{
	
	      initGUI();
	
	      while(true)
	      {
	    	  try 
	    	  {
	              Thread.sleep(10);
	           }
	           catch (InterruptedException e) {}

	    	  if(connect)
	    	  {	 if(isEditor)
		    	  {	
	    		  new Editor(name);
		    		 break ;
		    	  }
		    	  else
		    	  {
		    		  new Reader();
		    		  break ;
		    	  }
	    	  }
	      }
	      mainFrame.setVisible(false);   
	      			
	}
}

//Action adapter for easy event-listener coding
class ActionAdapter implements ActionListener {
public void actionPerformed(ActionEvent e) {}
}


