package com.joseph.Nexus;

import com.joseph.Nexus.utils.Tweet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexusApplication.class, args);

		System.out.println("Welcome To The NEXUS");
		System.out.println();


		String tw = "Nexus CRM is the future!";
		Tweet tweet = new Tweet();
		tweet.shareApp(tw);
	}

}
