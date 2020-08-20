package com.dangmailinh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.dangmailinh" })
public class QuanLyNhanSuApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLyNhanSuApplication.class, args);
	}

}
