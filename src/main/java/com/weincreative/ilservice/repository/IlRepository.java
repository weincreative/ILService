package com.weincreative.ilservice.repository;

import com.weincreative.ilservice.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IlRepository extends MongoRepository<Il, String> {
    List<Il> findAllByName(String name);
    Optional<Il> findByName(String name);
}
