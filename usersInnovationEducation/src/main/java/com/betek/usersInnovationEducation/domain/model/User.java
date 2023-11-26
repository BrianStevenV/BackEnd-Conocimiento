package com.betek.usersInnovationEducation.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String member_name;
    private String email;
    private String password;
    private String phone;

    // ------ >

    private Boolean state;

    /// ------- .
    private LocalDate updated_at;

    private Boolean is_admin;
    // ----- > HERE UP
    private Long idCountry;

    public User(Long id, String member_name, String email, String password, String phone, Boolean state, LocalDate updated_at, Boolean is_admin, Long idCountry) {
        this.id = id;
        this.member_name = member_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.state = state;
        this.updated_at = updated_at;
        this.is_admin = is_admin;
        this.idCountry = idCountry;
    }

    public User() {
    }

    // TODO SE PUEDE QUITAR DEL CONSTRUCTOR ID, Y FORMARLO UNICAMENTE EN PERSISTENCIA.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    // ------- >


    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }



    // ------->


    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }



    // ------ >
    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }


    // ----- > HERE UP


    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }
}
