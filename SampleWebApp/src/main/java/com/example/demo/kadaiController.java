package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class kadaiController {

//	@RequestMapping(value="/kadai1/{num}",method=RequestMethod.GET)
//	@ResponseBody
	public ModelAndView clac1(@PathVariable int num,ModelAndView cla) {
		 if(num==0) {
			cla.setViewName("clac");
			cla.addObject("title","足し算を行います");

			return cla;

		 }else if(num==1){
			cla.setViewName("clac");
			cla.addObject("title","引き算を行います");

			return cla;
		 }else if(num==2){
			 cla.setViewName("clac");
			 cla.addObject("title","掛け算をします");

			 return cla;
		 }else {
			 cla.setViewName("clac");
			 cla.addObject("title","割り算をします");

			 return cla;
		 }
	}

//	@RequestMapping(value="/kadai1/clac",method=RequestMethod.POST)
//	@ResponseBody
	public ModelAndView clacsend(@RequestParam("value1")int val1,@RequestParam("value2")int val2,
			@RequestParam("num") int num,ModelAndView cla) {

		int result1=0;


		if(num==0) {
			cla.setViewName("clac");
			result1=val1+val2;
			cla.addObject("title","足し算を行います");
			cla.addObject("value1",val1);
			cla.addObject("value2",val2);
			cla.addObject("result",result1);

			return cla;

		 }else if(num==1){
			cla.setViewName("clac");
			 result1=val1-val2;
			cla.addObject("title","引き算を行います");
			cla.addObject("value1",val1);
			cla.addObject("value2",val2);
			cla.addObject("result",result1);

			return cla;

		 }else if(num==2){
			 cla.setViewName("clac");
			 result1=val1*val2;
			 cla.addObject("title","掛け算をします");
			 cla.addObject("value1",val1);
			 cla.addObject("value2",val2);
			 cla.addObject("result",result1);

			 return cla;

		 }else {
			 cla.setViewName("clac");
			 result1=val1/val2;
			 cla.addObject("title","割り算をします");
			 cla.addObject("value1",val1);
			 cla.addObject("value2",val2);
			 cla.addObject("result",result1);

			 return cla;
		 }
	}
}