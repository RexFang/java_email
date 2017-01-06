package email;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * user:Rex
 * date:2016年12月25日 上午1:46:43
 * TODO 发送邮件配置信息
 */
public class EmailConfig {
	private static final Logger logger = Logger.getLogger(EmailConfig.class);
	
	public static final String MAIL_DEBUT = "mail.debug";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_HOST = "mail.host";
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_USER = "mail.user";
	public static final String MAIL_PASS = "mail.pass";
	public static final String MAIL_FROM = "mail.from";
	
	//是否开启debug调试
	private static String debug;
	
	//发送服务器是否需要身份验证
	private static String auth;
	
	//发送邮件端口
	private static String port;
	
	//邮件服务器主机名
	private static String host;
	
	//发送邮件协议名称
	private static String protocol;
	
	//发送邮件用户名
	private static String user;
	
	//发送邮件邮箱密码
	private static String pass;
	
	//发送邮件发件人
	private static String from;
	
	//创建单例Session配置信息
	private static Properties sessionProperties = new Properties();
	
	//创建单例邮箱配置信息
	private static EmailConfig emailConfig = new EmailConfig();
	
	/**
	 * user:Rex
	 * date:2016年12月25日 上午2:03:48
	 * @throws IOException
	 * TODO 从配置文件中读取邮箱配置信息（比较好的方式是让用户在管理后台配置，从数据库读取邮箱配置信息）
	 */
	private EmailConfig() {
		try {
			InputStream fis = EmailConfig.class.getResourceAsStream("/email.properties");
			Properties prop = new Properties();
			prop.load(fis);
			EmailConfig.auth = prop.getProperty(EmailConfig.MAIL_SMTP_AUTH, "false").trim();
			EmailConfig.port = prop.getProperty(EmailConfig.MAIL_SMTP_PORT, "495").trim();
			EmailConfig.debug = prop.getProperty(EmailConfig.MAIL_DEBUT, "false").trim();
			EmailConfig.from = prop.getProperty(EmailConfig.MAIL_FROM, "java_email_test@126.com").trim();
			EmailConfig.host = prop.getProperty(EmailConfig.MAIL_HOST, "smtp.126.com").trim();
			EmailConfig.pass = prop.getProperty(EmailConfig.MAIL_PASS, "test123").trim();
			EmailConfig.protocol = prop.getProperty(EmailConfig.MAIL_TRANSPORT_PROTOCOL, "smtp").trim();
			EmailConfig.user = prop.getProperty(EmailConfig.MAIL_USER, "java_email_test").trim();
			fis.close();
			
			sessionProperties.setProperty(EmailConfig.MAIL_SMTP_AUTH, EmailConfig.auth);
			sessionProperties.setProperty(EmailConfig.MAIL_SMTP_PORT, EmailConfig.port);
			sessionProperties.setProperty(EmailConfig.MAIL_DEBUT, EmailConfig.debug);
			sessionProperties.setProperty(EmailConfig.MAIL_HOST, EmailConfig.host);
			sessionProperties.setProperty(EmailConfig.MAIL_TRANSPORT_PROTOCOL, EmailConfig.protocol);
		} catch (FileNotFoundException e) {
			logger.error("邮箱配置信息初始化异常", e);
		} catch (IOException e) {
			logger.error("邮箱配置信息初始化异常", e);
		} catch (Exception e){
			logger.error("邮箱配置信息初始化异常", e);
		}
	}

	public static String getDebug() {
		return debug;
	}

	public static String getAuth() {
		return auth;
	}

	public static String getHost() {
		return host;
	}

	public static String getProtocol() {
		return protocol;
	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

	public static String getFrom() {
		return from;
	}

	public static EmailConfig createEmailConfig() {
		return emailConfig;
	}

	public static Properties getSessionProperties() {
		return sessionProperties;
	}

	public static String getPort() {
		return port;
	}
}
