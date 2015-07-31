package sef.module10.activity;

public class QueueWorker implements Runnable {

	private String task;
	private String name;
	private int length;
	private int index;
	private TaskQueue queue;
private long time;
	public QueueWorker(String name, TaskQueue queue, int index) {
		this.name = name;
		this.queue = queue;
		this.index=index;
	}

	public void run() {
System.out.println("worker "+index);
		while (true) {

			synchronized (this.queue) {
				if (this.queue.isEmpty()) {
					try {
						System.out.println(name + " waiting for task");
						queue.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}

			this.task = queue.getNextTask();
			if(task!=null){
			this.length = task.length();

			doTask();}
			
		}
	}
	
	private void doTask(){
		System.out.println(name + " GIVEN : " + task);
		int i=0;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time=System.currentTimeMillis();
			while(time-System.currentTimeMillis()>-5000)
			i+=1;
			
		}
	
}
