package com.sjsu.cmpe202.property.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.sjsu.cmpe202.search.retail.listing.model.User;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
