package sef.module10.activity;

public class QueueSample {
static Thread[] tread = new Thread[100000];

	public static void main(String arg[]){
		
		TaskQueue queue = new TaskQueue();
		/*QueueWorker w1 = new QueueWorker("Curly", queue, 1);
		QueueWorker w2 = new QueueWorker("Larry", queue,2));
		QueueWorker w3 = new QueueWorker("Moe", queue);
		QueueWorker w4 = new QueueWorker("Joe", queue);
		QueueWorker w5 = new QueueWorker("Bart", queue);
		QueueWorker w6 = new QueueWorker("Lisa", queue);
	
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		Thread t3 = new Thread(w3);
		Thread t4 = new Thread(w4);
		Thread t5 = new Thread(w5);
		Thread t6 = new Thread(w6);*/
	
		
		System.out.println("Queue starting in 5 seconds!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Queuing now!");
		
		
		for (int i=0; i<20000;i++){
	tread[i]= new Thread(new QueueWorker("Janis", queue, i));
	queue.addTask("Task 9");		
	tread[i].start();
		}
		//t1.start();
		//t2.start();
		//t3.start();
		//t4.start();
		//t5.start();
		//t6.start();
		

		
	}
}
