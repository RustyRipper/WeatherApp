package Sensors;

import java.util.Random;

public class SensorHumidity {

    Random random = new Random();

    public Float getHumidity() {
        return random.nextFloat() * 100;
    }
}
