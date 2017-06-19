package com.ohb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohb.app.model.type.RoomType;

@Repository(value = "roomTypeRepository")
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer>,
JpaSpecificationExecutor<RoomType> {
	
	

}
