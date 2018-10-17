package com.msufp.register;

import com.msufp.register.model.User;
import com.msufp.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		userRepository.save(new User("Darshan", "Panchal", "drsnpncl@gmail.com", "drsnpncl", "male", "7486866647", "Baroda", "MS University", "Computer Science", 4));
		userRepository.save(new User("Darshan2", "Panchal2", "drsnpncl2@gmail.com", "drsnpncl2", "male", "7486866647", "Baroda2", "MS University2", "Computer Science2", 4));
		userRepository.save(new User("Darshan3", "Panchal3", "drsnpncl3@gmail.com", "drsnpncl3", "male", "7486866647", "Baroda2", "MS University2", "Computer Science2", 4));
	}
}
