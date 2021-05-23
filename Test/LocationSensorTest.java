package Test;

import Main.LocationSensor;
import Main.Measurement;
import Main.User;
import Sensors.SensorHumidity;
import Sensors.SensorPressure;
import Sensors.SensorTemperature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationSensorTest {

    private static LocationSensor locationSensor;


    @Before
    public void create() {
        locationSensor = new LocationSensor("Wroclaw", "");
    }


    @Test
    public void addMeasurementTest1() {

        locationSensor.addMeasurement();

        Assert.assertNull(locationSensor.getValues().getLast().getTemperature());
        Assert.assertNull(locationSensor.getValues().getLast().getHumidity());
        Assert.assertNull(locationSensor.getValues().getLast().getPressure());
    }

    @Test
    public void addMeasurementTest2() {

        SensorTemperature mock1 = mock(SensorTemperature.class);
        SensorHumidity mock2 = mock(SensorHumidity.class);
        SensorPressure mock3 = mock(SensorPressure.class);

        locationSensor.setSensorTemperature(mock1);
        locationSensor.setSensorHumidity(mock2);
        locationSensor.setSensorPressure(mock3);

        when(mock1.getTemperature()).thenReturn(1f);
        when(mock2.getHumidity()).thenReturn(2f);
        when(mock3.getPressure()).thenReturn(3f);

        locationSensor.addMeasurement();
        Measurement measurement = new Measurement(1f, 2f, 3f);
        Assert.assertEquals(locationSensor.getValues().getLast(), measurement);
    }


    @Test
    public void registerTest() {
        User user = new User("bob");
        locationSensor.register(user);
        Assert.assertEquals(locationSensor.getObserver(), user);
    }

    @Test
    public void unregisterTest() {
        User user = new User("bob");
        locationSensor.unregister(user);
        Assert.assertNull(locationSensor.getObserver());
    }


}