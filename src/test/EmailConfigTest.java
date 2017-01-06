package test;

import org.junit.Assert;
import org.junit.Test;

import email.EmailConfig;

public class EmailConfigTest {
	@Test
	public void testEmailConfigInit(){
		Assert.assertEquals("没有开启debug调试", "true", EmailConfig.getDebug());
		Assert.assertEquals("发送服务器没有身份验证", "true", EmailConfig.getAuth());
		Assert.assertEquals("邮件服务器主机名错误", "smtp.126.com", EmailConfig.getHost());
		Assert.assertEquals("发送邮件协议名称错误", "smtp", EmailConfig.getProtocol());
		Assert.assertEquals("发送邮件用户名错误", "java_email_test", EmailConfig.getUser());
		Assert.assertEquals("发送邮件邮箱密码错误", "test123", EmailConfig.getPass());
		Assert.assertEquals("发送邮件发件人错误", "java_email_test@126.com", EmailConfig.getFrom());
		Assert.assertNotNull("Session 属性为空", EmailConfig.getSessionProperties());
	}
}
