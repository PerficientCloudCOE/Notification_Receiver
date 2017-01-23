package com.perficient.cloud.email;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;


/**
 * @author praveen.muthusamy
 *
 */
public class EmailUtil {

		public EmailUtil() {
		}
		
		private String from;
		private String[] to;
		private String subject;
		private String content;
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String[] getTo() {
			return to;
		}
		public void setTo(String[] to) {
			this.to = to;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
		public void sendEmail(){
			Email fromId = new Email(this.from);
		    Personalization person = new Personalization();
		    Email to = new Email();
		    for(String mailId : this.to){
		    	to.setEmail(mailId);
			    person.addTo(to);
		    }
		    
		    Content mailcontent = new Content("text/plain", this.content);
		    Mail mail = new Mail();
		    mail.setFrom(fromId);
		    mail.setSubject(this.subject);
		    mail.addContent(mailcontent);
		    mail.addPersonalization(person);

		    SendGrid sg = new SendGrid("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");
		    Request request = new Request();
		    try {
		      request.method = Method.POST;
		      request.endpoint = "mail/send";
		      request.body = mail.build();
		      Response response = sg.api(request);
		      System.out.println(response.statusCode);
		    } catch (IOException ex) {
		    	 System.out.println(ex.getMessage());
		    }
		}
		
	}
