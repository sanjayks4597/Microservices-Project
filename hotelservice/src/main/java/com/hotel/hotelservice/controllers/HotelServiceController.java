package com.hotel.hotelservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.services.Hotel_Service_Impl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/hotels")
public class HotelServiceController {
    @Autowired
    Hotel_Service_Impl hotel_Service_Impl;

    // create new hotel
    @PostMapping
    public ResponseEntity<Hotel> CreateHotel(@RequestBody Hotel hotel) {
        Hotel hotel2 = hotel_Service_Impl.addHotel(hotel);
        return new ResponseEntity<>(hotel2, HttpStatus.CREATED);
    }

    // get all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotel_Service_Impl.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    // get single hotel by id
    @GetMapping(path = "/{hotel_id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotel_id) {
        Hotel hotel1 = hotel_Service_Impl.getHotelById(hotel_id);
        return new ResponseEntity<>(hotel1, HttpStatus.OK);
    }

    // update hotel details by id
    @PutMapping("/{hotel_id}")
    public ResponseEntity<Hotel> updateHotelById(@RequestBody Hotel hotel, @PathVariable String hotel_id) {
        Hotel tempHotel = hotel_Service_Impl.updateHotelById(hotel, hotel_id);
        return new ResponseEntity<>(tempHotel, HttpStatus.OK);
    }
    // delete hotel by id

    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable String hotel_id) {
        hotel_Service_Impl.deleteHotelById(hotel_id);
        return new ResponseEntity<>("Hotel deleted successfully" + hotel_id, HttpStatus.OK);
    }
}
