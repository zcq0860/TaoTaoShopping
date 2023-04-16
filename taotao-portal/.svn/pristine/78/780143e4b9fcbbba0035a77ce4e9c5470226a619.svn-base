package com.taotao.portal.controller;

import java.awt.PageAttributes.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contentService.getContentList();
//		System.out.println("portal-IndexController-adjson"+adJson);
		model.addAttribute("ad1", adJson);
//		System.out.println("portal-IndexController-model"+model);
		
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post", method = RequestMethod.POST , produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE +";charset=utf-8" )
	@ResponseBody
	public String testPost(String username, String password) {
		return "{username:"+username + ",password:"+ password+"}";
	}
}
