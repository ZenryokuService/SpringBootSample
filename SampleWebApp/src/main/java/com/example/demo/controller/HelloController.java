package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.MyData;
import com.example.demo.repository.MyDataRepository;

@RestController
public class HelloController {
	@Autowired
	private MyDataRepository repository;

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("book");
		mav.addObject("key", 12);
		return mav;
	}

//	@RequestMapping("/")
//	@ResponseBody
//	public ModelAndView index(ModelAndView mav) {
//		mav.addObject("msg", "Hello World");
//		mav.setViewName("index");
//		return mav;
//	}

	@RequestMapping("/mondai")
	@ResponseBody
	public ModelAndView mondai(ModelAndView mav) {
		mav.addObject("msg", "こんいいいい");
		mav.setViewName("mondai");
		return mav;
	}

	@RequestMapping("/book")
	@ResponseBody
	public ModelAndView book(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@GetMapping("/home/{num}")
	@ResponseBody
	public ModelAndView home(@PathVariable int num, ModelAndView mav) {
		mav.setViewName("home");
		// request. session. application. setAttribute(キー, 値);
		mav.addObject("msg", "こんにちは");
		mav.addObject("isOne", num == 1);

		// リポジトリを使用する
		Iterable<MyData> list = repository.findAll();
		mav.addObject("data", list);
		return mav;
	}

	@RequestMapping("/home1")
	@ResponseBody
	@Deprecated
	public ModelAndView hello(ModelAndView mav) {
		// tplファイルは参照できない
		mav.setViewName("home1");
		mav.addObject("msg", "こんにちは1");
		return mav;
	}

	@RequestMapping(value="/send", method=RequestMethod.POST)
	public ModelAndView send(
			@RequestParam(value="text1", required=true) String text1
			, @RequestParam(value="check1", required=false) boolean check1
			, @RequestParam(value="male", required=false) String radioA
			, @RequestParam(value="female", required=false) String radioB
			, @RequestParam(value="select1", required=false) String select1
			, @RequestParam(value="select2", required=false) String select2
			, ModelAndView mav) {
		final String SEP = System.lineSeparator();
		StringBuilder build = new StringBuilder();
		build.append("check1: " + check1 + SEP);
		build.append("radio1: " + radioA + SEP);
		build.append("radio2: " + radioB + SEP);
		build.append("select1: " + select1 + SEP);
		build.append("select2: " + select2 + SEP);

		// tplファイルは参照できない
		mav.setViewName("home");
		mav.addObject("msg", text1 + " : " + build.toString());
		return mav;
	}

	@RequestMapping(value="/getParam")
	@ResponseBody
	public ModelAndView getParam(ModelAndView mav) {
		// tplファイルは参照できない
		mav.setViewName("home");
		mav.addObject("msg", "げっとぱらむ");
		return mav;
	}


}
