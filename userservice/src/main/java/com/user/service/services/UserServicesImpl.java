package com.user.service.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entites.Hotel;
import com.user.service.entites.Rating;
import com.user.service.entites.Users;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalServices.HotelServices;
import com.user.service.repositories.UserRepository;

import jakarta.persistence.Transient;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;
    private String randomUserID;
    @Autowired
    private HotelServices hotelServices;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Override
    public List<Users> getAllUser() {
        List<Users> usersList = userRepository.findAll();
        usersList.forEach(user -> {
            Rating[] UsrAllRatings = restTemplate
                    .getForObject("http://RATINGSERVICE/ratings/users/" + user.getUId(), Rating[].class);
            List<Rating> Ratinglist = Arrays.stream(UsrAllRatings).toList();
            List<Rating> allRating = Ratinglist.stream().map(rating -> {
                // get hotel service data
                ResponseEntity<Hotel> forEntity = restTemplate
                        .getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(allRating);
        });
        return usersList;
    }

    // get single user
    @Override
    public Users getUserById(String id) {
        // get user from database
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found on server !!" + id));

        // fetch rating of above user
        // http://localhost:8083/ratings/users/1b3d8861-1400-459c-8c25-ee3115a8a31a
        Rating[] RatingsOfUser = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUId(), Rating[].class);
        List<Rating> Ratinglist = Arrays.stream(RatingsOfUser).toList();
        logger.info("{}" + RatingsOfUser);

        List<Rating> ratingList = Ratinglist.stream().map(rating -> {
            // call hotel service api
            // ResponseEntity<Hotel> forEntity = restTemplate
            // .getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(),
            // Hotel.class);

            // Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelServices.getHotel(rating.getHotelId());
            // set hotel to rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUserById(String id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found on server !!" + id));
        userRepository.delete(user);
    }

    @Override
    public Users updateUserById(Users user, String id) {
        Users user1 = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found on server!!" + id));
        user1.setUName(user.getUName());
        user1.setUEmail(user.getUEmail());
        user1.setUabout(user.getUabout());
        userRepository.save(user1);
        return user1;
    }

    @Override
    public Users createUser(Users user) {
        randomUserID = UUID.randomUUID().toString();
        user.setUId(randomUserID);
        Users savedUser = userRepository.save(user);
        return savedUser;
    }

}
