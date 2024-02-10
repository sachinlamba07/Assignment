package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masai.modal.ApiEntity;
import com.masai.repository.ApiEntityRepository;

@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
    private ApiEntityRepository ApiEntityRepo;
	
	@Autowired
    private RestTemplate restTemplate;
	
    private static final String URL = "https://api.publicapis.org/entries";



	
	@Override
	public List<Map<String, String>> getAllEntriesByCategory(String category) {
		
		ResponseEntity<Map> result = restTemplate.getForEntity(URL, Map.class);

        if (result.getStatusCode()==HttpStatus.OK) 
        {
            Map responseBody = result.getBody();
            
            List<Map<String, String>> map = (List<Map<String, String>>) responseBody.get("entries");
            
            List<Map<String, String>> filteredResult = new ArrayList<>();
            
            for (Map<String, String> e : map)
            {
                if (e.get("Category").equalsIgnoreCase(category)) 
                {
                    Map<String, String> filteredEntry = Map.of("Title", e.get("API"), "Description", e.get("Description"));
                    filteredResult.add(filteredEntry);
                }
            }

            return filteredResult;
            
	}
		return null;

	}

	@Override
	public ApiEntity saveEntry(ApiEntity entity) {
		return ApiEntityRepo.save(entity);
	}

}
