package com.example.oracle_C;

import static org.assertj.core.api.Assertions.assertThat;    

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.oracle_C.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositorytest {
	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void  testCreateUser() {
		User user =new User();
		user.setEmail("nuscu@gmail.com");
		user.setPassword("456");
		user.setFirst_name("nuscu");
		user.setLast_name("nuscu2");
		User savedUser = repo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	@Test
	public void testFindUserbyEmail() {
		String email="nuscu@gmail.com";
		User user=repo.findByEmail(email);
		assertThat(user).isNotNull();
	}
	@Test
	public void deleteuser() {
		Long userID= (long) 22;
		repo.deleteById(userID);
	}
}
