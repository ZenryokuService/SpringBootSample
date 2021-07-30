package com.tkm.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tkm.sample.data.Categories;
import com.tkm.sample.data.IndexDto;
import com.tkm.sample.data.MngAnmData;
import com.tkm.sample.service.CrudService;

@Controller
public class BooksController {
	@Autowired
	private CrudService service;

	@GetMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		List<Categories> categList = service.findAllCategories();
		mav.addObject("categories", categList);

		List<MngAnmData> result = service.findAllMngAnms();
		final IndexDto dto = new IndexDto();
		List<Boolean> chList = new ArrayList<Boolean>();
		for ( int i = 0; i < result.size(); i++) {
			chList.add(new Boolean(false));
		}
		dto.setCheckList(chList);

		dto.setMngAnmList(result);
		mav.addObject("indexDto", dto);

		mav.setViewName("/enshu/index");
		return mav;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(@ModelAttribute IndexDto dto,ModelAndView mav) {
		List<MngAnmData> mngAnmList = dto.getMngAnmList();
		List<Boolean> checkList = dto.getCheckList();

		System.out.println("checkList.size: " + checkList.size());
		System.out.println("mngAmnList: " + mngAnmList);
		for(int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i) != null) {
				System.out.println("*** Testing ***");
				service.deleteMngAnm(mngAnmList.get(i));
			}
		}

		return index(mav);
	}


	@RequestMapping(value="/addData", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> addData(@RequestBody Map<String, String> request,ModelAndView mav) {
		MngAnmData data = new MngAnmData();
		data.setTitle(request.get("title"));
		data.setValue(Integer.parseInt(request.get("value")));
		data.setRecommend(request.get("recommend"));
		data.setIsFinished(Boolean.parseBoolean(request.get("isFinished")));
		Categories cat = service.findCategory(request.get("janle"));
		data.setCateg(cat);

		// データの登録
		service.updateMngAnm(data);

		return ResponseEntity.ok(data.getTitle());
	}

	@RequestMapping(value="/replace/update", method=RequestMethod.GET)
	public String replaceUpdate(@RequestParam("id") int id, ModelAndView mav) {
		MngAnmData data = service.findMngAnm(String.valueOf(id));
		service.updateMngAnm(data);
		mav.addObject("mng", data);
		return "fragment";
	}
}
