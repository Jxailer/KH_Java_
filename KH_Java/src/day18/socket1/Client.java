package day18.socket1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	
	private Socket socket;
	
	private void receive() { // 서버 측에서 보낸 메시지를 클라이언트가 받도록 하는 코드
		Thread t = new Thread(()->{
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream()); 
				// socket으로부터 받아들여온 stream을 오브젝트 형태로 받아옴
				// Stream : 컬렉션(배열 포함)의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자
				while(true) {
					String str = ois.readUTF(); // 받아온 stream을 UTF 방식으로 변환하여 String으로 저장
					if(str.equals("-1")) {
						break;
					}
					System.out.println(str);
				}
			}catch(Exception e) {
					e.printStackTrace();
					System.out.println("예외가 발생해서 읽기 기능을 종료합니다.");
			}
				
			
		});
		t.start();
		
		/*Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			
		}
	});*/
	}
	
	public void send() { // 클라이언트 쪽에서 서버로 메시지를 보내는 메서드
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
				System.out.println("전송 기능이 정상적으로 종료됩니다.");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("예외가 발생해서 전송 기능을 종료합니다.");
			}
		});
		t.start();
	}
	
	
}

