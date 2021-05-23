package Main;

public class Analyzer {

    public Measurement getAverage(LocationSensor locationSensor) {

        Float averageT = 0f;
        Float averageH = 0f;
        Float averageP = 0f;
        for (Measurement measurement : locationSensor.values) {
            if ((locationSensor.getCodeSensor().contains("T")))
                averageT += measurement.getTemperature();
            if ((locationSensor.getCodeSensor().contains("H")))
                averageH += measurement.getHumidity();
            if ((locationSensor.getCodeSensor().contains("P")))
                averageP += measurement.getPressure();
        }

        averageT = averageT / locationSensor.values.size();
        averageH = averageH / locationSensor.values.size();
        averageP = averageP / locationSensor.values.size();

        if (!(locationSensor.getCodeSensor().contains("T")))
            averageT = null;
        if (!(locationSensor.getCodeSensor().contains("H")))
            averageH = null;
        if (!(locationSensor.getCodeSensor().contains("P")))
            averageP = null;


        return new Measurement(averageT, averageH, averageP);
    }

    public Measurement getMinimum(LocationSensor locationSensor) {

        Float minT = locationSensor.values.getFirst().getTemperature();
        Float minH = locationSensor.values.getFirst().getHumidity();
        Float minP = locationSensor.values.getFirst().getPressure();

        for (Measurement measurement : locationSensor.values) {
            if ((locationSensor.getCodeSensor().contains("T")))
                if (minT > measurement.getTemperature())
                    minT = measurement.getTemperature();
            if ((locationSensor.getCodeSensor().contains("H")))
                if (minH > measurement.getHumidity())
                    minH = measurement.getHumidity();
            if ((locationSensor.getCodeSensor().contains("P")))
                if (minP > measurement.getPressure())
                    minP = measurement.getPressure();
        }


        return new Measurement(minT, minH, minP);
    }

    public Measurement getMaximum(LocationSensor locationSensor) {

        Float maxT = locationSensor.values.getFirst().getTemperature();
        Float maxH = locationSensor.values.getFirst().getHumidity();
        Float maxP = locationSensor.values.getFirst().getPressure();

        for (Measurement measurement : locationSensor.values) {
            if ((locationSensor.getCodeSensor().contains("T")))
                if (maxT < measurement.getTemperature())
                    maxT = measurement.getTemperature();
            if ((locationSensor.getCodeSensor().contains("H")))
                if (maxH < measurement.getHumidity())
                    maxH = measurement.getHumidity();
            if ((locationSensor.getCodeSensor().contains("P")))
                if (maxP < measurement.getPressure())
                    maxP = measurement.getPressure();
        }


        return new Measurement(maxT, maxH, maxP);

    }


}
