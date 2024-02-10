package com.masai.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ApiEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String category;
    
    
    //Constructor
    
	public ApiEntity(String title, String description, String category) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
	}

    // Getter and Setter methods
	
	
	public ApiEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
    
	
	
    

}
