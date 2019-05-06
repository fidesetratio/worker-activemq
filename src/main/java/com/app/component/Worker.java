package com.app.component;

import java.util.concurrent.CountDownLatch;

public class Worker  implements Runnable{
	
	  private int delay; 
	    private CountDownLatch latch; 
	    private String name;
	  
	    public Worker(int delay,String name, CountDownLatch latch) 
	    { 
	        this.name = name;
	        this.delay = delay; 
	        this.latch = latch;
	        
	    } 
	  
	    public void run() 
	    { 
	        try
	        
	        
	        { 
	          System.out.println("run...."+this.name);
	        	Thread.sleep(delay); 
	            System.out.println(Thread.currentThread().getName() 
	                            + " finished"); 
	            
	            latch.countDown();
	        } 
	        catch (InterruptedException e) 
	        { 
	            e.printStackTrace(); 
	        } 
	    } 

}
