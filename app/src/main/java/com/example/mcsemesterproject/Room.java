package com.example.mcsemesterproject;

public class Room {
    private String roomNumber;
    private Double roomRating;
    private String roomReviews;
    private String roomInfo;
    private String roomPrice;
    private int roomImageName;

    public Room(String roomNumber, Double roomRating, String roomReviews, String roomInfo, String roomPrice, int roomImageName) {
        this.roomNumber = roomNumber;
        this.roomRating = roomRating;
        this.roomReviews = roomReviews;
        this.roomInfo = roomInfo;
        this.roomPrice = roomPrice;
        this.roomImageName = roomImageName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getRoomRating() {
        return roomRating;
    }

    public void setRoomRating(Double roomRating) {
        this.roomRating = roomRating;
    }

    public String getRoomReviews() {
        return roomReviews;
    }

    public void setRoomReviews(String roomReviews) {
        this.roomReviews = roomReviews;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomImageName() {
        return roomImageName;
    }

    public void setRoomImageName(int roomImageName) {
        this.roomImageName = roomImageName;
    }

}
