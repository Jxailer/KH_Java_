package day18.student2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ServerMain {

	private static StudentManager sm = new StudentManager();
	private static String fileName = "src/day18/student2/list2.txt";
	
	public static void main(String[] args) {
		int port = 5001;
		load();
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				Thread t = new ServerThread(serverSocket.accept(), sm);
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
			List<Student> list = (List<Student>)fois.readObject();
			sm = new StudentManager(list); // create student manager object using list
		} catch (IOException | ClassNotFoundException e) {
			sm = new StudentManager();
			e.printStackTrace();
		}
	}

}

@RequiredArgsConstructor
class ServerThread extends Thread{
	@NonNull
	private Socket socket;
	@NonNull
	private StudentManager sm; // using student manager instead of student list
	
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
						break;
				case "INSERT":
						insert();
						break;
				case "SAVE":
						save();
						return;
				case "UPDATE":
						update();
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
			sm.updateStudent(std); 
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}

	private void save() {
		String fileName = "src/day18/student2/list2.txt";
		try {
			ObjectOutputStream foos = 
					new ObjectOutputStream(new FileOutputStream(fileName));
				foos.writeObject(sm.getList());
				foos.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void insert() {
		try {
			Student std = (Student)ois.readObject();
			sm.insertStudent(std);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try {
			oos.writeObject(sm.getList());
			oos.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
