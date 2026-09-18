package com.findandshare.demo.controller;

import com.findandshare.demo.entity.Profile;
import com.findandshare.demo.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Create a new profile
    @PostMapping
    public ResponseEntity<Profile> createProfile(
            @RequestBody Profile profile) {

        Profile createdProfile = profileService.createProfile(profile);

        return new ResponseEntity<>(
                createdProfile,
                HttpStatus.CREATED
        );
    }

    // Get profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(
            @PathVariable String id) {

        Profile profile = profileService.getProfileById(id);

        return ResponseEntity.ok(profile);
    }

    // Get all profiles
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {

        List<Profile> profiles = profileService.getAllProfiles();

        return ResponseEntity.ok(profiles);
    }

    // Update profile
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable String id,
            @RequestBody Profile profile) {

        Profile updatedProfile =
                profileService.updateProfile(id, profile);

        return ResponseEntity.ok(updatedProfile);
    }

    // Delete profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable String id) {

        profileService.deleteProfile(id);

        return ResponseEntity.noContent().build();
    }
}