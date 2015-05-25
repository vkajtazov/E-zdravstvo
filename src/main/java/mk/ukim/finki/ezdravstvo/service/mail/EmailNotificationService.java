package mk.ukim.finki.ezdravstvo.service.mail;

import mk.ukim.finki.ezdravstvo.model.EmailMessage;

public interface EmailNotificationService {

	void sendEmail(final EmailMessage email);

	void sendEmailAsync(final EmailMessage email);

}
