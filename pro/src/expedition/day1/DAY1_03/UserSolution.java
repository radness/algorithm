package expedition.day1.DAY1_03;

import java.util.HashMap;

public class UserSolution {
	static HashMap<Integer, Person> hmap;
	
	static class Person {
		int id;
		String name;
		String inOut;
		
		public Person(int id, String name, String inOut) {
			this.id = id;
			this.name = name;
			this.inOut = inOut;
		}
	}
	
	public void init() {
		hmap = new HashMap<>();
		
	}

	public String register(int id, String name) {
		if (!hmap.containsKey(id) && hmap != null) {
			hmap.put(id, new Person(id, name, "OUT"));
			return "OK";
		} else {
			return  "ERROR";
		}
	}

	public String[] inout(int id) {
		String rName = "";
		
		if (hmap.get(id) == null) {
			return new String[] { "ERROR", "ERROR" };
		}
		
		Person person = hmap.get(id);
		
		if (person.inOut.equals("OUT")) {
			person.inOut = "IN";
		} else {
			person.inOut = "OUT";
		}
		
		return new String[] { person.name, person.inOut };
	}
}
