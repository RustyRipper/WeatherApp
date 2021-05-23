package Main;

import Sensors.SensorHumidity;
import Sensors.SensorPressure;
import Sensors.SensorTemperature;
import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.Objects;

public class LocationSensor implements Observable, Comparable<LocationSensor> {

    @Expose()
    private final String location;
    @Expose()
    private final String codeSensor;

    @Expose()
    protected LinkedList<Measurement> values;

    private SensorTemperature sensorTemperature;
    private SensorHumidity sensorHumidity;
    private SensorPressure sensorPressure;

    private Observer observer;


    public LocationSensor(String location, String codeSensor) {
        this.location = location;
        this.codeSensor = codeSensor;
        values = new LinkedList<>();

        if (codeSensor.contains("T")) sensorTemperature = new SensorTemperature();
        if (codeSensor.contains("H")) sensorHumidity = new SensorHumidity();
        if (codeSensor.contains("P")) sensorPressure = new SensorPressure();
    }


    public void addMeasurement() {
        Float f1 = null;
        if (sensorTemperature != null)
            f1 = sensorTemperature.getTemperature();
        Float f2 = null;
        if (sensorHumidity != null)
            f2 = sensorHumidity.getHumidity();
        Float f3 = null;
        if (sensorPressure != null)
            f3 = sensorPressure.getPressure();

        Measurement measurement = new Measurement(f1, f2, f3);

        values.add(measurement);
        if (observer != null) {
            notifyObserver(measurement);
        }
    }

    public void viewMeasurement() {
        for (Measurement measurement : values) {
            System.out.println(measurement.toString());
        }
    }


    @Override
    public void register(Observer o) {
        observer = o;
    }

    @Override
    public void unregister(Observer o) {
        observer = null;
    }

    @Override
    public void notifyObserver(Measurement measurement) {
        observer.update(location, measurement);
    }


    public String getLocation() {
        return location;
    }

    public String getCodeSensor() {
        return codeSensor;
    }

    public LinkedList<Measurement> getValues() {
        return values;
    }

    public Observer getObserver() {
        return observer;
    }


    public void setSensorTemperature(SensorTemperature sensorTemperature) {
        this.sensorTemperature = sensorTemperature;
    }

    public void setSensorHumidity(SensorHumidity sensorHumidity) {
        this.sensorHumidity = sensorHumidity;
    }

    public void setSensorPressure(SensorPressure sensorPressure) {
        this.sensorPressure = sensorPressure;
    }


    @Override
    public int compareTo(LocationSensor o) {
        return this.location.compareTo(o.location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationSensor)) return false;
        LocationSensor that = (LocationSensor) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(codeSensor, that.codeSensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, codeSensor);
    }
}
