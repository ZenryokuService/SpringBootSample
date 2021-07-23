package com.tkm.sample.data;

import java.util.List;

import lombok.Data;

@Data
public class IndexDto {

	private List<Boolean> checkList;
	private List<MngAnmData> mngAnmList;
}
