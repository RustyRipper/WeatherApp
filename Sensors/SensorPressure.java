package Sensors;

import java.util.Random;

public class SensorPressure {
    Random random = new Random();

    public Float getPressure() {
        return random.nextFloat() * 200 + 900;
    }
}
