package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.modal.ApiEntity;

public interface ApiEntityRepository extends JpaRepository<ApiEntity, Integer>{

}
