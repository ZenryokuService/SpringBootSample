package com.example.demo.controller.kadai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Kadai1Controller {

	private int calcLogic = 0;
	private String calcTitle = "";

	@RequestMapping(value="/kadai1/{num}", method=RequestMethod.GET)
	public ModelAndView index(@PathVariable int num, ModelAndView mav) {

		mav.setViewName("/kadai/calc");

		calcLogic = num;

		if (num == 0) {
			calcTitle = "足し算を行います。";
		} else if(num == 1) {
			calcTitle = "引き算を行います。";
		} else if(num == 2) {
			calcTitle = "掛け算を行います。";
		} else {
			calcTitle = "割り算を行います。";
		}
		//            ${title}
		mav.addObject("title", calcTitle);

		return mav;
	}
	@RequestMapping(value="/kadai1/calc", method=RequestMethod.POST)
	public ModelAndView calc(@RequestParam("value1") int num1,
			@RequestParam("value2") int num2,
			ModelAndView mav) {

		int calcResult = 0;

		if(calcLogic == 0) {
			calcResult = num1 + num2;
		} else if(calcLogic == 1) {
			calcResult = num1 - num2;
		} else if(calcLogic == 2) {
			calcResult = num1 * num2;
		} else {
			calcResult = num1 / num2;
		}
		//            ${title}
		mav.addObject("title", calcTitle);
		//            ${value1}
		mav.addObject("value1", num1);
		//            ${value2}
		mav.addObject("value2", num2);
		//            ${result}
		mav.addObject("result", calcResult);
		mav.setViewName("/kadai/calc");
		return mav;
	}
}
