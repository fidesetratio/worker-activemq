package com.app.component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.stereotype.Component;

@Component
public class CountLatchComponent {

	private CountDownLatch latch;
	private ThreadPoolExecutor executors;
	public CountLatchComponent() {
		this.latch = new CountDownLatch(5);
		this.executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		this.executors.setRejectedExecutionHandler(new MyRejectedExecutionHandler());;
		
	}
	
	public CountDownLatch latch() {
		return this.latch;
	}
	
	public ThreadPoolExecutor executors() {
		return this.executors;
		
	}
	
	class MyRejectedExecutionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			// TODO Auto-generated method stub
			System.out.println(r.toString()+"is rejected");
		}
		
	}
}
