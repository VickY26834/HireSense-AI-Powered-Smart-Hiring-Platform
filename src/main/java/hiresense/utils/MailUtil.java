package hiresense.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
 public static void sendTextEmail(String recepientEmailId,String subject,String body)throws MessagingException{
	 Session sess=MailConfig.getSession();
	 Message message=new MimeMessage(sess);
    
     InternetAddress []addr=InternetAddress.parse(recepientEmailId);
     message.setRecipients(Message.RecipientType.TO, addr);
     message.setSubject(subject);
     message.setText(body);
     Transport.send(message);
     
 }
 public static void sendApplicationConfirmation(String name,String toEmail,String jobTitle,String companyName)throws MessagingException{
	 String subject="✅Application Submitted -"+jobTitle;
	 String body="Dear "+name+",\n\nYou have successfully applied for the position of "+jobTitle+" at "+companyName+"\n";
	 body+="We wish you best of luck\n\n";
	 body+="Regards, Team HireSense";
	 sendTextEmail(toEmail,subject,body);
     
 }
 public static void sendNewApplicationNotificationToEmployer(String employerName,String toEmail,String applicantName,String jobTitle)throws MessagingException{
	 String subject="✅New Application received";
	 String body="\n\nDear "+employerName+",\n\nA new candidate ("+applicantName+") has applied for "+jobTitle;
	 body+="\n\nYou can review applicant's details in your dashboard\n\n";
	 body+="Regards, Team HireSense";
	 sendTextEmail(toEmail,subject,body);
     
 }
 public static void sendApplicationStatusUpdate(String name,String toEmail, String jobTitle, String company, String status) throws MessagingException {
     String subject = "📢 Application Status Update - " + jobTitle;
     String body;

     if ("shortlisted".equalsIgnoreCase(status)) {
         body = "Dear "+name+",\n\n"
              + "Congratulations! You have been shortlisted for the role of " + jobTitle + " at " + company + ".\n"
              + "The employer may contact you for the next steps.\n\n"
              + "Best wishes,\nHireSense Team";
     } else if ("rejected".equalsIgnoreCase(status)) {
         body = "Dear candidate,\n\n"
              + "We regret to inform you that you were not shortlisted for the position of " + jobTitle + " at " + company + ".\n"
              + "We encourage you to apply for other jobs on HireSense.\n\n"
              + "Regards,\nHireSense Team";
     } else {
         body = "Dear candidate,\n\n"
              + "Your application status has been updated to: " + status + " for the job " + jobTitle + " at " + company + ".\n\n"
              + "Regards,\nHireSense Team";
     }

     sendTextEmail(toEmail, subject, body);
 }
 public static void sendAccountActionNotice(String toEmail, String userName, String action) throws MessagingException {
     String subject = "⚠️ Account Update - HireSense";
     String body;

     switch (action.toLowerCase()) {
         case "blocked":
             body = "Hello " + userName + ",\n\n"
                  + "Your account on HireSense has been *blocked* due to policy violations or suspicious activity.\n"
                  + "Please contact support for more details.\n\n"
                  + "Regards,\nAdmin Team";
             break;

         case "unblocked":
             body = "Hello " + userName + ",\n\n"
                  + "Your account has been *unblocked* and you may now access all services again.\n"
                  + "Thank you for your patience.\n\n"
                  + "Regards,\nAdmin Team";
             break;

         case "removed":
             body = "Hello " + userName + ",\n\n"
                  + "Your account has been *permanently removed* from HireSense by the administrator.\n"
                  + "If you believe this is a mistake, please contact support.\n\n"
                  + "Regards,\nAdmin Team";
             break;

         default:
             body = "Hello " + userName + ",\n\n"
                  + "There has been an update to your HireSense account: *" + action + "*.\n\n"
                  + "Regards,\nAdmin Team";
             break;
     }

     sendTextEmail(toEmail, subject, body);
 }
}
