package Sensors;

import java.util.Random;

public class SensorTemperature {
    Random random = new Random();

    public Float getTemperature() {
        return random.nextFloat() * 15;
    }
}
