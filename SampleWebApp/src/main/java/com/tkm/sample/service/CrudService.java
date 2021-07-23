package com.tkm.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkm.sample.data.Categories;
import com.tkm.sample.data.CategoryRepository;
import com.tkm.sample.data.MngAnmData;
import com.tkm.sample.data.MngAnmRepository;

/**
 * CRUD処理を行うサービスを実装する。
 *
 * @author tkm-yogo
 */
@Service
public class CrudService {
	/** カテゴリRepository */
	@Autowired
	private CategoryRepository cateRepo;
	@Autowired
	private MngAnmRepository mngAnmRepo;

	/** カテゴリのリスト取得 */
	public List<Categories> findAllCategories() {
		// DBからデータを取得する
		List<Categories> categList = cateRepo.findAll();
		return categList;

	}

	/** カテゴリIDを指定して取得 */
	public Categories findCategory(String categId) {
		Categories cat = cateRepo.findById(Integer.parseInt(categId)).get();
		return cat;
	}

	/** カテゴリの追加・更新 */
	public void updateCategories(Categories cate) {
		cateRepo.save(cate);
	}

	/** カテゴリの追加・更新 */
	public void deleteCategories(Categories cate) {
		cateRepo.delete(cate);
	}

	/** 全ての漫画アニメのリスト取得 */
	public List<MngAnmData> findAllMngAnms() {
		List<MngAnmData> result = mngAnmRepo.findAll();
		return result;
	}

	/** 指定IDでの漫画アニメを取得 */
	public MngAnmData findMngAnm(String mngAnmId) {
		MngAnmData mng = mngAnmRepo.findById(Integer.parseInt(mngAnmId)).get();
		return mng;
	}

	/** 漫画アニメのデータ削除 */
	public void deleteMngAnm(MngAnmData data) {
		mngAnmRepo.delete(data);
	}

	/** 漫画アニメの登録・更新 */
	public void updateMngAnm(MngAnmData data) {
		mngAnmRepo.save(data);
	}
}
