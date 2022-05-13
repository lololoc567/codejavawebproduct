package com.example.oracle_C;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.jpa.repository.Query;

import com.example.oracle_C.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
//	@Query("DELETE FROM User u WHERE u.ID =?1")
//	User deleteByID(Long ID);
	public User findByResetPasswordToken(String token);
}
