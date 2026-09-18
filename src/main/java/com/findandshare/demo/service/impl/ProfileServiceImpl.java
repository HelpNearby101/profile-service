package com.findandshare.demo.service.impl;

import com.findandshare.demo.entity.Profile;
import com.findandshare.demo.exception.ProfileNotFoundException;
import com.findandshare.demo.repository.ProfileRepository;
import com.findandshare.demo.service.ProfileService;
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

        existingProfile.setName(profile.getName());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setBio(profile.getBio());
        existingProfile.setProfileImage(profile.getProfileImage());
        existingProfile.setRole(profile.getRole());
        existingProfile.setStatus(profile.getStatus());
        existingProfile.setLatitude(profile.getLatitude());
        existingProfile.setLongitude(profile.getLongitude());

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
}