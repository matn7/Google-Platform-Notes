package com.pandatronik.builder.house;

public class House {

    private String walls;
    private String floors;
    private String rooms;
    private String roofs;
    private String windows;
    private String doors;
    private String garage;

    private House(HouseBuilder houseBuilder) {
        this.walls = houseBuilder.walls;
        this.floors = houseBuilder.floors;
        this.rooms = houseBuilder.rooms;
        this.roofs = houseBuilder.roofs;
        this.windows = houseBuilder.windows;
        this.doors = houseBuilder.doors;
        this.garage = houseBuilder.garage;
    }

    public String getWalls() {
        return walls;
    }

    public String getFloors() {
        return floors;
    }

    public String getRooms() {
        return rooms;
    }

    public String getRoofs() {
        return roofs;
    }

    public String getWindows() {
        return windows;
    }

    public String getDoors() {
        return doors;
    }

    public String getGarage() {
        return garage;
    }

    @Override
    public String toString() {
        return "House{" +
                "walls='" + walls + '\'' +
                ", floors='" + floors + '\'' +
                ", rooms='" + rooms + '\'' +
                ", roofs='" + roofs + '\'' +
                ", windows='" + windows + '\'' +
                ", doors='" + doors + '\'' +
                ", garage='" + garage + '\'' +
                '}';
    }

    public static class HouseBuilder {
        private String walls;
        private String floors;
        private String rooms;
        private String roofs;
        private String windows;
        private String doors;
        private String garage;

        public HouseBuilder buildWalls(String walls) {
            this.walls = walls;
            return this;
        }

        public HouseBuilder buildFloors(String floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder buildRooms(String rooms) {
            this.rooms = rooms;
            return this;
        }

        public HouseBuilder buildRoofs(String roofs) {
            this.roofs = roofs;
            return this;
        }

        public HouseBuilder buildWindows(String windows) {
            this.windows = windows;
            return this;
        }

        public HouseBuilder buildDoors(String doors) {
            this.doors = doors;
            return this;
        }

        public HouseBuilder buildGarage(String garage) {
            this.garage = garage;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}
