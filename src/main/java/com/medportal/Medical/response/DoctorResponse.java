package com.medportal.Medical.response;

import javax.print.Doc;

public class DoctorResponse {
    private long id;
    private String firstName;
    private String lastName;

    public DoctorResponse(){
    }

    @Override
    public String toString() {
        return "DoctorResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
