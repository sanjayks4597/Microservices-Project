package com.hotel.hotelservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.repositories.Hotel_Repository;

@Service
public class Hotel_Service_Impl implements Hotel_Service {
    @Autowired
    Hotel_Repository hotel_Repository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        String hId = UUID.randomUUID().toString();
        hotel.setHId(hId);
        return hotel_Repository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotel_Repository.findAll();
    }

    @Override
    public Hotel getHotelById(String hId) {
        Hotel hotel = hotel_Repository.findById(hId)
                .orElseThrow(() -> new ResourceAccessException("Resouce not found on server !!" + hId));
        return hotel;
    }

    @Override
    public void deleteHotelById(String hId) {
        Hotel hotel = hotel_Repository.findById(hId)
                .orElseThrow(() -> new ResourceAccessException("Resouce not found on server !!" + hId));
        hotel_Repository.delete(hotel);
    }

    @Override
    public Hotel updateHotelById(Hotel hotel, String hId) {
        Hotel hotel1 = hotel_Repository.findById(hId)
                .orElseThrow(() -> new ResourceAccessException("Resouce not found on server !!" + hId));
        if (hotel.getName() != null) {
            hotel1.setName(hotel.getName());
        }
        if (hotel.getLocation() != null) {
            hotel1.setLocation(hotel.getLocation());
        }
        if (hotel.getAbout() != null) {
            hotel1.setAbout(hotel.getAbout());
        }

        hotel_Repository.save(hotel1);
        return hotel1;
    }

}
