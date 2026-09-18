package com.findandshare.demo.service;

import com.findandshare.demo.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile createProfile(Profile profile);

    Profile getProfileById(String id);

    List<Profile> getAllProfiles();

    Profile updateProfile(String id, Profile profile);

    void deleteProfile(String id);
}
