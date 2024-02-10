package com.masai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ErrorOccuredException;
import com.masai.modal.ApiEntity;
import com.masai.service.ApiService;

@RestController
public class ApiController {
	@Autowired
	private ApiService service;

    private static final String URL = "https://api.publicapis.org/entries";


    @GetMapping("/api/list")
    public ResponseEntity<List<Map<String, String>>> listEntriesByCategory(@RequestParam String category)
    {
        List<Map<String, String>> filteredResults = service.getAllEntriesByCategory(category);
        return new ResponseEntity<>(filteredResults, HttpStatus.OK);
    }

    @PostMapping("/api/save")
    public ResponseEntity<String> saveNewEntry(@RequestBody Map<String, Object> newEntryMap)
    {
        try {
        	
            if (!newEntryMap.containsKey("Title") || !newEntryMap.containsKey("Description") || !newEntryMap.containsKey("Category")) 
            {
                return new ResponseEntity<>("Not a valid formet", HttpStatus.BAD_REQUEST);
            }

         
            ApiEntity newApiEntity = new ApiEntity();
            newApiEntity.setTitle((String) newEntryMap.get("Title"));
            newApiEntity.setDescription((String) newEntryMap.get("Description"));
            newApiEntity.setCategory((String) newEntryMap.get("Category"));

    
            service.saveEntry(newApiEntity);

            return new ResponseEntity<>("Saved the entry in database", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ErrorOccuredException("Error occured in process please try again "+e);
        }
    }

}
