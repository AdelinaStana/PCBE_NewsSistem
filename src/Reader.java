import java.io.*;
import java.net.*;



public class Reader implements Runnable {

    private ReaderUI rdrUI ;

    
    public Reader() { 
    	 new Thread( this ).start();
    }

	@Override
	public void run()
	{ 
	
		ReaderUI rdrUI = new ReaderUI();
		rdrUI.start();
		
		  
	     while (rdrUI.getConnectionStatus()) 
			{  
	    	  try { // Poll every ~10 ms
	              Thread.sleep(10);
	           }
	           catch (InterruptedException e) {}
	    	  
	    	  
			}
	

	}
}


