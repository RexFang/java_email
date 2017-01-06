package email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

/**
 * user:Rex
 * date:2016年12月25日 上午3:56:49
 * TODO 邮件工具类
 */
public class EmailUtil {
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午4:29:18
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to) throws Exception{
		Message msg = createMessage(subject, content, to, null);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午4:17:11
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param type
	 * @param otherRecipient 抄送人或暗送人（多个抄送人或暗送人用英文逗号“,”隔开）
	 * @return 邮箱对象
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to, RecipientType type, String otherRecipient) throws Exception{
		Message msg = createMessage(subject, content, to, type, otherRecipient, null);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午4:17:11
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param cc 抄送人（多个抄送人用英文逗号“,”隔开）
	 * @param bcc 暗送人（多个暗送人用英文逗号“,”隔开）
	 * @return 邮箱对象
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to, String cc, String bcc) throws Exception{
		Message msg = createMessage(subject, content, to, cc, bcc, null);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:04:02
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to, List<File> fileList) throws Exception{
		Message msg = createMessage(subject, content, to, fileList);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:04:02
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param type
	 * @param otherRecipient 抄送人或暗送人（多个抄送人或暗送人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to, RecipientType type, String otherRecipient, List<File> fileList) throws Exception{
		Message msg = createMessage(subject, content, to, type, otherRecipient, fileList);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:04:02
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param cc 抄送人（多个抄送人用英文逗号“,”隔开）
	 * @param bcc 暗送人（多个暗送人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @throws Exception 
	 */
	public static void sendEmail(String subject, String content, String to, String cc, String bcc, List<File> fileList) throws Exception{
		Message msg = createMessage(subject, content, to, cc, bcc, fileList);
		// 连接邮件服务器、发送邮件
        Transport.send(msg);  
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:02:07
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param cc 抄送人（多个抄送人用英文逗号“,”隔开）
	 * @param bcc 暗送人（多个暗送人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @return 邮箱对象
	 * @throws Exception 
	 */
	private static Message createMessage(String subject, String content, String to, String cc, String bcc, List<File> fileList) throws Exception{
		Message msg = createMessage(subject, content, to, RecipientType.CC, cc, fileList);
		msg.setRecipients(RecipientType.BCC, InternetAddress.parse(bcc));
        msg.setSentDate(new Date());     //设置信件头的发送日期
		
        return msg;
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:02:07
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param otherRecipient 抄送人或暗送人（多个抄送人或暗送人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @return 邮箱对象
	 * @throws Exception 
	 */
	private static Message createMessage(String subject, String content, String to, RecipientType type, String otherRecipient, List<File> fileList) throws Exception{
		Message msg = createMessage(subject, content, to, fileList);
		msg.setRecipients(type, InternetAddress.parse(otherRecipient));
		
        return msg;
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午7:02:07
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param to 收件人（多个收件人用英文逗号“,”隔开）
	 * @param fileList 附件
	 * @return 邮箱对象
	 * @throws Exception 
	 */
	private static Message createMessage(String subject, String content, String to, List<File> fileList) throws Exception{
		checkEmail(subject, content, fileList);
		//邮件内容
		Multipart mp = createMultipart(content, fileList);
		Message msg = new MimeMessage(createSession());
		msg.setFrom(new InternetAddress(EmailConfig.getFrom()));
		msg.setSubject(subject);
		msg.setRecipients(RecipientType.TO, InternetAddress.parse(to));
		msg.setContent(mp); //Multipart加入到信件  
        msg.setSentDate(new Date());     //设置信件头的发送日期
		
        return msg;
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午9:01:12
	 * @param content 邮件正文内容
	 * @param fileList 附件
	 * @return 邮件内容对象
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * Multipart
	 * TODO 创建邮件正文
	 */
	private static Multipart createMultipart(String content, List<File> fileList) throws MessagingException, UnsupportedEncodingException{
		//邮件内容
		Multipart mp = new MimeMultipart();  
		MimeBodyPart mbp = new MimeBodyPart();  
        mbp.setContent(content, "text/html;charset=gb2312");  
        mp.addBodyPart(mbp);    
        
        if(fileList!=null && fileList.size()>0){
        	//附件
            FileDataSource  fds;
            for(File file : fileList){
            	mbp=new MimeBodyPart();  
            	fds = new FileDataSource(file);//得到数据源  
            	mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
            	mbp.setFileName(MimeUtility.encodeText(file.getName()));  //得到文件名同样至入BodyPart  
            	mp.addBodyPart(mbp);  
            }
        }
        
        return mp;
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午9:48:18
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param fileList 邮件附件
	 * void
	 * TODO 校验邮件内容合法性
	 * @throws Exception 
	 */
	private static void checkEmail(String subject, String content, List<File> fileList) throws Exception{
		if(StringUtils.isEmpty(subject)){
			throw new Exception("邮件标题不能为空");
		}
		
		if(StringUtils.isEmpty(content) && (fileList==null || fileList.size()==0)){
			throw new Exception("邮件内容不能为空");
		}
	}
	
	/**
	 * user: Rex
	 * date: 2016年12月25日  上午4:01:47
	 * @return
	 * Session
	 * TODO 创建邮箱上下文
	 */
	private static Session createSession(){
		return Session.getDefaultInstance(EmailConfig.getSessionProperties(), EmailAuthenticator.createEmailAuthenticator());
	}
}
