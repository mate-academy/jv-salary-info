package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Convert single date strings to LocalDate objects
        LocalDate fromDate = convertToDate(dateFrom);
        LocalDate toDate = convertToDate(dateTo);
        // Array to store total earnings for each employee
        int[] earnings = new int[names.length];
        // Calculate earnings for each employee
        for (String rowData : data) {
            String[] rowDataArray = rowData.split(" ");
            String dateString = rowDataArray[0];
            String employeeName = rowDataArray[1];
            int hours = Integer.parseInt(rowDataArray[2]);
            int incomePerHours = Integer.parseInt(rowDataArray[3]);
            LocalDate currentDate = convertToDate(dateString);
            // Check if the date is within the specified range
            if ((currentDate.isEqual(fromDate) || currentDate.isAfter(fromDate))
                    && (currentDate.isEqual(toDate) || currentDate.isBefore(toDate))) {
                // Find the index of the employee in the names array
                int index = -1;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    // Update total earnings for the current employee
                    earnings[index] += hours * incomePerHours;
                }
            }
        }
        // Print the report
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(earnings[i]);
        }
        return report.toString();
    }

    private static LocalDate convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
