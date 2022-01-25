package com.example.mcsemesterproject;

public class BookedRoomInfo {
        private String name;
        private String email;
        private String cellNumber;
        private String nightStay;
        private String roomNumber;

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setCellNumber(String cellNumber) {
            this.cellNumber = cellNumber;
        }

        public void setNightStay(String nightStay) {
            this.nightStay = nightStay;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }


        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCellNumber() {
            return cellNumber;
        }

        public String getNightStay() {
            return nightStay;
        }

        public String getRoomNumber() {
            return roomNumber;
        }


        public BookedRoomInfo(String name, String email, String cellNumber, String nightStay, String roomNumber) {
            this.name = name;
            this.email = email;
            this.cellNumber = cellNumber;
            this.nightStay = nightStay;
            this.roomNumber = roomNumber;
        }
}
