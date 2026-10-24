package com.portfolio.issue_tracker_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@RestController
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class IssueTrackerApiApplication {
	@RequestMapping("/")
	public String home() {
		return "Issue tracker api";
	}

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApiApplication.class, args);
	}

}
