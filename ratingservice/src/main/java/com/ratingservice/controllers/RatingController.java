package com.ratingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.entities.Rating;
import com.ratingservice.services.RatingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingServiceImpl ratingServiceImpl;

    @PostMapping
    public ResponseEntity<Rating> CreateRating(@RequestBody Rating rating) {
        Rating ratingTemp = ratingServiceImpl.createRating(rating);
        return new ResponseEntity<>(ratingTemp, HttpStatus.OK);
    }

    // get all ratings

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingServiceImpl.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsOfUser(@PathVariable String userId) {
        List<Rating> ratings = ratingServiceImpl.getAllRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId) {
        List<Rating> ratings = ratingServiceImpl.getAllRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteRatingByUser(@PathVariable String userId) {
        ratingServiceImpl.deleteRatingByUserId(userId);
        return ResponseEntity.ok("Rating is deleted.. " + userId);
    }

}
