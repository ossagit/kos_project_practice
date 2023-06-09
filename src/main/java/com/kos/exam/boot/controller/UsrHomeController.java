package com.kos.exam.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UsrHomeController {

	@GetMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}

	@GetMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
}