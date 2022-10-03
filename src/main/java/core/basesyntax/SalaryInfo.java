package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // create formatter

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = LocalDate.parse(dateFrom.replace(".", "-"), formatter);
        LocalDate dateT = LocalDate.parse(dateTo.replace(".", "-"), formatter);
        // parse string "dateFrom" and "datwTo" to LocalDate format

        LocalDate[] dateArray = new LocalDate[data.length];
        String[] nameArray = new String[data.length];
        int[] hoursArray = new int[data.length];
        int [] paymentArray = new int[data.length];
        String[] totalArray;
        //  Сreate arrays for the future division of the "date array" into components

        for (int i = 0; i < data.length; i++) {
            totalArray = data[i].split(" ");
            for (int j = 0; j < 4; j++) {
                dateArray[i] = LocalDate.parse(totalArray[0].replace(".", "-"), formatter);
                nameArray[i] = totalArray[1];
                hoursArray[i] = Integer.parseInt(totalArray[2]);
                paymentArray[i] = Integer.parseInt(totalArray[3]);
            }
        }
        // Divide "date array" into components

        int [] totalSalary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (names[i].equals(nameArray[j]) && (dateArray[j].isAfter(dateF)
                        && dateArray[j].isBefore(dateT.plusDays(1)))) {
                    totalSalary[i] += hoursArray[j] * paymentArray[j];
                }
            }
        }
        // Check name & data and calculate total salary for period

        String result = "";
        for (int i = 0; i < names.length; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(names[i]).append(" - ").append(totalSalary[i]);
            result += builder.toString() + System.lineSeparator();
        }
// Convert data to general string

        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator()
                + result.trim();
    }
}




