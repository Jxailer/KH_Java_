package day18.student2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Program.Program;
import day18.student2.Student;

public class StudentProgram implements Program {
	private final int EXIT = 4;
	private Scanner scan = new Scanner(System.in);
	private StudentManager sm = new StudentManager();
	private Socket socket;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	@Override
	public void run() {
		String ip = "192.168.30.199";
		int port = 5001;
		// connect server
		connectServer(ip, port);
		// load student info from server
		load();
		int menu = 0;
		do {
			try {
				printMenu();
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("wrong menu option.");
				scan.nextLine(); //  removes value or space mis-entered in input buffer
			}
		}while(menu != EXIT);
	}

	private void load() {
		if(socket == null) {
			System.out.println("[cannot load data : server is not connected.]");
		}
		try {
			oos.writeUTF("LOAD");
			oos.flush();
			// 읽어오기
			List<Student> list = (List<Student>)ois.readObject();
			sm = new StudentManager(list);
			System.out.println("[loaded successfully]");
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("[exception occurred while loading file]");
		}
		
	}

	private void connectServer(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println(socket);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("[server connected successfully]");
		}catch(IOException e) {
			System.out.println("[server connection failed]");
		}
		
	}

	@Override
	public void printMenu() {
		System.out.println("------menu------");
		System.out.println("1. add student");
		System.out.println("2. student list(whole)");
		System.out.println("3. student revise(name)");
		System.out.println("4. exit");
		System.out.println("---------------");
		System.out.print("menu select : ");

	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			insertStudent();
			break;
		case 2:
			printStudent();
			break;
		case 3:
			updateStudent();
			break;
		case 4:
			exit();
			System.out.println("shutting down program.");
			break;
		default:
			throw new InputMismatchException();

	}

}

	private void exit() {
		try{
			oos.writeUTF("SAVE");
			oos.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	private void updateStudent() {
		// 학생 정보 입력
		System.out.print("grade : ");
		int grade = scan.nextInt();
		System.out.print("class  : ");
		int classNum = scan.nextInt();
		System.out.print("student number : ");
		int num = scan.nextInt();
		System.out.print("name : ");
		scan.nextLine();
		String name = scan.nextLine();
		
		// revise student info
		Student std = new Student(grade, classNum, num, name);
		if(sm.updateStudent(std)) {
			System.out.println("name revised.");
			sendUpdateStudent(std);
		}else {
			System.out.println("not enrolled student.");
		}
		
	}

	private void sendUpdateStudent(Student std) {
		try {
			oos.writeUTF("UPDATE");
			oos.flush();
			oos.writeObject(std);
			oos.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void printStudent() {
		sm.printStudent();
	}

	private void insertStudent() {
		// enter student info
		System.out.print("grade : ");
		int grade = scan.nextInt();
		System.out.print("class  : ");
		int classNum = scan.nextInt();
		System.out.print("student number : ");
		int num = scan.nextInt();
		System.out.print("name : ");
		scan.nextLine();
		String name = scan.nextLine();
		// add student info to manager
		Student std = new Student(grade, classNum, num, name);
		if(sm.insertStudent(std)) {
			System.out.println("student added.");
			sendStudent(std);
		}else {
			System.out.println("already enrolled student.");
		}
		
	}

	private void sendStudent(Student std) {
		if(socket == null) {
			System.out.println("[cannot add student info : server not connected.]");
		}
		try {
			oos.writeUTF("INSERT");;
			oos.flush();
			oos.writeObject(std);
			oos.flush();
		}catch(IOException e) {
			System.out.println("[exception occurred while adding student info on server.]");
		}
		
	}
}