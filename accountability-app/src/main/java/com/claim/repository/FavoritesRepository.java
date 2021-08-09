package com.claim.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.claim.entity.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, String> {

}
