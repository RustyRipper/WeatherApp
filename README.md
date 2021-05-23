# WeatherApp
Project at the end of the first semester

## Instruction for the task

/* Projekt meteorologiczny */
/*

1. Na Dolnym Śląsku, w różnych lokalizacach zostały rozmieszczone czujniki: temperatury, wilgotności i ciśnienia, które
są sterowane z Centralnego Systemu Informatycznego. CSI pozwala na synchronizacę czujników i przeprowadzenie jednoczesnego pomiaru
przez wszystkie z nich. Pomiar wielkości fizycznych odbywa się regularnie, co 5 sekund i jest automatycznie zapisywany
w pamięci czujnika. Czujniki mogą być zintegrowane i mierzyć jednocześnie więcej niż jedną wielkość fizyczną.
2. CSI nie tylko synchronizuje czujniki, ale pozwala na wyświetlenie wszystkich dostępnych lokalizacji, w których zostały
rozmieszczone czujniki. Lista lokalizacji wraz z dostępnością czujnika może zostać przekazana w postaci obiektu lub w postaci JSON.
np. Jelenia Góra TP (temperature/pressure) albo Jawor HP (humidity/pressure) itp.
3. Mieszkańcy Dolnego Śląska mogą korzystać z aplikacji Katalog Uniwersalnych Pomiarów Asynchronicznych (w skr. KUPA),
dostępny w domenie gov.gov/other-trash-without-any-specific-usability. Aplikacja pozwala na rejestrację użytkownika
do określonej lokalizacji i notyfikacje o zmianach z subskrybowanego czujnika.
4. Protokół komunikacyjny KUPA-CSI jest uniwersalny, tj. bez względu na ilość monitorowanych wielkości fizycznych, wiadomość wysyłana
do odbiorcy jest zawsze obiektem, który zawiera wszystkie pola, tj. temperaturę, wilgotność i ciśnienie, co najwyżej
przy niektórych polach wyświetla informację "niedostępne".
5. W przypadku rejestracji jednego użytkownika do wielu czujników, aplikacja pozwala na wygodne gromadzenie wszystkich danych.
6. Aplikacja pozwala na zapisanie wszystkich zgromadzonych danych w pliku .json
7. Aplikaca oferue możliwość wykonania prostej analizy danych względem lokalizacji. Aktualnie oferowane są tylko opcje obliczania
wartości średnich, minimum i maksimum.
8. Użytkownik aplikacji może sprawdzić listę subskrybowanych lokalizacji, dodać nowe subskrypcje lub anulować je w dowolnym momencie.

Wymagania techniczne:
- Pomiar danych odbywa się w wątku
- Aplikacja posiada testy jednostkowe
