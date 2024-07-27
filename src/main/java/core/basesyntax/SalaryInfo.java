package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy"); //Definiowanie formatu DAT dla programu
    private static final int defaultDataToAray = 0;
    private static final int datePoss = 0; //Ustawienie pozycji Daty w Tablicy
    private static final int namePoss = 1; //Ustawienie pozycji Imienia Pracownika w Tablicy
    private static final int hoursPoss = 2; //Ustawienie pozycji Liczby przepracowanych godzin danego dnia
    private static final int salaryPoss = 3; //Ustawienie pozycji stawki godzinowej

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String header = "Report for period " + dateFrom + " - " + dateTo; //Definiowanie hedera dla tablicy
        StringBuilder stringBuilder = new StringBuilder(header); //Budowanie StringBuildera

        LocalDate localFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER); //Pobieranie dateFrom i jego konwersja na LocalDate
        LocalDate localTo = LocalDate.parse(dateTo,DATE_TIME_FORMATTER); //Pobieranie dateTo i jego konwersja na LocalDate

        int[] salaries = new int[names.length]; //Tworzenie tablicy wypłat o długości liczby imion pracowników
        Arrays.fill(salaries,defaultDataToAray); //Ustawienie pozycji nr 0 jako pustej pod header

        for (int i = 0; i < names.length; i++) {
            for (String split : data) { //Wstawienie splita jako " " (SPACJA)
                String[] splitData = split.split(" ");
                LocalDate localDateFromData = LocalDate
                        .parse(splitData[datePoss], DATE_TIME_FORMATTER); //Ustawienie wyżej zdefiniowanego rodzaju czytania dat dla tej petli

                if (names[i].equals(splitData[namePoss])
                        && !localDateFromData.isBefore(localFrom)
                        && !localDateFromData.isAfter(localTo)) {
                    salaries[i] += Integer.parseInt(splitData[hoursPoss]) * Integer.parseInt(splitData[salaryPoss]); //Warunek sprawdzający czy daty z tablicy mieszczą się w widełkach daty od do daty do
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salaries[i]); //Wstawienie separatora dla końcowego wyniku w postaci myślnika
        }

        return stringBuilder.toString(); //Zwrócenie powstałego wyniku w postaci tekstu "String"
    }
}
