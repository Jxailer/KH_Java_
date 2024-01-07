package day18.student2;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 만들기
public class StudentManager {


	private List<Student> list = new ArrayList<Student>();
	
	public StudentManager(List<Student> list) {
		if(list == null) {
			return;
		}
		this.list = list;
	}

	public boolean updateStudent(Student std) {
		// 등록된 학생인지 확인함
		int index = list.indexOf(std);
		// 등록된 학생이 아니면 flase를 리턴함
		if(index<0) {
			return false;
		}
		// 등록된 학생이라면 이름을 수정함.
		list.get(index).setName(std.getName());
		return true;
	}

	public void printStudent() {
		list.stream().forEach(s->System.out.println(s));
		
	}

	public boolean insertStudent(Student std) {
		// 중복 체크
		if(list.contains(std)) {
			return false;
		}
		// 아니면 추가
		list.add(std);
		return true;
	}
}
