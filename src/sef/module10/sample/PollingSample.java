package sef.module10.sample;

public class PollingSample {
	
	public static void main(String arg[]) throws InterruptedException{
		AnnoyedDriver d = new AnnoyedDriver();
		AnnoyingPassenger p = new AnnoyingPassenger(d);
		
		Thread a = new Thread(d);
		a.join();
		System.out.println("start");
		Thread b = new Thread(p);
		a.start();
		b.start();
		
	}
}
