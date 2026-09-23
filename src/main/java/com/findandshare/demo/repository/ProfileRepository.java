package com.findandshare.demo.repository;

import com.findandshare.demo.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.geo.Distance;

import java.util.List;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    List<Profile> findByLocationNear(
            GeoJsonPoint location,
            Distance distance
    );

    List<Profile> findByNameContainingIgnoreCase(String name);
}