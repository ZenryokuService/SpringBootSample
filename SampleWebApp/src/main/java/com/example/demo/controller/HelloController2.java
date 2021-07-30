package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.MyData;
import com.example.demo.repository.MyDataRepository;

/** RESTful アプリケーション */
@Controller
public class HelloController2 {

	@Autowired
	private MyDataRepository repository;

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

	///////////////////////////////////
	// 教科書のリスト5-13用のメソッド
	//////////////////////////////////
	@RequestMapping(value="/demo/list", method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("formModel") MyData mydata
				, ModelAndView mav) {
		System.out.println("*** list ***");
		Iterable<MyData> list = repository.findAll();// SELECT  * FROM MYDATA;

//		// p244
//		Optional<MyData> res = repository.findById(1L);
		mav.addObject("dataList", list);
		// テンプレート指定
		mav.setViewName("5_12");
		return mav;
	}

	@RequestMapping(value="/demo/list", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView listForm(@ModelAttribute("formModel") MyData mydata
				, ModelAndView mav) {
		System.out.println("*** listForm ***");
		repository.saveAndFlush(mydata);
		mav.setViewName("5_12");
		return new ModelAndView("redirect:/demo/list");
	}

	@RequestMapping(value="/demo/edit/{id}", method = RequestMethod.GET)
	@Transactional(readOnly=false)
	public ModelAndView edit(@ModelAttribute("formModel") MyData mydata
				, @PathVariable int id, ModelAndView mav) {
		System.out.println("*** Edit ***");

		mav.setViewName("edit");
		mav.addObject("title", "Edit MyData");
		// SELECT * FROM MYDATA WHERE ID = id;
		Optional<MyData> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

	@RequestMapping(value="/demo/edit", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView editUpdate(@ModelAttribute("formModel") MyData mydata
				, ModelAndView mav) {
		System.out.println("*** Update ***");
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/demo/list");
	}


}
