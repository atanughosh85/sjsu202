package com.sjsu.cmpe202.search.retail.listing.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe202.property.repositories.UserRepository;
import com.sjsu.cmpe202.search.retail.listing.model.Property;
import com.sjsu.cmpe202.search.retail.listing.model.User;

@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    @Autowired
    EntityManager em;
    
    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Iterable<User> getById(Long id) {
    	
        List <Long> ids = new ArrayList<Long>();
        ((ArrayList<Long>) ids).add(id);
		return userRepository.findAllById(ids);
    }

   

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		try {
		User savedUser=userRepository.save(user);
		}
		catch (Exception e) {
			System.out.println("************************************Inside Save User Exception");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//System.out.println("Email of saved User:"+savedUser.getEmail());
	}

	

}
