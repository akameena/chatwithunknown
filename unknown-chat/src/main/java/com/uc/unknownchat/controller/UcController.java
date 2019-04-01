package com.uc.unknownchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.unknownchat.service.MessageReceiver;
import com.uc.unknownchat.service.MessageSender;

@Controller
public class UcController {

	@Autowired
	private MessageSender messageSender;
	@Autowired
	private MessageReceiver messageReceiver;
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	@GetMapping("/welcome")
	public String welcomePage(ModelMap model, @RequestParam String name, String connect)
	{
		model.put("name", name);
		model.put("connect",connect);
		return "welcome";
	}
	
	
	@PostMapping("/msg")
	public String message(ModelMap model, @RequestParam String msg,String name,String connect)
	{
		String pushQueue= name+"_"+connect;
		String pullQueue = connect+"_"+name;
		String msgFrom = "";
		msg = name+":"+" "+msg;
		messageSender.pushInQueue(msg,pushQueue);
		msgFrom=messageReceiver.pullFromQueue(pullQueue);
		model.put("name",name);
		model.put("msgFrom",msgFrom);
		model.put("connect", connect);
		
		return "welcome";
		
		
	}
	
	
	
	
}
