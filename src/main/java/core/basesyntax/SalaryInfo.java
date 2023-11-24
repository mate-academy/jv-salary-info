package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final String SPACE = " ";
    static final String DASH = " - ";
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Initialize a StringBuilder to store the report
        StringBuilder stringBuilder = new StringBuilder();
        // Parse the dateFrom and dateTo strings into LocalDate objects
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        // Initialize an array to hold the LocalDate objects for each day in the data array
        LocalDate[] dataArr = new LocalDate[data.length];
        // Convert the dates in the data array into LocalDate objects
        // and store them in the dataArr array
        for (int i = 0; i < data.length; i++) {
            String[] subData = data[i].split(SPACE); // assuming comma as delimiter
            dataArr[i] = LocalDate.parse(subData[ZERO], FORMATTER);
        }
        // Add a header to the report with the time period for which the report is being generated
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());

        String[] tempSplit;
        int[] result = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            // Loop through the data array
            for (int j = 0; j < dataArr.length; j++) {
                // Split the current data entry into its components
                tempSplit = data[j].split(SPACE);
                // Check if the current data entry is for the current employee
                if (tempSplit[ONE].equals(names[i])) {
                    // Get the date of the current data entry
                    LocalDate date = dataArr[j];
                    // Check if the date falls within the specified time period
                    if (date.isAfter(fromDate.minusDays(ONE))
                            && date.isBefore(toDate.plusDays(ONE))) {
                        // Add the salary earned on this day to the running total
                        result[i] += Integer.parseInt(tempSplit[TWO])
                                * Integer.parseInt(tempSplit[THREE]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                    .append(DASH)
                    .append(result[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
