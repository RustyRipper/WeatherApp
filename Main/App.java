package Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {

    private CSI csi;
    private User user;
    private Thread thread;
    private AtomicBoolean running;

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        running = new AtomicBoolean(false);
        csi = new CSI(loadSensors("labs12\\src\\Sensors.txt"));
        startThread();

        System.out.println("--------------------");
        System.out.println(" Welcome to K.U.P.A ");
        System.out.println("--------------------\n");
        System.out.println("Enter your nickname");

        user = new User(scanString());

        menu();
    }

    //My factory ------------------------------------
    public void startThread() {
        running.set(true);

        thread = new Thread(() -> {

            while (running.get()) {
                for (LocationSensor sensor : csi.locationSensors) {

                    sensor.addMeasurement();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void stopThread() {
        System.out.println("Bye "+user.name);
        running.set(false);
        thread.interrupt();

        System.out.println("Thread has been killed");
        System.exit(0);
    }

    //-----MENU--------------------------------------------
    public void menu() {
        for (int i = 0; i < 6; i++)
            System.out.println();

        System.out.println("------");
        System.out.println(" MENU ");
        System.out.println("------\n");

        System.out.println("1. Subscribe Location");
        System.out.println("2. UnSubscribe Location");
        System.out.println("3. View your Data");
        System.out.println("4. Analyze your data");
        System.out.println("5. Save your data to .json");
        System.out.println("0. Exit");

        int choice = scanINT();

        switch (choice) {
            case 1 -> subscribe();
            case 2 -> unSubscribe();
            case 3 -> viewUserLocations();
            case 4 -> analyzeData();
            case 5 -> saveToJson();
            default -> stopThread();
        }
    }

    public void subscribe() {
        System.out.println("Select");
        csi.viewLocations();
        System.out.println("0.Back");

        while (true) {
            int choice = scanINT();
            if (choice < 1 || choice > csi.locationSensors.size()) {
                menu();
            }
            csi.registerUser(csi.locationSensors.get(choice - 1), user);

            System.out.println("Sub " + csi.locationSensors.get(choice - 1).getLocation());
        }
    }

    public void unSubscribe() {
        System.out.println("Select");
        System.out.println("-1.UnSub All");
        user.viewActiveUserLocation();
        System.out.println("0.Back");

        while (true) {
            int choice = scanINT();
            if (choice == -1) {
                while (!(user.activeUserLocations.isEmpty())) {
                    csi.unRegisterUser(user.activeUserLocations.get(user.activeUserLocations.size() - 1), user);
                }
                System.out.println("UnSub all");
                menu();
            } else if (choice < 1 || choice > user.activeUserLocations.size()) {
                menu();
            }
            System.out.println("UnSub " + user.activeUserLocations.get(choice - 1));

            csi.unRegisterUser(user.activeUserLocations.get(choice - 1), user);

            unSubscribe();
        }
    }

    public void viewUserLocations() {
        System.out.println("Select to read Data");
        user.viewUserLocation();
        System.out.println("0.Back");

        int choice = scanINT();

        while (true) {
            if (choice < 1 || choice > user.userLocationSensorsData.size()) {
                menu();
            }
            user.viewMeasurement(user.userLocationSensorsData.get(choice - 1));
            viewUserLocations();
        }
    }

    public void analyzeData() {
        System.out.println("Select location to analyze");
        user.viewUserLocation();
        System.out.println("0.Back");

        int choice = scanINT();

        while (true) {
            if (choice < 1 || choice > user.userLocationSensorsData.size()) {
                menu();
            }
            analyzeFunctions(choice);
        }
    }

    public void saveToJson() {
        System.out.println("1.Save your LocationSensorData");
        System.out.println("2.Save list of Sensors");
        System.out.println("3.Save all Measurements from CSI");
        System.out.println("0.Back");

        int choice = scanINT();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().serializeNulls().create();
        String json = null;
        if (choice == 1) {
            json = gson.toJson(user.getUserLocationSensorsData());
        } else if (choice == 2) {
            json = csi.getJsonListOfLocations();
        } else if (choice == 3) {
            json = gson.toJson(csi.locationSensors);
        } else menu();

        saveToJsonData(json);
    }

    //----------------------------------------------------------------------------
    public ArrayList<LocationSensor> loadSensors(String filePath) {

        ArrayList<LocationSensor> locationSensors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] table;
            String line;
            while ((line = br.readLine()) != null) {

                table = line.split(" ");
                if (table.length == 2) locationSensors.add(new LocationSensor(table[0], table[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationSensors;
    }

    public void analyzeFunctions(int choice) {

        System.out.println("1.Average");
        System.out.println("2.Minimum");
        System.out.println("3.Maximum");
        System.out.println("0.Back");

        int choice2 = scanINT();

        Analyzer analyzer = new Analyzer();
        Measurement measurement;


        if (choice2 == 0) analyzeData();
        if (choice2 == 1) {
            measurement = analyzer.getAverage(user.userLocationSensorsData.get(choice - 1));
            System.out.println("Average " + user.userLocationSensorsData.get(choice - 1).getLocation() + " : " + measurement.toString());
        }
        if (choice2 == 2) {
            measurement = analyzer.getMinimum(user.userLocationSensorsData.get(choice - 1));
            System.out.println("Minimum " + user.userLocationSensorsData.get(choice - 1).getLocation() + " : " + measurement.toString());
        }
        if (choice2 == 3) {
            measurement = analyzer.getMaximum(user.userLocationSensorsData.get(choice - 1));
            System.out.println("Maximum " + user.userLocationSensorsData.get(choice - 1).getLocation() + " : " + measurement.toString());
        }
        analyzeFunctions(choice);
    }

    public void saveToJsonData(String json) {
        System.out.println("Entry name file .json");
        File file = new File(scanString() + ".json");


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(json);
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        saveToJson();
    }

    public int scanINT() {
        Scanner scanner = new Scanner(System.in);

        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Enter INT");
            return scanINT();
        }
    }

    public String scanString() {
        return new Scanner(System.in).nextLine();
    }
}
