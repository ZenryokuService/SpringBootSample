package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** RESTful アプリケーション */
@Controller
public class HelloController2 {
	@Autowired
	private MessageSource message;
	private String[] names;
	private String[] mails;

	public HelloController2() {
		names = new String[]{"hanako", "taro", "sachiko", "ichiro"};
		mails = new String[]{"hanako@do.com", "taro@do.com", "sachiko@do.com", "ichiro@do.com"};
	}

	// http://localhost:8080/
	@RequestMapping("/ind")
	public ModelAndView index(ModelAndView mav) {
		System.out.println("*** index() ***");
		// list3-23
		mav.setViewName("index2");
		return mav;
	}

	@RequestMapping("/other")
	public String other() {
		System.out.println("*** other() ***");
		return "redirect:/ind";
	}

	@RequestMapping("/thome")
	public String thome() {
		System.out.println("*** thome() ***");
		return "forward:/ind";
	}


//	// http://localhost:8080/?num=12
//	@GetMapping("/{num}")
//	@ResponseBody
//	public String index(@PathVariable double num) {
//		// tplファイルは参照できない
//		return "total: " + num;
//	}

//	@GetMapping("/{id}")
//	@ResponseBody
//	public DataObject index(@PathVariable int id) {
//		// thymeleaf
//		// JSON文字列、JSONデータ
//		// var obj = ????;
//		// obj.id, obj.name
//		// {"id":1,"name":"taro","mail":"taro@do.com"}
//		return new DataObject(id, names[id], mails[id]);
//	}

	// http://localhost:8080/?num=12
	@RequestMapping("/demo/{num}")
	public ModelAndView index(@PathVariable int num, ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "total: " + num);
		// tplファイルは参照できない
		return mav;
	}


}
