package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int ABSENT_INCOME = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder resultData = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        int uniqueIndex = 0;
        int[] salaries = new int[names.length];
        try {
            for (String entry : getEmployeesSalaries(data, dateFrom, dateTo)) {
                String[] parts = entry.split(" ");
                String name = parts[ZERO_INDEX];
                String salaryString = parts[FIRST_INDEX].replaceAll("\\r\\n", "");
                int salary = Integer.parseInt(salaryString);
                int exitingIndex = findIndex(names, name);
                if (exitingIndex != -1) {
                    salaries[exitingIndex] += salary;
                } else {
                    salaries[uniqueIndex++] = salary;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < names.length; i++) {
            resultData.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }
        return resultData.toString();
    }

    private String[] getEmployeesSalaries(String[] data,
                                                     String dateFrom, String dateTo) {
        int localCount = 0;
        String[] resultData = new String[data.length];
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom,SIMPLE_DATE_FORMAT);
            LocalDate toDate = LocalDate.parse(dateTo,SIMPLE_DATE_FORMAT);
            for (String entry : data) {
                StringBuilder result = new StringBuilder();
                String[] parts = entry.split(" ");
                String workingDate = parts[ZERO_INDEX];
                String name = parts[FIRST_INDEX];
                int workingHours = Integer.parseInt(parts[SECOND_INDEX]);
                int incomePerHour = Integer.parseInt(parts[THIRD_INDEX]);
                LocalDate date = LocalDate.parse(workingDate,SIMPLE_DATE_FORMAT);
                if (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    int totalIncome = workingHours * incomePerHour;
                    result.append(name).append(" ").append(totalIncome).append(" ");
                    resultData[localCount++] = result.toString();
                } else {
                    result.append(name).append(" ").append(ABSENT_INCOME).append(" ");
                    resultData[localCount++] = result.toString();
                }
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    private int findIndex(String[] data, String target) {
        for (int i = 0; i < data.length; i++) {
            if (target.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }
}
