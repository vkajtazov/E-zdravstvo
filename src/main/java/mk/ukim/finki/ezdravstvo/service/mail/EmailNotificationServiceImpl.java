package mk.ukim.finki.ezdravstvo.service.mail;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import mk.ukim.finki.ezdravstvo.model.EmailMessage;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

	private final Logger log = LoggerFactory
			.getLogger(EmailNotificationServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public void sendEmail(EmailMessage email) {
		log.info("### SEND MAIL");
		try {
			prepareMailAndSend(email);
		} catch (Exception ex) {
			throw new RuntimeException("mail_sent_error", ex);
		}

	}

	@Override
	@Async
	public void sendEmailAsync(EmailMessage email) {
		log.info("### SEND MAIL ASYNC");
		try {
			prepareMailAndSend(email);
		} catch (Exception ex) {
			log.error("Async send mail:", ex);
		}
	}

	private void prepareMailAndSend(final EmailMessage email)
			throws MailException {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
				message.setTo(email.getTo());
				message.setFrom(getFrom());
				if (email.getBcc() != null) {
					message.setBcc(email.getBcc());
				}
				message.setSubject(email.getSubject());
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, email.getTemplate(), "UTF8",
						email.getModel());
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		System.out.println("### SEND MAIL - FINISHED");
	}

	
	private String getFrom() {
	    return "admin@ezdravstvo.finki.ukim.mk";
	  }
}
