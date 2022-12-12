package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DIFFERENCE_DAY = 1;
    private static  final int CURRENT_NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static  final int HOURLY_INCOME = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder printResult = new StringBuilder("Report for period ");
        printResult.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int[] resultSalary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String date : data) {
                String[] inputData = date.split(" ");
                if (names[i].equals(inputData[CURRENT_NAME])
                        && isRightTime(date, dateFrom, dateTo)) {
                    resultSalary[i] += Integer.parseInt(inputData[WORKING_HOURS])
                            * Integer.parseInt(inputData[HOURLY_INCOME]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            printResult.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(resultSalary[i]);
        }
        return printResult.toString();
    }

    private LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private boolean isRightTime(String data, String dateFrom, String dateTo) {
        String[] inputData = data.split(" ");
        return toLocalDate(inputData[0]).isAfter(toLocalDate(dateFrom).minusDays(DIFFERENCE_DAY))
                && toLocalDate(inputData[0]).isBefore(toLocalDate(dateTo).plusDays(DIFFERENCE_DAY));
    }
}

