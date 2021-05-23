package Main;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;

public class CSI {

    @Expose()
    protected ArrayList<LocationSensor> locationSensors;


    public CSI(ArrayList<LocationSensor> locationSensors) {
        this.locationSensors = locationSensors;
        Collections.sort(locationSensors);
    }

    public void registerUser(LocationSensor locationSensor, User user) {

        for (LocationSensor sensor : locationSensors) {
            if (sensor.equals(locationSensor)) {

                sensor.register(user);
                user.addSensor(sensor);
                return;
            }
        }
    }

    public void unRegisterUser(String location, User user) {
        for (LocationSensor sensor : locationSensors) {
            if (location.equals(sensor.getLocation())) {

                sensor.unregister(user);
                user.deleteActiveSensor(location);
                return;
            }
        }

    }

    public void viewLocations() {
        int i = 1;

        for (LocationSensor sensor : locationSensors) {
            System.out.println(i + ". " + sensor.getLocation() + " " + sensor.getCodeSensor());
            i++;
        }
    }


    public ArrayList<LocationSensor> getLocationSensors() {
        return locationSensors;
    }

    public ArrayList<String> getListOfLocations() {
        ArrayList<String> listOfLocations = new ArrayList<>();
        for (LocationSensor sensor : locationSensors) {
            listOfLocations.add(sensor.getLocation() + " " + sensor.getCodeSensor());
        }
        return listOfLocations;
    }

    public String getJsonListOfLocations() {

        ArrayList<String> list = getListOfLocations();
        Gson gson = new Gson();

        return gson.toJson(list);

    }


}
