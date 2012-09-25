package jin.collection.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PropertyUtilTest {

	@Test
	public void bean() throws Exception {
		String string = new String("333");
		assertNotNull(PropertyUtil.getProperty(string, "bytes"));
	}

	@Test
	public void maps() throws Exception {
		Map map = new HashMap();
		map.put("pino", "222");
		assertEquals("222", PropertyUtil.getProperty(map, "pino"));
	}

}
