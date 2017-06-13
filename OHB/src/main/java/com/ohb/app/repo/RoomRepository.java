package com.ohb.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.ohb.app.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {

}
