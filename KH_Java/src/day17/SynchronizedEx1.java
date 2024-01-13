package day17;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SynchronizedEx1 {

	public static void main(String[] args) {
		BankBook bb1 = new BankBook(0, "홍길동");
		Customer c1 = new Customer(bb1, "홍길동");
		BankBook bb2 = new BankBook(0, "고길동");
		Customer c2 = new Customer(bb2, "고길동");
		
		c1.start();
		c2.start();
	}

}

@AllArgsConstructor
class Customer extends Thread{
	private BankBook bankbook;
	private String name;
	
	@Override
	public void run() {
		System.out.println("depositting... "+ name);
		bankbook.deposit(10000);
	}
}

@Data
@AllArgsConstructor
class BankBook{
	public void deposit(int money) {
		this.balance += money;
		
		
		try {
			// pause current thread for 2 sec
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("name: "+ this.name);
		System.out.println("balance: " + this.balance);
		
	}
	private int balance;
	private String name;
	
}