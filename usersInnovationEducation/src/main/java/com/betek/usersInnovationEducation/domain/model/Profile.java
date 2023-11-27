package com.betek.usersInnovationEducation.domain.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Profile {
    private Long id;
    private String description;

    private String profile_image;

    private String link_website;

    public Profile(Long id, String description, String profile_image, String link_website) {
        this.id = id;
        this.description = description;
        this.profile_image = profile_image;
        this.link_website = link_website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getLink_website() {
        return link_website;
    }

    public void setLink_website(String link_website) {
        this.link_website = link_website;
    }
}
