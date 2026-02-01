package ek.dk.friday_2026_01_30;

import java.time.LocalDate;

public class GardenTask extends Task {
    String gardenLocation;

    public GardenTask(String title, String description, LocalDate duedate, String gardenLocation) {
        super(title, description, duedate);
        this.gardenLocation = gardenLocation;
    }

    public String getGardenLocation() {
        return gardenLocation;
    }

    public void setGardenLocation(String gardenLocation) {
        this.gardenLocation = gardenLocation;
    }

    @Override
    public String toString() {
        return super.toString() +
                "%n  Garden      : %s" + gardenLocation;

    }
}
