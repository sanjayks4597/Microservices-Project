package com.user.service.entites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Micro_Users")
public class Users {

    @Id
    @Column(name = "ID")
    String UId;
    @Column(name = "NAME")
    private String UName;
    @Column(name = "EMAIL")
    private String UEmail;
    @Column(name = "ABOUT")
    private String Uabout;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
