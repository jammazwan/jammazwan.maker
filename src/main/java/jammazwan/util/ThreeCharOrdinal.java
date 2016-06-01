package jammazwan.util;

import java.util.ArrayList;
import java.util.List;

public class ThreeCharOrdinal {
	List<String> index = new ArrayList<String>();

	public List<String> get() {
		if (index.isEmpty()) {
			String position1, position2, position3;
			for (int x = 0; x < 26; x++) {
				position1 = "" + (char) (x + 97);
				for (int y = 0; y < 26; y++) {
					position2 = "" + (char) (y + 97);
					for (int z = 0; z < 26; z++) {
						position3 = "" + (char) (z + 97);
						String val = position1 + position2 + position3;
						index.add(val.trim());
					}
				}
			}
		}
		return index;
	}

	public List<String> get(int count) {
		count++;
		List<String> returnList = new ArrayList<String>();
		for (String abc : get()) {
			count--;
			if (count == 0) {
				break;
			}
			returnList.add(abc.trim());
		}
		return returnList;
	}

	public List<String> get(String start, int count) {
		count++;
		List<String> returnList = new ArrayList<String>();
		boolean go = false;
		for (String abc : get()) {
			if (abc.equals(start)) {
				go = true;
			}
			if (go) {
				count--;
				if (count == 0) {
					break;
				}
				returnList.add(abc.trim());
			}
		}
		return returnList;
	}
}
