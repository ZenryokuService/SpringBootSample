package com.tkm.sample.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 漫画アニメのデータを管理するクラス。
 *
 * @author tkm-yogo
 */
@Entity
@Table(name="MNG_ANM")
@Data
public class MngAnmData implements Serializable{
	/** DB用のID */
	@Id
	@Column()
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer mngAnmId;
	/** 漫画アニメのタイトル */
	@Column
	private String title;
	/** 漫画アニメの発巻数 */
	@Column
	private Integer value;
	/** 漫画アニメのジャンル */
	@OneToOne
	@JoinColumn(name="categoryId")
	private Categories categ;
	/** 押す理由 */
	@Column
	private String recommend;
	/** 完結 */
	@Column
	private Boolean isFinished;


}
