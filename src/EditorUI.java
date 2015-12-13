
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


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
   private static List<CustomeEventListener> listeners;


   public EditorUI(String name)
   {
    this.name = name ;
   listeners = new ArrayList<CustomeEventListener>(); 
   }

   public void addListener(CustomeEventListener toAdd) {
       listeners.add(toAdd);
   }

   public static void notifyAdd(News n) {
   
       for (CustomeEventListener hl : listeners)
           hl.newsAdded(n);
   }
   
   public static void notifyEdited(News n) {

       for (CustomeEventListener hl : listeners)
           hl.newsEdited(n);
   }
   
	private static void notifyFollow(String string) {
		for (CustomeEventListener hl : listeners)
	           hl.followRequest(string);
	}
	
	private static void notifyDeleted(News n) {
		for (CustomeEventListener hl : listeners)
	           hl.newsDeleted(n);
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
            	 Date d = selected.getDateCreated();
            	 
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
            	 if(!(field1.getText().isEmpty()) && !(field2.getText().isEmpty()) && !(field3.getText().isEmpty()) && !(field5.getText().isEmpty()) && !(field6.getText().isEmpty())){
            	 switch (result) {
            	     case JOptionPane.OK_OPTION:
                         News news = new News(field1.getText(),field2.getText(),field3.getText(),name,field5.getText(),field6.getText());
                         news.setDateCreated(d);
                         int index = listNews.getSelectedIndex();
                         ((DefaultListModel) listNews.getModel()).remove(index);
                         model.add(index, news);
                         notifyEdited(news);
                         break;
            	 }
        	 }
        	 else
        		 JOptionPane.showMessageDialog(null, "Not enought arguments!");
        	 }
        }
       });
     
     followButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 News selected = listNews.getSelectedValue();
        	 if(selected!=null)
        	 {
        	 notifyFollow("");	 
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
    	 field4.setText(name);
    	 field4.setEnabled(false);
    	 JTextField field5 = new JTextField(10);
    	 JTextField field6 = new JTextField(30);
    	 
    	 
    	 field4.setText(name);
    	 field4.setEnabled(false);
    	 
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
    	 
    	 System.out.println("*"+field1.getText().isEmpty()+"*");
    	 int result = JOptionPane.showConfirmDialog(null, fields, "Edit Topic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    	 if(!(field1.getText().isEmpty()) && !(field2.getText().isEmpty()) && !(field3.getText().isEmpty()) && !(field5.getText().isEmpty()) && !(field6.getText().isEmpty())){
    	 
    	 switch (result) {
    	     case JOptionPane.OK_OPTION:
                 News news = new News(field1.getText(),field2.getText(),field3.getText(),name,field5.getText(),field6.getText());
                 model.addElement(news);
                 notifyAdd(news);
                 break;
    	 }
    	 }
    	 else
    		 JOptionPane.showMessageDialog(null, "Not enought arguments!");
    	 
     }
   	 
  });

  deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
   	 News selected = listNews.getSelectedValue();
   	 if(selected!=null)
   	 {
     int index = listNews.getSelectedIndex();
   	 notifyDeleted(selected);
     ((DefaultListModel) listNews.getModel()).remove(index);
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



