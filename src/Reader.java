import java.io.*;
import java.net.*;

import javax.jms.*;
import javax.naming.*;

import java.util.Properties;

public class Reader implements javax.jms.MessageListener , Runnable , ReaderEvents{

    private ReaderUI rdrUI ;
    private TopicSession pubSession;
    private TopicSession subSession;
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

	public void onMessage(Message message) {
		try {
			ObjectMessage messageObj = (ObjectMessage) message;
			News NewsItem = (News) messageObj.getObject();
            
            System.out.println(NewsItem);
            // TODO Send to ui
        
		} catch (JMSException jmse){ jmse.printStackTrace( ); }
		
	}
	
	/* Close the JMS connection */
    public void close( ) throws JMSException {
        connection.close( );
    }
        
    public void subscribeRequest(String wantedTopic){		
        // Create a JMS publisher and subscriber
    	try{
    		// Create two JMS session objects
    		TopicSession subSession =
    				connection.createTopicSession(false,
    						Session.AUTO_ACKNOWLEDGE);
    		
			TopicSubscriber subscriber = 
				subSession.createSubscriber(subSession.createTopic(wantedTopic));

			// Set a JMS message listener
			subscriber.setMessageListener(this);
    	}
    	catch(JMSException e){
    		e.printStackTrace();
    	}
    }
    
    public void seen(News NewsItem){
    	try{
    		TopicSession pubSession =
    				connection.createTopicSession(false,
    						Session.AUTO_ACKNOWLEDGE);    	
			TopicPublisher publisher = 
				pubSession.createPublisher(pubSession.createTopic("seen:" + NewsItem.getId()));
			
			publisher.publish(pubSession.createObjectMessage(NewsItem));	
    	}
    	catch(JMSException e){
    		e.printStackTrace();
    	}

    }

}



