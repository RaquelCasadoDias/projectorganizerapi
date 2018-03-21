package com.examples.projectorganizerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.projectorganizerapi.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
