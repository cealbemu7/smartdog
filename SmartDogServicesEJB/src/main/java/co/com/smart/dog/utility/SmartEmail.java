package co.com.smart.dog.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

/**
 * 
 * @author SmartJungle S.A.S
 *
 */
public class SmartEmail extends Thread {

	/**
	 * 
	 */
	
	private static final Logger logger = Logger.getLogger(SmartEmail.class);

	private static final String HTML = "text/html";
	private static final String PLAIN = "text/plain";
	private boolean resendStack = true;
	
	Properties props = System.getProperties();
	Session session;
	MimeMessage message;
	String pathTemplate = null;
	MimeMultipart multipart = new MimeMultipart();
	BodyPart messageBodyPart = new MimeBodyPart();
	String content = null;
	boolean isHtml = false;
	Map<String, String> templateParametersMap = new HashMap<>();
	Map<String, String> propertiesConfig = new HashMap<>();

	/**
	 * SmartMail
	 * 
	 * @param properties
	 */
	public SmartEmail(Map<String, String> properties) {

		if (properties != null && properties.entrySet() != null
				&& properties.entrySet().iterator() != null) {

			try {

				Iterator<Entry<String, String>> configs = properties.entrySet()
						.iterator();

				while (configs.hasNext()) {
					Entry<String, String> config = configs.next();
					propertiesConfig.put(config.getKey(), config.getValue());
					props.put(config.getKey(), config.getValue());
				}

				session = Session.getDefaultInstance(props);
				message = new MimeMessage(session);
			} catch (Exception e) {
				logger.error(
						"Error inicializando la configuración del servidor SMTP: "
								+ e.getMessage(), e);
			}
		} else {
			logger.error("Las configuraciones iniciales no estan definidas");
		}

	}

	/**
	 * add Subject
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		try {
			message.setSubject(subject);
		} catch (MessagingException e) {
			logger.error(
					"Error adicionando el asunto del correo: " + e.getMessage(),
					e);
		}
	}

	/**
	 * add From
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		try {
			message.setFrom(new InternetAddress(from));
		} catch (MessagingException e) {
			logger.error(
					"Error adicionando el emisor del correo: " + e.getMessage(),
					e);
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipients(String... recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error("Error adicionando los destinatarios del correo: "
						+ e.getMessage(), e);
			}
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipients(List<String> recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error("Error adicionando los destinatarios del correo: "
						+ e.getMessage(), e);
			}
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipientsCC(String... recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error(
						"Error adicionando los destinatarios con copia del correo: "
								+ e.getMessage(), e);
			}
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipientsCC(List<String> recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error(
						"Error adicionando los destinatarios con copia del correo: "
								+ e.getMessage(), e);
			}
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipientsBCC(String... recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error(
						"Error adicionando los destinatarios con copia oculta del correo: "
								+ e.getMessage(), e);
			}
		}
	}

	/**
	 * addRecipients
	 * 
	 * @param recipients
	 */
	public void addRecipientsBCC(List<String> recipients) {
		for (String recipient : recipients) {
			try {
				message.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(recipient));
			} catch (MessagingException e) {
				logger.error(
						"Error adicionando los destinatarios con copia oculta  del correo: "
								+ e.getMessage(), e);
			}
		}
	}

	/**
	 * set's Path Template
	 * 
	 * @param pathTemplate
	 */
	public void setPathTemplate(String pathTemplate,
			Map<String, String> templateParametersMap) {
		this.pathTemplate = pathTemplate;
		this.templateParametersMap = templateParametersMap;
	}

	/**
	 * set's Content
	 * 
	 * @param content
	 */
	public void setContent(String content,boolean isHtml) {
		this.content = content;
		this.isHtml = isHtml;
	}

	private String loadTemplate() {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					this.pathTemplate));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				reader.close();
			}
		} catch (IOException e) {
			logger.error(
					"Error adicionando los destinatarios con copia oculta  del correo: "
							+ e.getMessage(), e);
		}

		String msg = contents.toString();
		try {
			Set<Entry<String, String>> entries = this.templateParametersMap
					.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				msg = msg.replace(entry.getKey().trim(), entry.getValue()
						.trim());
			}
		} catch (Exception e) {
			logger.error(
					"Error mapeando los parametros del template: "
							+ e.getMessage(), e);
		}
		return msg;
	}
	
	@Override
	public void run() {
		while(resendStack){
			try {
				if (this.pathTemplate != null) {
					messageBodyPart.setContent(loadTemplate(), HTML);
				} else if(this.isHtml) {
					messageBodyPart.setContent(this.content, HTML);
				} else {
					messageBodyPart.setContent(this.content, PLAIN);
				}
				
				if(message.getFrom() == null || message.getFrom().length == 0){
					setFrom(SmartUtilities.decodeToString(propertiesConfig.get("mail.smtp.user"), true));
				}
	
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);
	
				Transport transport = session.getTransport("smtp");
				transport.connect(
						propertiesConfig.get("mail.smtp.host"),
						SmartUtilities.decodeToString(propertiesConfig.get("mail.smtp.user"), true),
						SmartUtilities.decodeToString(propertiesConfig.get("mail.smtp.password"), true));
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				resendStack = false;
			} catch (MessagingException e) {
				logger.error("Error enviando el correo: " + e.getMessage(), e);
				resendStack = true;
			}
		}
	}

	public void send() {
	  this.start();
	}

}
