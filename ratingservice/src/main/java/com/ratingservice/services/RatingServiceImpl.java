package com.ratingservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entities.Rating;
import com.ratingservice.exceptions.ResourceNotFoundException;
import com.ratingservice.repositories.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepo ratingRepo;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        List<Rating> allRatings = ratingRepo.findAll();
        return allRatings;
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRatingByUserId(Rating currentRating, String userId) {
        Rating rating1 = ratingRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found !! " + userId));
        if (currentRating.getRating() != 0) {
            rating1.setRating(currentRating.getRating());
        }
        if (currentRating.getFeedback() != null) {
            rating1.setFeedback(currentRating.getFeedback());
        }
        return rating1;
    }

    @Override
    public void deleteRatingByUserId(String userId) {
        List<Rating> ratings = ratingRepo.findByUserId(userId);
        ratings.forEach(rate -> {
            ratingRepo.delete(rate);
        });
    }

}
