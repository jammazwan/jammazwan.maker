package jammazwan.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class OptionsTest {

	@Test
	public void test() {
		Options o = new Options();
		Options.StartType ob = o.randomStartType();
		System.out.println(ob.getClass());
	}

}
