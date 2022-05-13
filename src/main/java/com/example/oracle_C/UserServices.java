package com.example.oracle_C;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.oracle_C.model.User;

@Service
@Transactional
public class UserServices {
	@Autowired
	private UserRepository Userrepo;
	
	/* test user có tồn tại để edit */
	public User get(Long id) throws UserNotFoundException {
		Optional<User> result = Userrepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		throw new UserNotFoundException("Không tìm thấy User" +id);
	}
	public void deleteUser(Long ID)  {
		/*
		 * Long count = Userrepo.countById(ID); if(count == null || count==0) { throw
		 * new UserNotFoundException("Không tìm thấy User" +ID ); }
		 */
		Userrepo.deleteById(ID);
	}
	/* tạo token cho user */ 
	public void updateResetPassworToken(String token, String email) throws UserNotFoundException {
		User user = Userrepo.findByEmail(email);
		if (user != null) {
			user.setResetPasswordToken(token);
			Userrepo.save(user);
		} else {
			throw new UserNotFoundException("Không tìm thấy User với email này "+ email);
		}
	}
	/* check token gửi đúng cho user hay ko */
	public User Get(String resetPasswordToken) {
		return Userrepo.findByResetPasswordToken(resetPasswordToken);
		
	}
	public void updatePassword(User user,String newpassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(newpassword);
		user.setPassword(encodePassword);
		user.setResetPasswordToken(null);
		Userrepo.save(user);
	}
	 
}
