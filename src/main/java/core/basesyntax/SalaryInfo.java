package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder printResult = new StringBuilder("Report for period ");
        printResult.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int[] resultSalary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                String[] inputData = datum.split(" ");
                if (names[i].equals(inputData[1])
                        && isRightTime(datum, dateFrom, dateTo)) {
                    resultSalary[i] += Integer.parseInt(inputData[2])
                            * Integer.parseInt(inputData[3]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            printResult.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(resultSalary[i]);
        }
        return printResult.toString();
    }

    private LocalDate stringData(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    private boolean isRightTime(String data, String dateFrom, String dateTo) {
        String[] inputData = data.split(" ");
        return stringData(inputData[0]).isAfter(stringData(dateFrom).minusDays(1))
                && stringData(inputData[0]).isBefore(stringData(dateTo).plusDays(1));
    }
}

