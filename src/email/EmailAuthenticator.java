package email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * user:Rex
 * date:2016年12月25日 上午1:13:37
 * TODO 发送邮件账户信息
 */
public class EmailAuthenticator extends Authenticator {
	//创建单例邮件账户信息
	private static EmailAuthenticator emailAuthenticator = new EmailAuthenticator();
	
	/**
	 * user:Rex
	 * date:2016年12月25日 上午3:28:10
	 * TODO 私有化构造函数
	 */
	private EmailAuthenticator() {
		
	}

	/* 
	 * user: Rex
	 * date: 2016年12月25日  上午3:33:36
	 * @return 返回密码校验对象
	 * TODO 重写获取密码校验对象方法
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(EmailConfig.getUser(), EmailConfig.getPass());
	}

	public static EmailAuthenticator createEmailAuthenticator() {
		return emailAuthenticator;
	}
}
