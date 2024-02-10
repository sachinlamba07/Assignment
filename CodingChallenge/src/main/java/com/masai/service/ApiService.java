package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.modal.ApiEntity;

public interface ApiService {

	List<Map<String, String>> getAllEntriesByCategory(String category);

    ApiEntity saveEntry(ApiEntity entity);
	
}
