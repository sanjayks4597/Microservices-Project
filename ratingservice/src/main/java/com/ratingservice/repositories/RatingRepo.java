package com.ratingservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ratingservice.entities.Rating;

public interface RatingRepo extends MongoRepository<Rating, String> {
    // custom finder methods
    public List<Rating> findByUserId(String userId);

    //
    public List<Rating> findByHotelId(String hotelId);
}
