package day17;

public class ThreadEx1 {

	public static void main(String[] args) {
		// Single-thread (function works in order)
		for(int i = 0; i<1000; i++) {
			System.out.print("-");
		}
		for(int i = 0; i<1000; i++) {
			System.out.print("+");
		}
		System.out.println();
		
		// Multi-thread (both threads are works at the same time
		Thread t1 = new Thread1();
		t1.start();
		
		Thread t2 = new Thread2();
		t2.start();
	}

}

class Thread1 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i<1000; i++) {
			System.out.print("-");
		}
	}
	
}

class Thread2 extends Thread{
	@Override
	public void run() {
		for(int i = 0; i<1000; i++) {
			System.out.print("+");
		}
	}
}