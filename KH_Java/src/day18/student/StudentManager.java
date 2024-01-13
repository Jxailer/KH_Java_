package day18.student;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor // generates default constructor
public class StudentManager {


	private List<Student> list = new ArrayList<Student>();
	
	public StudentManager(List<Student> list) {
		if(list == null) {
			return;
		}
		this.list = list;
	}

	public boolean updateStudent(Student std) {
		// checks whether the student is already inserted.
		int index = list.indexOf(std);
		// if not, returns false
		if(index<0) {
			return false;
		}
		// if they are, update their name.
		list.get(index).setName(std.getName());
		return true;
	}

	public void printStudent() {
		list.stream().forEach(s->System.out.println(s));
		
	}

	public boolean insertStudent(Student std) {
		// check duplicate
		if(list.contains(std)) {
			return false;
		}
		// if not, add
		list.add(std);
		return true;
	}
}
