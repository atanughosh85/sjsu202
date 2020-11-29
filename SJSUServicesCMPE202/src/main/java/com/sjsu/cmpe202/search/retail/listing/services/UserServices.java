package com.sjsu.cmpe202.search.retail.listing.services;

import java.util.List;

import com.sjsu.cmpe202.search.retail.listing.model.Property;
import com.sjsu.cmpe202.search.retail.listing.model.User;


public interface UserServices
{
    List<User> listAll();
    Iterable<User> getById(Long id);


    void saveUser(User user);
}
