import java.io.*;
import java.net.*;


public class Editor implements Runnable {

    private EditorUI edtUI ;
    private String name;

    
    public Editor(String name) { 
    	 this.name = name;
    	 new Thread( this ).start();
    }

	@Override
	public void run()
	{ 
	
		edtUI = new EditorUI(name);
		edtUI.start();
		
		  
	     while (edtUI.getConnectionStatus()) 
			{  
	    	  try { // Poll every ~10 ms
	              Thread.sleep(10);
	           }
	           catch (InterruptedException e) {}
	    	  
	    	  
			}
	

	}
}


