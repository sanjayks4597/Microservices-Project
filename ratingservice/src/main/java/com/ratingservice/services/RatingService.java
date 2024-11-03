package com.ratingservice.services;

import java.util.List;

import com.ratingservice.entities.Rating;

public interface RatingService {
    // create rating
    public Rating createRating(Rating rating);

    // get all ratings
    public List<Rating> getAllRatings();

    // get all rating by user id
    public List<Rating> getAllRatingByUserId(String userId);

    // get all rating by hotel id
    public List<Rating> getAllRatingByHotelId(String hotelId);

    // update rating by user id
    public Rating updateRatingByUserId(Rating rating, String userId);

    // delete Rating
    public void deleteRatingByUserId(String userId);
}
