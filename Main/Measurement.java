package Main;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Measurement {

    @Expose()
    private final Date date;
    @Expose()
    private final Float temperature;
    @Expose()
    private final Float humidity;
    @Expose()
    private final Float pressure;

    public Measurement(Float temperature, Float humidity, Float pressure) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        date = gregorianCalendar.getTime();
    }


    public Float getTemperature() {
        return temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getPressure() {
        return pressure;
    }


    @Override
    public String toString() {
        String temp = temperature + "C", hum = humidity + "%", pre = pressure + "hPa";

        if (temperature == null)
            temp = "Unavailable";
        if (humidity == null)
            hum = "Unavailable";
        if (pressure == null)
            pre = "Unavailable";

        return "time: " + date +
                ", temperature:" + temp +
                ", humidity:" + hum +
                ", pressure:" + pre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(temperature, that.temperature) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(pressure, that.pressure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, humidity, pressure);
    }
}
