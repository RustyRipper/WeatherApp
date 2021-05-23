package Test;

import Main.LocationSensor;
import Main.Measurement;
import Main.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {


    private static LocationSensor locationSensor;
    private static User user;

    @Before
    public void create() {
        user = new User("Bob");
        ArrayList<LocationSensor> list = new ArrayList<>();

        list.add(new LocationSensor("Wroclaw", "THP"));
        user.setUserLocationSensorsData(list);
        user.getUserLocationSensorsData().get(0).getValues().add(new Measurement(1f, 1f, 1f));
    }


    @Test
    public void addSensorTest() {
        locationSensor = new LocationSensor("Wroclaw", "THP");
        user.addSensor(locationSensor);
        Assert.assertTrue(user.getActiveUserLocations().contains("Wroclaw"));
        Assert.assertTrue(user.getUserLocationSensorsData().contains(locationSensor));
    }

    @Test
    public void deleteActiveSensorTest() {

        user.deleteActiveSensor("Wroclaw");

        Assert.assertFalse(user.getActiveUserLocations().contains("Wroclaw"));
    }

    @Test
    public void updateTest() {

        locationSensor = new LocationSensor("Wroclaw", "THP");
        Measurement measurement = new Measurement(2f, 2f, 2f);

        user.update("Wroclaw", measurement);

        Assert.assertEquals(user.getUserLocationSensorsData().get(0).getValues().getLast(), measurement);
    }

    @Test()
    public void updateTest2() {

        locationSensor = new LocationSensor("Jawor", "TH");
        Measurement measurement = new Measurement(2f, 2f, 2f);
        user.update("Jawor", measurement);

        Assert.assertNotEquals(user.getUserLocationSensorsData().get(0).getValues().getLast(), measurement);
    }


}