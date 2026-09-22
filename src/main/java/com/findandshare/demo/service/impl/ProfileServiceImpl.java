package com.findandshare.demo.service.impl;

import com.findandshare.demo.entity.Profile;
import com.findandshare.demo.exception.ProfileNotFoundException;
import com.findandshare.demo.repository.ProfileRepository;
import com.findandshare.demo.service.ProfileService;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {

        LocalDateTime now = LocalDateTime.now();

        profile.setCreatedAt(now);
        profile.setUpdatedAt(now);

        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileById(String id) {

        return profileRepository.findById(id)
                .orElseThrow(() ->
                        new ProfileNotFoundException(
                                "Profile not found with id: " + id
                        )
                );
    }

    @Override
    public List<Profile> getAllProfiles() {

        return profileRepository.findAll();
    }

    @Override
    public Profile updateProfile(String id, Profile profile) {

        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new ProfileNotFoundException(
                                "Profile not found with id: " + id
                        )
                );

        // Fields allowed to be updated
        existingProfile.setName(profile.getName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setBio(profile.getBio());
        existingProfile.setProfileImage(profile.getProfileImage());
        existingProfile.setLocation(profile.getLocation());

        // id, role, status and createdAt are not changed

        existingProfile.setUpdatedAt(LocalDateTime.now());

        return profileRepository.save(existingProfile);
    }

    @Override
    public void deleteProfile(String id) {

        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new ProfileNotFoundException(
                                "Profile not found with id: " + id
                        )
                );

        profileRepository.delete(existingProfile);
    }

    @Override
    public List<Profile> findNearbyProfiles(
            double latitude,
            double longitude,
            double radius) {

        GeoJsonPoint location =
                new GeoJsonPoint(longitude, latitude);

        Distance distance =
                new Distance(radius, Metrics.KILOMETERS);

        return profileRepository.findByLocationNear(
                location,
                distance
        );
    }
}