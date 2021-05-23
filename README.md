# WeatherApp
Project at the end of the first semester

## Instruction for the task

/ * Meteorological project * /
/ *

1. In Lower Silesia, sensors for temperature, humidity and pressure have been placed in various locations
are controlled from the Central IT System. CSI allows you to synchronize sensors and carry out simultaneous measurement
by all of them. Measurement of physical quantities takes place regularly, every 5 seconds and is automatically saved
in the sensor memory. Sensors can be integrated and measure more than one physical quantity simultaneously.
2. CSI not only synchronizes sensors, but allows you to view all available locations where they have been
sensors arranged. The list of locations with the availability of the sensor can be provided in the form of an object or in the form of JSON.
e.g. Jelenia Góra TP (temperature / pressure) or Jawor HP (humidity / pressure) etc.
3. The inhabitants of Lower Silesia can use the Catalog of Asynchronous Measurements Catalog (in the box KUPA),
available at gov.gov/other-trash-without-any-specific-usability. The application allows user registration
to a specific location and notifications of changes from the subscribed sensor.
4. The KUPA-CSI communication protocol is universal, i.e. regardless of the number of monitored physical quantities, the message sent
to the recipient, it is always an object that contains all the fields, i.e. temperature, humidity and pressure, at most
with some fields it displays "not available".
5. In the case of registration of one user to many sensors, the application allows for convenient collection of all data.
6. The application allows you to save all collected data in a .json file
7. The applicant offers the possibility of performing a simple data analysis in relation to the location. Currently only calculation options are offered
mean, minimum and maximum values.
8. The application user can check the list of subscribed locations, add new subscriptions or cancel them at any time.

Technical requirements:
- Data measurement is done in thread
- The application has unit tests
