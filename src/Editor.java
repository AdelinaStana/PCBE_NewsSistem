import java.io.*;
import java.net.*;


public class Editor implements Runnable, EditorEvents {

    private EditorUI edtUI ;
    private String name;

    
    public Editor(String name) { 
    	 this.name = name;
    	 new Thread( this ).start();
    }

	
	public void run()
	{ 
	
		edtUI = new EditorUI(name);
		edtUI.start();
		
		edtUI.addListener(this);
		  
	     while (edtUI.getConnectionStatus()) 
			{  
	    	  try { // Poll every ~10 ms
	              Thread.sleep(10);
	           }
	           catch (InterruptedException e) {}
	    	  
	    	  
			}
	

	}



	public void newsAdded(News n) {
		
		System.out.println("a");
	}


	@Override
	public void newsDeleted(News n) {
		System.out.println("d");
		
	}


	@Override
	public void newsEdited(News n ) {

		System.out.println("e");
	}


	@Override
	public void followRequest(String s) {
		System.out.println("f");
	}
	
	
}


