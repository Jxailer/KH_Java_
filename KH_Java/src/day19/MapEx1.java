package day19;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapEx1 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		
		/* put(k,v)
		 * - add values by using k and v
		 * - if k is duplicated, only value of v will be renewed 
		 *   and returns previous v value
		 * (k cannot be duplicated.)
		 * */
		map.put("abc", "def");
		String prev = map.put("abc", "ddf");
		System.out.println(map);
		System.out.println(prev);
		System.out.println();
		
		/*get(k): returns v value that matches with k*/
		String str = map.get("abc");
		System.out.println(str);
		System.out.println();
		
		/*containsKey(k): notify whether there's key matches with k
		 *returns boolean */
		System.out.println(map.containsKey("abc"));
		System.out.println();
		
		/* remove(k): removes object that mathes with k and returns its value 
		 * */
		str = map.remove("abc");
		System.out.println(str);
		System.out.println();
		
		map.put("zxc", "123");
		map.put("gello", "hi");
		
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String value = it.next();
			System.out.println(value);
		}
		
	}

}
