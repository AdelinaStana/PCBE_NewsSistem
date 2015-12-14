import java.io.*;
import java.net.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Editor implements javax.jms.MessageListener, Runnable, EditorEvents {

    private EditorUI edtUI ;
    private String name;
    private TopicSession pubSession;
    private TopicSession subSession;
    private TopicConnection connection;

    
    public Editor(String name) { 
    	 this.name = name;
//    	 new Thread( this ).start();
    	 run();
    }

	
	public void run()
	{ 
	
		edtUI = new EditorUI(name);
		edtUI.start();
		
		edtUI.addListener(this);

		InitialContext jndi;
		
		try {
			jndi = new InitialContext();
			
			
			// Look up a JMS connection factory
			TopicConnectionFactory conFactory =
					(TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");
			
			
			// Create a JMS connection
			TopicConnection connection =
					conFactory.createTopicConnection();	 
			
			
			// Intialize the Chat application
			set(connection, pubSession, subSession);
			
			connection.start();
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
		catch (NamingException e1) {
			e1.printStackTrace();
		}
	}

	/* Initialize the instance variables */
    public void set(TopicConnection con, TopicSession pubSess, TopicSession subSess) {
        this.connection = con;
        this.pubSession = pubSess;
        this.subSession = subSess;
    }



	public void newsAdded(News n) {
    	try{
    		TopicSession pubSession =
    				connection.createTopicSession(false,
    						Session.AUTO_ACKNOWLEDGE);
    		
			pubSession.createPublisher(pubSession.createTopic("id:" + n.getId()))
			.publish(pubSession.createObjectMessage(n));
    		pubSession.createPublisher(pubSession.createTopic("Author:" + n.getAuthor()))
			.publish(pubSession.createObjectMessage(n));	
			pubSession.createPublisher(pubSession.createTopic("Domain:" + n.getDomain()))
			.publish(pubSession.createObjectMessage(n));
			pubSession.createPublisher(pubSession.createTopic("Subdomain:" + n.getSubdomain()))
			.publish(pubSession.createObjectMessage(n));
			pubSession.createPublisher(pubSession.createTopic("Source:" + n.getSource()))
			.publish(pubSession.createObjectMessage(n));
			pubSession.createPublisher(pubSession.createTopic("Title:" + n.getTitle()))
			.publish(pubSession.createObjectMessage(n));    	
    	}
    	catch(JMSException e){
    		e.printStackTrace();
    	}
	}


	@Override
	public void newsDeleted(News n) {
		newsAdded(n);
	}


	@Override
	public void newsEdited(News n) {
		newsAdded(n);
	}

		
	public void followRequest(long id) {

		try {
			TopicSession subSession;
			subSession = connection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			
			TopicSubscriber subscriber = 
				subSession.createSubscriber(subSession.createTopic("seen:" + Long.valueOf(id)));
	
			// Set a JMS message listener
			subscriber.setMessageListener(this);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	

	public void onMessage(Message message) {
		try{
			String textMessage = (String) ((TextMessage) message).getText();
            
			String textMessageType = textMessage.split(":")[0];
			String textMessageValue = textMessage.split(":")[1];
			
			if(textMessageType == "seen"){
				System.out.println(textMessageValue);
			}
            // TODO Send to ui
        
		} catch (JMSException jmse){ jmse.printStackTrace( ); }
		
	}
}


