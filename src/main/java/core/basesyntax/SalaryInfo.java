package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate newDateFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate newDateTo = LocalDate.parse(dateTo, dateFormatter);

        int[] salaryArray = new int[names.length];
        processSalaryData(data, names, salaryArray, newDateFrom, newDateTo);
        buildSalaryReport(result, names, salaryArray);
        return result.toString();
    }

    private void processSalaryData(String[] data, String[] names, int[] salaryArray,
                                   LocalDate newDateFrom, LocalDate newDateTo) {
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[DATE_INDEX], dateFormatter);

            if (!date.isBefore(newDateFrom) && !date.isAfter(newDateTo)) {
                String name = parts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);

                int index = findIndex(names, name);
                if (index != -1) {
                    salaryArray[index] += hoursWorked * hourlyRate;
                }
            }
        }
    }

    private void buildSalaryReport(StringBuilder result, String[] names, int[] salaryArray) {
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaryArray[i]);
        }
    }

    private int findIndex(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
