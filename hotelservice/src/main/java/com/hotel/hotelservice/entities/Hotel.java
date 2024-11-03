package com.hotel.hotelservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private String hId;
    @Column(name = "hotel_name")
    private String name;
    @Column(name = "hotel_location")
    private String location;
    @Column(name = "about")
    private String about;

}
