import java.io.*;
import java.net.*;

import javax.jms.*;
import javax.naming.*;

import java.util.Properties;

public class Reader implements javax.jms.MessageListener , Runnable , ReaderEvents{

    private ReaderUI rdrUI ;
    private TopicSession pubSession;
    private TopicSession subSession;
    private TopicPublisher publisher;
    private TopicConnection connection;

    
    public Reader() { 
    	 new Thread( this ).start();
    }

	public void run()
	{ 
	
		ReaderUI rdrUI = new ReaderUI();
		rdrUI.start();

		rdrUI.addListener(this);
		Properties env = new Properties();
        // ... specify the JNDI properties specific to the vendor
		
        InitialContext jndi;
        
		try {
			jndi = new InitialContext();
	
	
	        // Look up a JMS connection factory
	        TopicConnectionFactory conFactory =
	        (TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");
	
	        
	        // Create a JMS connection
	        TopicConnection connection =
	        conFactory.createTopicConnection();	 
	
	        // Create two JMS session objects
	        TopicSession pubSession =
	        connection.createTopicSession(false,
	                                      Session.AUTO_ACKNOWLEDGE);
	        TopicSession subSession =
	        connection.createTopicSession(false,
	                                      Session.AUTO_ACKNOWLEDGE);
			
	        // Create a JMS publisher and subscriber
	//        TopicPublisher publisher = 
	//            pubSession.createPublisher(chatTopic);
	//        TopicSubscriber subscriber = 
	//            subSession.createSubscriber(chatTopic);
	
	        // Set a JMS message listener
	       // subscriber.setMessageListener(this);
	
	        // Intialize the Chat application
	        set(connection, pubSession, subSession, publisher);
			
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
    public void set(TopicConnection con, TopicSession pubSess,
                    TopicSession subSess, TopicPublisher pub) {
        this.connection = con;
        this.pubSession = pubSess;
        this.subSession = subSess;
        this.publisher = pub;
    }

	public void onMessage(Message message) {
		try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText( );
            System.out.println(text);
        } catch (JMSException jmse){ jmse.printStackTrace( ); }
		
	}
	
	/* Close the JMS connection */
    public void close( ) throws JMSException {
        connection.close( );
    }
    
    /* Create and send message using topic publisher */
    protected void writeMessage(String text) throws JMSException {
        TextMessage message = pubSession.createTextMessage( );
        message.setText(text);
        publisher.publish(message);
    }

	@Override
	public void seen(News n) {
	System.out.println("S");
		
	}

	@Override
	public void subscribeRequest(String s) {
		// stringul vine gata creat de tip "x : y"
		System.out.println("R");
	}
}


