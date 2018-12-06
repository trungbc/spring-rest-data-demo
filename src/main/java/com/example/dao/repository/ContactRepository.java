package com.example.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.entity.Contact;

@RepositoryRestResource(collectionResourceRel="contact", path="contact")
public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
