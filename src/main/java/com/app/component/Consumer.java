package com.app.component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	 @Autowired
	 CountLatchComponent latchcomp;
	 
	 private AtomicInteger counter = new AtomicInteger();
	
	@JmsListener(destination = "helloworldactivemq")
    public void listener(String msg){
		/*
		 * CountDownLatch l = latchcomp.latch();
		 * System.out.println("Received Message : "+msg); Worker w = new Worker(1000000,
		 * l, msg); w.start();
		 * 
		 * try { l.await(); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * System.out.println("only receive one");
		 * 
		 */
		
		CountDownLatch l = latchcomp.latch();
		System.out.println("main"+msg);
		
		latchcomp.executors().submit(new Worker(100000, msg,l) );
		int count = counter.incrementAndGet();
		if(count>=5) {
			System.out.println("sudah lima");
			try {
				l.await(10000,TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			counter.set(0);
		}
	}

	
	
}
