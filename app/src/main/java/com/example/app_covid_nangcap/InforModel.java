package com.example.app_covid_nangcap;

public class InforModel {
    private String Name,Birthday,Gender,Citizen,Phone,Address,Departure,DepartureDay,Destination,DestinationDay,Schuedule,Health;
    private int Id;
    public InforModel(Integer id,String name, String birthday, String gender, String citizen, String phone, String address, String departure, String departureDay, String destination, String destinationDay, String schuedule, String health) {
        Id=id;
        Name = name;
        Birthday = birthday;
        Gender = gender;
        Citizen = citizen;
        Phone = phone;
        Address = address;
        Departure = departure;
        DepartureDay = departureDay;
        Destination = destination;
        DestinationDay = destinationDay;
        Schuedule = schuedule;
        Health = health;

    }

    public InforModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCitizen() {
        return Citizen;
    }

    public void setCitizen(String citizen) {
        Citizen = citizen;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getDepartureDay() {
        return DepartureDay;
    }

    public void setDepartureDay(String departureDay) {
        DepartureDay = departureDay;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDestinationDay() {
        return DestinationDay;
    }

    public void setDestinationDay(String destinationDay) {
        DestinationDay = destinationDay;
    }

    public String getSchuedule() {
        return Schuedule;
    }

    public void setSchuedule(String schuedule) {
        Schuedule = schuedule;
    }

    public String getHealth() {
        return Health;
    }

    public void setHealth(String health) {
        Health = health;
    }
}
