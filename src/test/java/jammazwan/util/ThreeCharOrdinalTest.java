package jammazwan.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ThreeCharOrdinalTest {

	@Test
	public void test() {
		List<String> wholeList = new ThreeCharOrdinal().get();
		List<String> part22List = new ThreeCharOrdinal().get(22);
		List<String> partDef27List = new ThreeCharOrdinal().get("def", 27);
		assertEquals(17576, wholeList.size());
		assertEquals("aab", wholeList.get(1));
		assertEquals("zzz", wholeList.get(wholeList.size() - 1));
		assertEquals(22, part22List.size());
		assertEquals("deg", partDef27List.get(1));
		assertEquals(27, partDef27List.size());
	}

}
