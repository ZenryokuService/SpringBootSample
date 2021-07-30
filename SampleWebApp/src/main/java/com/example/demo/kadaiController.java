package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class kadaiController {
	int keisan=0;
	@RequestMapping(value="/kadai1/{num}",method=RequestMethod.GET)
	public ModelAndView index(@PathVariable int num , ModelAndView mav) {
		mav.setViewName("calc");
		keisan = num;
		if(num==0) {
			mav.addObject("title", "足し算");
		}
		else if(num==1) {
			mav.addObject("title", "引き算");
		}
		else if(num==2) {
			mav.addObject("title", "掛け算");
		}
		else {
			mav.addObject("title", "割り算");
		}
		return mav;

	}
	@RequestMapping(value="/kadai1/calc",method = RequestMethod.POST)
	public ModelAndView calc(@RequestParam("value1") int num1,
			@RequestParam("value2") int num2,ModelAndView mav) {
		if(keisan==0) {
			mav.addObject("result","結果は"+(num1+num2));
		}
		else if (keisan == 1) {
			mav.addObject("result","結果は"+(num1-num2));
		}
		else if(keisan == 2) {
			mav.addObject("result","結果は"+(num1*num2));
		}
		else {
			mav.addObject("result","結果は"+(num1/num2));
		}
		mav.setViewName("calc");
		return mav;

	}
}