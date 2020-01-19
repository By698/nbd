package com.nbd.nbd.repository;

import org.springframework.data.repository.CrudRepository;
import com.nbd.nbd.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {

}