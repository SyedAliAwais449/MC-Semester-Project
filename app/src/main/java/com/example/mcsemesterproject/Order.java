package com.example.mcsemesterproject;

public class Order {

        private int roomId;
        private String roomName;


    public Order(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public int getRoomId() {
            return roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

}
