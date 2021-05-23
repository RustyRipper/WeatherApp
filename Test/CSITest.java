package Test;

import Main.CSI;
import Main.LocationSensor;
import Main.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CSITest {
    private static CSI csi;


    @Before
    public void create() {
        ArrayList<LocationSensor> locationSensors = new ArrayList<>();

        locationSensors.add(new LocationSensor("Olawa", "P"));
        locationSensors.add(new LocationSensor("Olesnica", "THP"));
        locationSensors.add(new LocationSensor("Wroclaw", "THP"));

        csi = new CSI(locationSensors);
    }

    @Test
    public void registerUserTest() {
        User user = new User("Bob");
        Main.LocationSensor locationSensor = new LocationSensor("Wroclaw", "THP");
        csi.registerUser(locationSensor, user);

        Assert.assertEquals(csi.getLocationSensors().get(2).getObserver(), user);
        Assert.assertTrue(user.getActiveUserLocations().contains("Wroclaw"));
        Assert.assertTrue(user.getUserLocationSensorsData().contains(locationSensor));
        //Round 2
        csi.registerUser(locationSensor, user);
        Assert.assertTrue(user.getActiveUserLocations().contains("Wroclaw"));
        Assert.assertTrue(user.getUserLocationSensorsData().contains(locationSensor));

    }

    @Test
    public void unregisterUserTest() {
        User user = new User("Bob");

        csi.unRegisterUser("Olawa", user);

        Assert.assertNull(csi.getLocationSensors().get(0).getObserver());
        Assert.assertFalse(user.getActiveUserLocations().contains("Olawa"));
    }

}