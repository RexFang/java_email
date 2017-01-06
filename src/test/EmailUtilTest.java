package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import email.EmailUtil;

public class EmailUtilTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSendEmail() {
		try {
			String title = "利比亚客机遭劫持6大疑问：劫机者怎样通过安检的(1)";
			String content = "当地时间23日，俄罗斯总统普京在莫斯科国际贸易中心举行年度记者会。记者会持续了近4个小时，普京一共回答了来自俄罗斯各个地区及全世界记者的47个问题。自2001年起，普京都会在每年12月中下旬举行年度记者会，这是他的第12次记者会。";
			List<File> fileList = new ArrayList<File>();
			fileList.add(new File("C:/Users/Rex/Desktop/log4j.properties"));
			EmailUtil.sendEmail(title, content, "rex_test@yeah.net", "2316056859@qq.com", "2363271365@qq.com", fileList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
