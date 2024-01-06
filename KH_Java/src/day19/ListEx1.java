package day19;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ListEx1 {

	public static void main(String[] args) {
		// 제네릭 인터페이스인 List의 타입을 지정하지 않은 경우,
		// 최상위 클래스인 Object가 자동으로 지정됨.
		// 이 경우, get()을 이용할 때 클래스 형변환을 해줘야함.
		
		// -get(): 리스트에서만 제공하는 메서드
		List list  = new ArrayList();
		list.add("abc");
		String str = (String)list.get(0);
		System.out.println(str);
		
		List<String> list2 = new ArrayList<String>(0);
		list2.add("abc");
		String str2 = list2.get(0);
		System.out.println(str2);
		
		/* list3은 List 인터페이스를 구현한 구현 클래스들의 객체 중 아무나 올 수 있다.
		 * list3 = new ArrayList<String>();
		 * list3 = new LinkedList<String>();
		 * - 둘 다 가능함
		 * 
		 * list 4는 ArrayList클래스를 이용한 객체만 올 수 있다.
		 * list4 = new ArrayLsit<String>(); (o)
		 * list4 = new LinkedList<String>(); (x)
		 * */
		List<String> list3;
		ArrayList<String> list4;
		
		/* add(객체) 메서드: Collection에서 제공하는 메서드
		 * - List에서는 무조건 true(중복을 허용하기 때문에)
		 * - Set에서는 없으면 true, 있으면 false(중복을 허용하지 않기 때문에
		 * */
		List<String> list5 = new ArrayList<String>();
		list5.add("abc");
		list5.add("def");
		
		/* contains(객체): Collection에서 제공해주는 메서드
		 * 컬렉션에 객체가 있는지 없는지를 알려줌
		 * - 이 때, Object.equals()를 이용하여 동작함.
		 * - 컬렉션에 있는 타입과 객체의 타입이 다르면 false를 반환함
		 * - 컬렉션에 있는 타입과 객체의 타입이 같으면 객체.equals()를 호출해서 결과를 리턴함
		 * - 해당 클래스의 equals가 어떻게 오버라이딩 되어있느냐에 따라 결과가 달라짐.
		 * */
		System.out.println();
		System.out.println(list5.contains("abc"));
		System.out.println();
		/*indexOf(객체): 리스트에서 제공해주는 메서드(셋에서는 사용할 수 없음.)
		 * - 리스트에 객체가 몇 번지에 있는 지 알려주고, 없으면 -1을 반환함
		 * - 동작방식은 contains()와 같음.*/
		System.out.println(list.indexOf("abc"));
		System.out.println();
		/*remove(번지): List에서 제공해주는 메서드(Set에서는 사용할 수 없음.)
		 * - 번지에 있는 객체를 제거한 후, 해당 객체를 반환함
		 *remove(객체): Collection에서 제공해주는 메서드.
		 *- 객체ㅘ 일치하는 요소를 제거한 후, 제거 여부를 반환함(boolean)*/
		list5.remove(0);
		System.out.println(list5);
		System.out.println(list5.remove("def"));
		System.out.println();
		
		list5.add("123");
		list5.add("456");
	
		//==============================================================
		
		/* iterator(): Collection에서 제공하는 메서드
		 * - 컬렉션을 이용해 반복자를 생성하여 리턴함
		 * => 반복자를 이용하여 반복문으로 활용함
		 * */
		Iterator<String> it = list5.iterator();
		while(it.hasNext()) {
			String tmp = it.next();
			System.out.println(tmp);
		}
		System.out.println();
		
		//==============================================================
		
		/* size(): Collection에서 제공해주는 메서드
		 * - 컬렉션의 크기를 반환함
		 * */
		System.out.println(list5.size());
		System.out.println();
		
		/* forEach(): collection에서 제공해주느 ㄴ메서드
		 * - 람다식을 이요하여 컬렉션에서 하나 꺼낸 값을 활용할 때 사용함
		 * */
		list5.forEach(a->System.out.println(a));
		System.out.println();
		
		/*sort(): List에서 제공하는 메서드. (Set에서는 사용할 수 없음)
		 * - 람다식을 이요해 정렬기준을 정해주면, 해당 기준대로 리스트를 정렬함
		 * - 정렬기준은 정수로 알려주고, 음수면 앞, 0이면 같은 위치, 양수면 뒤.
		 * - comparator 인터페이스를 구현한 클래스의 객체가 필요함
		 * - 일반적으로는 람다식을 이용하여 익명 객체를 생성해서 활용함*/
		list5.add("1");
		list5.add("42132");
		list5.sort((s1, s2)->s1.compareTo(s2));
		System.out.println(list5);
		
		/* Collections.sort(): Collections 클래스의 메서드를 사용하기
		 * - 리스트 타입이 comparable 인터페이스를 구현한 구현 클래스인 경우, 해당 기준을 이용해 정렬함
		 * */
		
		Collections.sort(list5);
		System.out.println(list5);
		
		
	}

}
