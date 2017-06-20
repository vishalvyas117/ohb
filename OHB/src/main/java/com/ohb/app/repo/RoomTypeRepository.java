package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.type.RoomType;

@Repository(value = "roomTypeRepository")
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer>,
JpaSpecificationExecutor<RoomType> {
	
	

}
