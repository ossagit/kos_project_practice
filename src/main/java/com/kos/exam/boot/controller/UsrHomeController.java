package com.kos.exam.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usr/home")
public class UsrHomeController {

	@GetMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "";
	}
}

