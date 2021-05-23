package Test;

import Main.Analyzer;
import Main.LocationSensor;
import Main.Measurement;
import org.junit.Assert;
import org.junit.Test;

public class AnalyzerTest {

    private static LocationSensor locationSensor;

    @Test
    public void getMeasurementTest() {
        locationSensor = new LocationSensor("Wroclaw", "THP");
        locationSensor.getValues().add(new Measurement(1f, 2f, 3f));
        locationSensor.getValues().add(new Measurement(3f, 1f, 2f));
        locationSensor.getValues().add(new Measurement(2f, 3f, 1f));

        Analyzer analyzer = new Analyzer();

        Assert.assertEquals(analyzer.getAverage(locationSensor), new Measurement(2f, 2f, 2f));
        Assert.assertEquals(analyzer.getMaximum(locationSensor), new Measurement(3f, 3f, 3f));
        Assert.assertEquals(analyzer.getMinimum(locationSensor), new Measurement(1f, 1f, 1f));
    }

    @Test
    public void getMeasurementTest2() {
        locationSensor = new LocationSensor("Wroclaw", "H");
        locationSensor.getValues().add(new Measurement(null, 2f, null));
        locationSensor.getValues().add(new Measurement(null, 1f, null));
        locationSensor.getValues().add(new Measurement(null, 3f, null));

        Analyzer analyzer = new Analyzer();

        Assert.assertEquals(analyzer.getAverage(locationSensor), new Measurement(null, 2f, null));
        Assert.assertEquals(analyzer.getMaximum(locationSensor), new Measurement(null, 3f, null));
        Assert.assertEquals(analyzer.getMinimum(locationSensor), new Measurement(null, 1f, null));
    }

    @Test
    public void getMeasurementTest3() {
        locationSensor = new LocationSensor("Wroclaw", "");
        locationSensor.getValues().add(new Measurement(null, null, null));
        locationSensor.getValues().add(new Measurement(null, null, null));
        locationSensor.getValues().add(new Measurement(null, null, null));

        Analyzer analyzer = new Analyzer();

        Assert.assertEquals(analyzer.getAverage(locationSensor), new Measurement(null, null, null));
        Assert.assertEquals(analyzer.getMaximum(locationSensor), new Measurement(null, null, null));
        Assert.assertEquals(analyzer.getMinimum(locationSensor), new Measurement(null, null, null));
    }
}