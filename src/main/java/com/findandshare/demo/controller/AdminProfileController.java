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
@RequestMapping("/api/admin/profiles")
public class AdminProfileController {

    private final ProfileService profileService;

    public AdminProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Get all profiles
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProfiles() {

        List<Profile> profiles = profileService.getAllProfiles();

        ApiResponse response = new ApiResponse(
                "Profiles fetched successfully",
                ResponseStatus.SUCCESS,
                (Profile) profiles,
                HttpStatus.OK.value()
        );

        return ResponseEntity.ok(response);
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

    // Delete a profile
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