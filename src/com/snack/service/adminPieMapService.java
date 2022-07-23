package com.snack.service;

import java.util.List;

import com.snack.model.domain.DoRecord;

public interface adminPieMapService {
	List<DoRecord> selectGroupBytype(String startDate,String endDate);
}
