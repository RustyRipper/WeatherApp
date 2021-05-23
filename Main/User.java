package Main;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class User implements Observer {

    protected final String name;

    @Expose()
    protected ArrayList<LocationSensor> userLocationSensorsData;
    protected ArrayList<String> activeUserLocations;


    public User(String name) {
        this.name = name;
        userLocationSensorsData = new ArrayList<>();
        activeUserLocations = new ArrayList<>();
    }

    public void addSensor(LocationSensor locationSensor) {
        if (!activeUserLocations.contains(locationSensor.getLocation()))
            activeUserLocations.add(locationSensor.getLocation());

        if (!userLocationSensorsData.contains(locationSensor))
            userLocationSensorsData.add(new LocationSensor(locationSensor.getLocation(), locationSensor.getCodeSensor()));
    }

    public void deleteActiveSensor(String location) {

        activeUserLocations.remove(location);
    }


    public void viewMeasurement(LocationSensor locationSensor) {
        System.out.println(locationSensor.getLocation());
        locationSensor.viewMeasurement();
    }

    public void viewActiveUserLocation() {
        int i = 1;
        for (String active : activeUserLocations) {
            System.out.println(i + "." + active);
            i++;
        }
    }

    public void viewUserLocation() {
        int i = 1;
        for (LocationSensor locationSensor : userLocationSensorsData) {

            System.out.println(i + "." + locationSensor.getLocation());
            i++;
        }
    }


    @Override
    public void update(String location, Measurement measurement) {
        for (LocationSensor sensor : userLocationSensorsData) {
            if (sensor.getLocation().equals(location)) {

                sensor.getValues().add(measurement);
                System.out.println("NEW! "+sensor.getLocation()+" "+measurement);
            }
        }

    }

    public ArrayList<String> getActiveUserLocations() {
        return activeUserLocations;
    }

    public ArrayList<LocationSensor> getUserLocationSensorsData() {
        return userLocationSensorsData;
    }

    public void setUserLocationSensorsData(ArrayList<LocationSensor> userLocationSensorsData) {
        this.userLocationSensorsData = userLocationSensorsData;
    }


}
