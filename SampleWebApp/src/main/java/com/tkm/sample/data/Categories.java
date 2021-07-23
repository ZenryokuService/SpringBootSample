package com.tkm.sample.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CATEGORY")
@Data
public class Categories {
	/** カテゴリID */
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer categoryId;
	/** カテゴリ名 */
	@Column
	private String categoryName;
	/** 説明 */
	@Column
	private String discription;
	/** 親カテゴリID */
	private Integer parentId;
	/** 親カテゴリである */
	private Boolean isParent;

}
