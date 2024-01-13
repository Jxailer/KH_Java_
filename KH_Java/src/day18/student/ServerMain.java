package day18.student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ServerMain {

	private static List<Student> list;
	private static String fileName = "src/day18/student/list.txt";
	
	public static void main(String[] args) {
		int port = 5001;
		load();
		System.out.println(list);
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				Thread t = new ServerThread(serverSocket.accept(), list);
				t.start();
				//save();
			}
		} catch (IOException e) {
			System.out.println("[server is shut down due to exception.]");
		}
		
	}

	private static void load() {
		try {
			ObjectInputStream fois = new ObjectInputStream(new FileInputStream(fileName));
			list = (List<Student>)fois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			list = new ArrayList<Student>();
			e.printStackTrace();
		}
	}

}

@RequiredArgsConstructor
class ServerThread extends Thread{
	@NonNull
	private Socket socket;
	@NonNull
	List<Student> list; // student list shared by server
	
	private ObjectInputStream ois; // used while receiving messages from client 
	private ObjectOutputStream oos; // used while sending messages to client 
	
	public void run() {
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			while(true) {
				// start function that client demands
				// submits function that client demands
				String menu = ois.readUTF();
				// starts the function
				switch(menu) {
				case "LOAD":
						load();
						System.out.println(list);
						break;
				case "INSERT":
						insert();
						System.out.println(list);
						break;
				case "SAVE":
						save();
						return;
				case "UPDATE":
						update();
						System.out.println(list);
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void update() {
		Student std;
		try {
			std = (Student)ois.readObject();
			int index = list.indexOf(std);
			if(index<0) {
				return;
			} list.get(index).setName(std.getName());
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}

	private void save() {
		String fileName = "src/day18/student/list.txt";
		try {
			ObjectOutputStream foos = 
				new ObjectOutputStream(new FileOutputStream(fileName));
			foos.writeObject(list);
			foos.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void insert() {
		try {
			Student std = (Student)ois.readObject();
			list.add(std);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try {
			oos.writeObject(list);
			oos.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
