package com.tkm.sample.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tkm.sample.SampleApp;
import com.tkm.sample.data.Categories;
import com.tkm.sample.data.CategoryRepository;
import com.tkm.sample.data.MngAnmData;
import com.tkm.sample.data.MngAnmRepository;

@SpringBootTest(classes= {SampleApp.class, H2TestConfig.class})
@Transactional
public class ReposTest {

	/** カテゴリRepository */
	@Autowired
	private CategoryRepository cateRepo;
	@Autowired
	private MngAnmRepository mngAnmRepo;

	@BeforeEach
	public void porepare() {
		// カテゴリデータの登録
		Categories cat = new Categories();
		cat.setCategoryName("テストカテゴリ");
		cat.setDiscription("テスト説明");
		cat.setIsParent(false);
		cat.setParentId(1);
		cateRepo.save(cat);

		// 漫画アニメデータの登録
		MngAnmData mng = new MngAnmData();
		mng.setTitle("テスト漫画");
		mng.setRecommend("テストおし");
		mng.setValue(12);
		mng.setCateg(cat);
		mng.setIsFinished(false);
		mngAnmRepo.save(mng);
	}

	@Test
	public void testInjected() {
		assertThat(cateRepo).isNotNull();
		assertThat(mngAnmRepo).isNotNull();

	}

	@Test
	public void testSelect() {
		List<Categories> categList = cateRepo.findAll();
		List<MngAnmData> mngAnmList = mngAnmRepo.findAll();

		// テストテーブルではないので値を取得できることのみを確認
		assertNotNull(categList);
		assertNotEquals(0, categList.size());
		assertNotNull(mngAnmList);
		assertNotEquals(0, mngAnmList.size());
	}

	@Test
	public void testSaveCategories() {
		Categories cat = new Categories();
		//cat.setCategoryId(testId);
		cat.setCategoryName("テストカテゴリ");
		cat.setDiscription("テスト");
		cat.setIsParent(true);
		cat.setParentId(1);
		cateRepo.save(cat);
		Categories result = null;
		System.out.println("ID: " + cat.getCategoryId());
//		try {
//			cateRepo.findById(cat.getCategoryId()).get();
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("カテゴリIDでの検索に失敗しました。");
//		}

		// チェック
//		assertNotNull(result);
//		assertEquals(cat.getCategoryId(), result.getCategoryId());
//		assertEquals(cat.getCategoryName(), result.getCategoryName());
//		assertEquals(cat.getDiscription(), result.getDiscription());
//		assertEquals(cat.getIsParent(), result.getIsParent());
//		assertEquals(cat.getParentId(), result.getParentId());

		// 後処理
		//cateRepo.delete(cat);

	}

	@Test
	public void testSaveMngAnm() {
		int testId = 999;
		MngAnmData mng = new MngAnmData();
		mng.setMngAnmId(testId);
		mng.setTitle("テスト漫画");
		mng.setRecommend("テストおし");
		mng.setIsFinished(false);
		mng.setValue(2);
		mngAnmRepo.save(mng);
		MngAnmData result = null;
//		try {
//			result = mngAnmRepo.findById(mng.getMngAnmId()).get();
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("IDでの検索に失敗しました。");
//		}

		// チェック
//		assertEquals(mng.getMngAnmId(), result.getMngAnmId());
//		assertEquals(mng.getTitle(), result.getTitle());
//		assertEquals(mng.getIsFinished(), result.getIsFinished());
//		assertEquals(mng.getRecommend(), result.getRecommend());
//		assertEquals(mng.getValue(), result.getValue());

		// 後処理
		//mngAnmRepo.delete(mng);
	}
}
