package com.guestbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.guestbook.model.GuestEntity;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Long> {

}
