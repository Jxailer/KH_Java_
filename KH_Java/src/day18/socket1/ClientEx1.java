package day18.socket1;

import java.net.Socket;

public class ClientEx1 {

	public static void main(String[] args) {
		int port = 5001;
		String ip =  "192.168.30.199";
		try {
			Socket socket = new Socket(ip, port);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
