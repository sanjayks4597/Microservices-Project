package com.hotel.hotelservice.services;

import java.util.List;

import com.hotel.hotelservice.entities.Hotel;

public interface Hotel_Service {
    // create hotel
    Hotel addHotel(Hotel hotel);

    // get all hotels
    List<Hotel> getAllHotels();

    // get single hotel
    Hotel getHotelById(String hId);

    // delete Hotel
    void deleteHotelById(String hId);

    // update hotel details
    Hotel updateHotelById(Hotel hotel, String hId);
}
