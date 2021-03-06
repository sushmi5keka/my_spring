package com.ex.securityloginwithmysql.Controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import com.ex.securityloginwithmysql.Entity.User;
import com.ex.securityloginwithmysql.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {
	
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepo userRepo;



	private static String USER_NAME = "ijksu33";  // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "ijk_33su"; // GMail password
	//private static String RECIPIENT = "springapidev@gmail.com";




//	@Autowired
//	public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
//							  UserRepo userRepo) {
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//		this.userRepo = userRepo;
//
//	}

	@Autowired
	PasswordEncoder passwordEncoder;
	
	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail

		
try {
	User userExists = userRepo.findByEmail(user.getEmail());
	if (userExists != null) {
		modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
		modelAndView.setViewName("register");
		bindingResult.reject("email");
	}
}catch(NullPointerException ne){
	if (bindingResult.hasErrors()) {
		modelAndView.setViewName("register");
	} else { // new user so we create user and send confirmation e-mail

		// Disable user until they click on confirmation link in email
		user.setEnable(false);

		// Generate random 36-character string token for confirmation link
		user.setConfirmationToken(UUID.randomUUID().toString());

		userRepo.save(user);

		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + "8008";
		//email sending

		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = {user.getEmail()}; // list of recipient email addresses
		String subject = "Registration Confirmation";
		String body = "To confirm your e-mail address, please click the link below:\n"
				+ appUrl + "/confirm?token=" + user.getConfirmationToken();

		sendFromGMail(from, pass, to, subject, body);

		modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
		modelAndView.setViewName("register");
	}
}

		return modelAndView;
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		User user = userRepo.findByConfirmationToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
				
		modelAndView.setViewName("confirm");
		
		Zxcvbn passwordCheck = new Zxcvbn();
		
		Strength strength = passwordCheck.measure(requestParams.get("password"));
		
		if (strength.getScore() < 3) {
			//modelAndView.addObject("errorMessage", "Your password is too weak.  Choose a stronger one.");
			bindingResult.reject("password");
			
			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
		//	System.out.println(requestParams.get("token"));
			return modelAndView;
		}
	
		// Find the user associated with the reset token
		User user = userRepo.findByConfirmationToken(requestParams.get("token"));

		// Set new password
		user.setPassword(passwordEncoder.encode(requestParams.get("password")));

		// Set user to enabled
		user.setEnable(true);
		
		// Save user
		userRepo.save(user);
		
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}



	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress("ijksu33@gmail.com", "SAMI LLC."));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Email sending Success!!!");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}