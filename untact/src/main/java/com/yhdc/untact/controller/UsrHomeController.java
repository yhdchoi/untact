package com.yhdc.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrHomeController {
	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
}
