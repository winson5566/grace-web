package com.grace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Test
	public void contextLoads() {
		String pass0 = new BCryptPasswordEncoder().encode("w5566");
		String salt =BCrypt.gensalt();
		String pass1 = BCrypt.hashpw("w5566",salt);
		System.out.println(pass0);
		System.out.println(pass1);
	}

}
