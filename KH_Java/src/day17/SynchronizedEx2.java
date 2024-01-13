package day17;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SynchronizedEx2 {

	public static void main(String[] args) {
		BankBook2 bb1 = new BankBook2(0, "홍길동");
		Customer2 c1 = new Customer2(bb1, "홍길동");
		BankBook2 bb2 = new BankBook2(0, "고길동");
		Customer2 c2 = new Customer2(bb2, "고길동");
		
		Thread t1 = new Thread(()->{
			c1.deposit(1000);
		});
		
		Thread t2 = new Thread(()->{
			c2.deposit(1000);
		});
		
		t1.start();
		t2.start();
	}

}

@AllArgsConstructor
class Customer2 extends Thread{
	private BankBook2 bankbook;
	private String name;
	
	public void deposit(int money) {
		synchronized(bankbook) { // using synchronized keyword, restricts calling bankBook object
			int balance = bankbook.getBalance();
			bankbook.setBalance(balance+money);
			
			
			try {
				// pause current thread for 2 sec
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("name: "+ this.name);
			System.out.println("balance: " +bankbook.getBalance());
		}
	}
}

@Data
@AllArgsConstructor
class BankBook2{
	
	private int balance;
	private String name;
	
}