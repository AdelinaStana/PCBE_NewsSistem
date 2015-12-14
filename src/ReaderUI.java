
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

public class ReaderUI
{

   private static String name ;
   public static boolean connect = false;
   
   public final static ReaderUI jchat = new ReaderUI();

   public static JFrame mainFrame =null;
   public static JTextField statusColor = null;
   public static JTextField nameField = null;;
   public static JButton connectButton = null;
   private static JList<News> listNews;
   private static List<ReaderEvents> listeners;

   static DefaultListModel<News> model = new DefaultListModel<>();

   public ReaderUI()
   {

	   listeners = new ArrayList<ReaderEvents>(); 
   }

   public void addListener(ReaderEvents toAdd) {
       listeners.add(toAdd);
   }

   public static void notifySeen(News n) {
   
       for (ReaderEvents hl : listeners)
           hl.seen(n);
   }
   
   public static void notifySubscribe(String s) {

       for (ReaderEvents hl : listeners)
           hl.subscribeRequest(s);
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
     
     JButton readButton = new JButton("Read this topic...");
     JButton followButton = new JButton("Settings");
     
     readButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 News selected = listNews.getSelectedValue();
        	 if(selected!=null)
        	 {ImageIcon image = new ImageIcon("C:\\Users\\Adelina\\workspace\\NewsSistemPCBE\\Resources\\icon.jpg");
        	 Image img = image.getImage();
        	 Image newimg = img.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        	 image = new ImageIcon(newimg); 
        	 JOptionPane.showMessageDialog(new JFrame(), selected.getContent(), selected.getTitle(), JOptionPane.INFORMATION_MESSAGE, image);
        	 notifySeen(selected);
        	 }
        }
       });
     
     
     followButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           
        	 JPanel fields = new JPanel(new GridLayout(2, 1));
        	 JTextField field = new JTextField(10);
        	 JComboBox<String> comboBox = new JComboBox<>(new String[]{"Domain", "Subdomain", "Source","Author"});

        	 fields.add(field);
        	 fields.add(comboBox);

        	 int result = JOptionPane.showConfirmDialog(null, fields, "Follow Topic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        	 switch (result) {
        	     case JOptionPane.OK_OPTION:
        	         notifySubscribe(comboBox.getSelectedItem()+":"+field.getText());
        	         break;
        	 }
        	 
         }
       });
     
     JPanel panel = new JPanel(new BorderLayout());
     panel.setBorder(new EmptyBorder(10, 10, 10, 10));

     panel.add(new JScrollPane(listNews = createNewsList()),
             BorderLayout.CENTER);
     optionsPane.add(panel);
     
     pane = new JPanel();
     pane.setLayout(new GridLayout(0,2));
     pane.add(readButton, BorderLayout.WEST);
     pane.add(followButton, BorderLayout.EAST);
     
     optionsPane.add(pane);
     optionsPane.setPreferredSize(new Dimension(500,500));
     
     
     
     return optionsPane;
   }

private static JList<News> createNewsList() {

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

public void process(News newsItem)
{

	if(newsItem.getDeleted())
	{
		int n = model.getSize();
		for(int i=0;i<n;i++)
		{
			News news =  model.getElementAt(i);
			if(newsItem.getId()==news.getId())
			{
				model.remove(i);
			}
		}
	}
	else
	{
		int n = model.getSize();
		boolean found = false;
		int index = 0 ;
		for(int i=0;i<n;i++)
		{
			News news =  model.getElementAt(i);
			if(newsItem.getId()==news.getId())
			{
				found = true;
				index = i;
			}
		}
		
		if(found)
		{
			model.remove(index);
			model.insertElementAt(newsItem, index);
		}
		else
		{
			model.add(model.getSize(), newsItem);
		}
			
	}
	
}

}



