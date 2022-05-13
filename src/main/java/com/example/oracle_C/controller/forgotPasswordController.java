package com.example.oracle_C.controller;

import java.io.UnsupportedEncodingException; 

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.oracle_C.UserNotFoundException;
import com.example.oracle_C.UserServices;
import com.example.oracle_C.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class forgotPasswordController {
	@Autowired
	private UserServices userService;
	@Autowired
	private JavaMailSender mailsender;
	
	@GetMapping("/forgot_password")
	public String showforgotpasswordForm(Model model)
	{
		model.addAttribute("pageTitle","Forgot Password");
		return "forgotpassword_Form";
	}
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request,Model model)
	{
		String email= request.getParameter("email");
		String token = RandomString.make(45);
		/*
		 * System.out.println("Email: " + email); System.out.println("token: " + token);
		 */
		try {
			userService.updateResetPassworToken(token, email);
			//Tạo ra link để send password
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token="+token;
			/* System.out.println(resetPasswordLink); */
			//Gửi email
			sendemail(email,resetPasswordLink);
			model.addAttribute("message", "we have sent a reset password link to your email.");
		}catch(UserNotFoundException ex) {  
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException |MessagingException e) {
			model.addAttribute("error", "error while sending email.");
		}
		return "forgotpassword_Form";
		
	}
	private void sendemail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("mxhbbook@gmail.com","User Support");
		helper.setTo(email);
		
		String subject ="here your link to reset password";
		String content="<p>Hello,</p>"
				+"<p>You have request reset your password.</p>"
				+"<p>Click the link below to change your password</p>"
				+"<p><b><a href=\""+resetPasswordLink+"\">Change my Password</a></b></p>"
				+"<p>Ignore this email if you do remember your password</p>";
		helper.setSubject(subject);
		helper.setText(content,true);
		mailsender.send(message);
	}
	
}
