package day19;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ListEx1 {

	public static void main(String[] args) {
		// In case, didn't set type of List which is Generic Interface:
		// it will be set as Top class 'Object'
		// in this case, while using get(), it demands type transformation 
		
		// -get(): provided by List
		List list  = new ArrayList();
		list.add("abc");
		String str = (String)list.get(0);
		System.out.println(str);
		
		List<String> list2 = new ArrayList<String>(0);
		list2.add("abc");
		String str2 = list2.get(0);
		System.out.println(str2);
		
		 /* list3 = new ArrayList<String>();
		 * list3 = new LinkedList<String>();
		 * - both are available.
		 * 
		 * list 4 is only can be set as object from ArrayList class.
		 * list4 = new ArrayLsit<String>(); (o)
		 * list4 = new LinkedList<String>(); (x)
		 * */
		List<String> list3;
		ArrayList<String> list4;
		
		/* add(object) method: provided by Collection interface
		 * - List.add(object) always returns true (permits duplicate)
		 * - In case of Set, if Set contains 'object', it returns true, if not, returns false
		 *  (does not permit duplicate)
		 * */
		List<String> list5 = new ArrayList<String>();
		list5.add("abc");
		list5.add("def");
		
		/* contains(object): provided by Collection interface
		 * notify whether Collection contains the object
		 * - it functions using Object.equals()
		 * - if object in Collection is different with object in method, returns false.
		 * - if it's not, returns result derive from using .equals()
		 * - results are depends on how equals() was overrided in the class
		 * */
		System.out.println();
		System.out.println(list5.contains("abc"));
		System.out.println();
		
		/*indexOf(object): provided by List interface (cannot be used with Set.)
		 * - returns which index is object located. if there isn't, returns -1
		 * - same function mechanism with contains() method 
		*/
		System.out.println(list.indexOf("abc"));
		System.out.println();
		/*remove(index):provided by List interface (cannot be used with Set.)
		 * - removes object located at the index, returns it.
		 * 
		 *remove(object): provided by Collection interface
		 *- removes object in Collection same with object in method, 
		 *and return whether it is removed (boolean). 
		 * */
		list5.remove(0);
		System.out.println(list5);
		System.out.println(list5.remove("def"));
		System.out.println();
		
		list5.add("123");
		list5.add("456");
	
		//==============================================================
		
		/* iterator(): provided by Collection interface
		 * - generates iterator using Collection and returns it.
		 * => can be used with loops
		 * */
		Iterator<String> it = list5.iterator();
		while(it.hasNext()) {
			String tmp = it.next();
			System.out.println(tmp);
		}
		System.out.println();
		
		//==============================================================
		
		/* size(): provided by Collection interface
		 * - returns size of Collection's size
		 * */
		System.out.println(list5.size());
		System.out.println();
		
		/* forEach(): provided by Collection interface
		 * - it can be used when you'd like to use a object derive from Collection
		 * */
		list5.forEach(a->System.out.println(a));
		System.out.println();
		
		/*sort(): provided by List interface (cannot be used with Set.)
		 * - if sorting criteria is set, it sorts list based on the criteria
		 * - the criteria can be set by integer. (front: negative, same position: 0, behind: positive)
		 * - demands the class that implemented Comparator interface
		 * - generally, it is used with lambda that generates anonymous object.
		 */
		list5.add("1");
		list5.add("42132");
		list5.sort((s1, s2)->s1.compareTo(s2));
		System.out.println(list5);
		
		/* Collections.sort(): sorts objects by using method from Collections interface
		 * - in case list implemented Comparable interface.
		 * */
		
		Collections.sort(list5);
		System.out.println(list5);
		
		
	}

}
