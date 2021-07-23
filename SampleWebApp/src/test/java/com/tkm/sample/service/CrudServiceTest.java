package com.tkm.sample.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tkm.sample.data.Categories;
import com.tkm.sample.data.CategoryRepository;
import com.tkm.sample.data.MngAnmData;
import com.tkm.sample.data.MngAnmRepository;

/** 【注意！】
 * 1.本来はテスト用のテーブルを用意するが、
 *   時間の都合でテスト用スキーマ(テーブルを用意できていない。
 * 2.H2DB(サーバー)を起動してからテストする
 */
public class CrudServiceTest {

	@InjectMocks
	private CrudService target;
	@Mock
	private CategoryRepository cateRepo;
	@Mock
	private MngAnmRepository mngAnmRepo;

	@BeforeEach
	public void prepare() {
		MockitoAnnotations.openMocks(this);
	}

	private Categories createCat(int id, boolean isParent) {
		Categories cat = new Categories();
		cat.setCategoryId(id);
		cat.setCategoryName("テストカテゴリ" + id);
		cat.setDiscription("テスト説明" + id);
		cat.setIsParent(isParent);
		cat.setParentId(id);
		return cat;
	}

	private List<Categories> createCatList() {
		List<Categories> res = new ArrayList<>();
		boolean isParent = false;
		for (int i = 0; i < 5; i++) {
			res.add(createCat(i, !isParent));
		}
		return res;
	}

	private MngAnmData createMng(int id, Categories cat) {
		MngAnmData mng = new MngAnmData();
		mng.setMngAnmId(id);
		mng.setTitle("テスト漫画");
		mng.setRecommend("テストおし");
		mng.setValue(12);
		mng.setCateg(cat);
		mng.setIsFinished(false);
		return mng;
	}

	private List<MngAnmData> createMngList() {
		List<MngAnmData> res = new ArrayList<>();
		boolean isParent = false;
		for (int i = 0; i < 5; i++) {
			res.add(createMng(i, createCat(i, !isParent)));
		}
		return res;
	}

	@Test
	public void testInjected() {
		assertThat(target).isNotNull();
	}

	@Test
	public void testFindCategories() {
		// テスト準備
		List<Categories> res = createCatList();
		when(cateRepo.findAll()).thenReturn(res);

		// テスト実行
		List<Categories> testResult = target.findAllCategories();
		assertEquals(5, testResult.size());
	}

	@Test
	public void testFindMngAmn() {
		// テスト準備
		List<MngAnmData> res = createMngList();
		when(mngAnmRepo.findAll()).thenReturn(res);

		// テスト実行
		List<MngAnmData> testResult = target.findAllMngAnms();
		assertEquals(5, testResult.size());

	}

	@Test
	public void testUpdate() {
		// テスト準備
		Categories cat = createCat(4, false);
		MngAnmData mng = createMng(3, cat);
		// saveメソッドは返り値を使用しないため呼び出しのみ確認
		target.updateCategories(cat);
		target.updateMngAnm(mng);
		// 呼び出しの確認
		verify(cateRepo, times(1)).save(cat);
		verify(mngAnmRepo, times(1)).save(mng);
	}

	@Test
	public void testDelete() {
		// テスト準備
		Categories cat = new Categories();
		cat.setCategoryId(1);
		MngAnmData mng = createMng(1, null);
		mng.setMngAnmId(1);
		// deleteメソッドは返り値を使用しないため呼び出しのみ確認
		target.deleteCategories(cat);
		target.deleteMngAnm(mng);
		// 呼び出しの確認
		verify(cateRepo, times(1)).delete(cat);
		verify(mngAnmRepo, times(1)).delete(mng);
	}


}
