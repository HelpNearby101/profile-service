package com.findandshare.demo.controller;

import com.findandshare.demo.entity.Profile;
import com.findandshare.demo.enumeration.ResponseStatus;
import com.findandshare.demo.response.ApiResponse;
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
    public ResponseEntity<ApiResponse> createProfile(
            @RequestBody Profile profile) {

        Profile createdProfile = profileService.createProfile(profile);

        ApiResponse response = new ApiResponse(
                "Profile created successfully",
                ResponseStatus.SUCCESS,
                createdProfile,
                HttpStatus.CREATED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProfileById(
            @PathVariable String id) {

        Profile profile = profileService.getProfileById(id);

        ApiResponse response = new ApiResponse(
                "Profile fetched successfully",
                ResponseStatus.SUCCESS,
                profile,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }

    // Get all profiles
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProfiles() {

        List<Profile> profiles = profileService.getAllProfiles();

        ApiResponse response = new ApiResponse(
                "Profiles fetched successfully",
                ResponseStatus.SUCCESS,
                profiles,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }

    // Update profile
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProfile(
            @PathVariable String id,
            @RequestBody Profile profile) {

        Profile updatedProfile =
                profileService.updateProfile(id, profile);

        ApiResponse response = new ApiResponse(
                "Profile updated successfully",
                ResponseStatus.SUCCESS,
                updatedProfile,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
    }

    // Delete profile
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProfile(
            @PathVariable String id) {

        profileService.deleteProfile(id);

        ApiResponse response = new ApiResponse(
                "Profile deleted successfully",
                ResponseStatus.SUCCESS,
                null,
                HttpStatus.NO_CONTENT.value()
        );

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
