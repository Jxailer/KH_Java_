package day18.socket1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	
	private Socket socket;
	
	public void receive() { // method receiving message from server
		Thread t = new Thread(()->{
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream()); 
				// gets stream in Object form received from Socket
//					Stream: Iterator that able to refer to objects in Collection one by one
				while(true) {
					String str = ois.readUTF(); // stream is transform in UTF and stored as String
					if(str.equals("-1")) {
						break;
					}
					System.out.println(str);
				}
			}catch(Exception e) {
					System.out.println("reading fuction suddenly ended by following exception:");
					e.printStackTrace();
			}
				
			
		});
		t.start();
		
		/*Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			
		}
	});*/
	}
	
	public void send() { // method sending message from client to server
		Thread t = new Thread(()->{
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				Scanner scan = new Scanner(System.in);
				while(true) {
					String str = scan.nextLine();
					oos.writeUTF(str);
					oos.flush();
					if(str.equals("-1")) {
						break;
					}
				}
				System.out.println("sending fuction ends successfully.");
			}catch(Exception e) {
				System.out.println("sending fuction suddenly ends due to following exception:");
				e.printStackTrace();
			}
		});
		t.start();
	}
	
	
}

