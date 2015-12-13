
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class EditorUI
{

   private static String name ;
   public static boolean connect = false;
   
   public final static EditorUI jchat = new EditorUI(name);
   static DefaultListModel<News> model = new DefaultListModel<>();;

   public static JFrame mainFrame =null;
   public static JTextField statusColor = null;
   public static JTextField nameField = null;;
   public static JButton connectButton = null;
   private static JList<News> listNews;


   public EditorUI(String name)
   {
    this.name = name ;
	 
   }

private static JPanel initOptionsPane() {
	 JPanel pane = null;
     JPanel pane1 = null;
     ActionAdapter buttonListener = null;

     // Create an options pane
     JPanel optionsPane = new JPanel();
     
     optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.Y_AXIS));
     
     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
     ge.getAllFonts();

     Font font = new Font("Jokerman", Font.PLAIN, 35);
     JLabel textLabel = new JLabel("News Feed");
     textLabel.setFont(font);
     
     optionsPane.add(textLabel);
     
     JButton editButton = new JButton("Edit this topic...");
     JButton followButton = new JButton("Follow");
     JButton newButton = new JButton("New");
     JButton deleteButton = new JButton("Delete");
     
     editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 News selected = listNews.getSelectedValue();
        	 if(selected!=null)
        	 {
        		 JPanel fields = new JPanel(new GridLayout(7, 2));
        		 JLabel lb1 = new JLabel("Domain:");
        		 JLabel lb2 = new JLabel("Subdomain:");
        		 JLabel lb3 = new JLabel("Source:");
        		 JLabel lb4 = new JLabel("Author:");
        		 JLabel lb5 = new JLabel("Title:");
        		 JLabel lb6 = new JLabel("Content:");
        		 
            	 JTextField field1 = new JTextField(10);
            	 JTextField field2 = new JTextField(10);
            	 JTextField field3 = new JTextField(10);
            	 JTextField field4 = new JTextField(10);
            	 JTextField field5 = new JTextField(10);
            	 JTextField field6 = new JTextField(30);
            	 field1.setText(selected.getDomain());
            	 field2.setText(selected.getSubdomain());
            	 field3.setText(selected.getSource());
            	 field4.setText(name);
            	 field4.setEnabled(false);
            	 field5.setText(selected.getTitle());
            	 field6.setText(selected.getContent());
            	 
            	 
            	 fields.add(lb1);
            	 fields.add(field1);
            	 fields.add(lb2);
            	 fields.add(field2);
            	 fields.add(lb3);
            	 fields.add(field3);
            	 fields.add(lb4);
            	 fields.add(field4);
            	 fields.add(lb5);
            	 fields.add(field5);
            	 fields.add(lb6);
            	 fields.add(field6);
            	 int result = JOptionPane.showConfirmDialog(null, fields, "Edit Topic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            	 switch (result) {
            	     case JOptionPane.OK_OPTION:
                         News news = new News(field1.getText(),field2.getText(),field3.getText(),name,field4.getText(),field5.getText());
            	        model.addElement(news);
                         break;
            	 }
            	 
        	 }
        }
       });
     
     followButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 News selected = listNews.getSelectedValue();
        	 if(selected!=null)
        	 {
        	 JOptionPane.showMessageDialog(null, "Done!");
        	 }
        	 else
        		 JOptionPane.showMessageDialog(null, "Select something!");
        	 
         }
       });

newButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

    	
    	 JPanel fields = new JPanel(new GridLayout(7, 2));
    	 JLabel lb1 = new JLabel("Domain:");
    	 JLabel lb2 = new JLabel("Subdomain:");
    	 JLabel lb3 = new JLabel("Source:");
    	 JLabel lb4 = new JLabel("Author:");
    	 JLabel lb5 = new JLabel("Title:");
    	 JLabel lb6 = new JLabel("Content:");
    	 
    	 JTextField field1 = new JTextField(10);
    	 JTextField field2 = new JTextField(10);
    	 JTextField field3 = new JTextField(10);
    	 JTextField field4 = new JTextField(10);
    	 JTextField field5 = new JTextField(10);
    	 JTextField field6 = new JTextField(30);
    	 
    	 fields.add(lb1);
    	 fields.add(field1);
    	 fields.add(lb2);
    	 fields.add(field2);
    	 fields.add(lb3);
    	 fields.add(field3);
    	 fields.add(lb4);
    	 fields.add(field4);
    	 fields.add(lb5);
    	 fields.add(field5);
    	 fields.add(lb6);
    	 fields.add(field6);
    	 int result = JOptionPane.showConfirmDialog(null, fields, "Edit Topic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    	 switch (result) {
    	     case JOptionPane.OK_OPTION:
                 News news = new News(field1.getText(),field2.getText(),field3.getText(),name,field4.getText(),field5.getText());
                 model.addElement(news);
                 break;
    	 }
    	 
     }
   	 
  });

  deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
   	 News selected = listNews.getSelectedValue();
   	 if(selected!=null)
   	 {
   		 //more...
   	 JOptionPane.showMessageDialog(null, "Done!");
   	 }
   	 else
   		 JOptionPane.showMessageDialog(null, "Select something!");
   	 
    }
  });   
     

     JPanel panel = new JPanel(new BorderLayout());
     panel.setBorder(new EmptyBorder(10, 10, 10, 10));

     panel.add(new JScrollPane(listNews = createNewsList()),
             BorderLayout.CENTER);
     optionsPane.add(panel);
     
     pane = new JPanel();
     pane.setLayout(new GridLayout(0,2));
     pane.add(editButton, BorderLayout.WEST);
     pane.add(followButton, BorderLayout.EAST);
     pane.add(newButton);
     pane.add(deleteButton);
     
     optionsPane.add(pane);
     optionsPane.setPreferredSize(new Dimension(500,500));
     
     
     
     return optionsPane;
   }

public static JList<News> createNewsList() {
    // create List model
    // add item to model
    model.addElement(new News("A", "A", "cpp","A",  "cpp","a"));
    model.addElement(new News("A", "A", "cpp","A", "V","a"));
   
    // create JList with model
    JList<News> list = new JList<News>(model);
    list.setCellRenderer(new NewsRenderer());
    
    return list;
}


   private static void initGUI() 
   {
	      
	      mainFrame = new JFrame("Welcome");
	      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      //mainFrame.setBackground(new Color(0,0,0,65));
	      
	      JPanel mainPane = new JPanel(new BorderLayout());
	      JPanel optionsPane = initOptionsPane();
	      optionsPane.setBackground(new Color(0,0,0,65));
	      mainPane.add(optionsPane, BorderLayout.WEST);

	      mainFrame.add(mainPane);

	      mainFrame.setIconImage(new ImageIcon("Jface.png").getImage());
	      mainFrame.setResizable(false);
	      mainFrame.setLocation(200, 200);
	      mainFrame.pack();
	      mainFrame.setVisible(true);
      
      mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      mainFrame.addWindowListener( new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent we) {
              connect = false;
              System.exit(0);
          }
      } );
      
   }
   
  
  
   public  boolean getConnectionStatus() {
	return false;
      
   }


public void start() 
{
	 	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
		}
	    catch (Exception ex) {
	    }

      initGUI();

}

}



