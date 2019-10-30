package com.guestbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guestbook.exception.RecordNotFoundException;
import com.guestbook.model.GuestEntity;
import com.guestbook.repository.GuestRepository;

@Service
public class GuestService {
	
	@Autowired
	GuestRepository repository;
	
	public List<GuestEntity> getAllGuest(){
            List<GuestEntity> result = (List<GuestEntity>) repository.findAll();

            if(result.size() > 0) {
                    return result;
            } else {
                    return new ArrayList<GuestEntity>();
            }
	}
	
	public GuestEntity getGuestById(Long id) throws RecordNotFoundException {
            Optional<GuestEntity> guest = repository.findById(id);

            if(guest.isPresent()) {
                    return guest.get();
            } else {
                    throw new RecordNotFoundException("No guest record exist for given id");
            }
	}
	
	public GuestEntity createOrUpdateGuest(GuestEntity entity){
            if(entity.getId()  == null) {
                
                entity = repository.save(entity);

                return entity;
            } else {
                Optional<GuestEntity> guest = repository.findById(entity.getId());

                if(guest.isPresent()) {
                    GuestEntity newEntity = guest.get();
                    newEntity.setName(entity.getName());
                    newEntity.setEmail(entity.getEmail());
                    newEntity.setNotes(entity.getNotes());

                    newEntity = repository.save(newEntity);

                    return newEntity;
                } else {
                    entity = repository.save(entity);

                    return entity;
                }
            }
        } 
	
	public void deleteGuestById(Long id) throws RecordNotFoundException {
            Optional<GuestEntity> guest = repository.findById(id);

            if(guest.isPresent()){
                repository.deleteById(id);
            } else {
                throw new RecordNotFoundException("No guest record exist for given id");
            }
	} 
}