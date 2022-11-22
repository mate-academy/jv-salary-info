package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateToLocal = LocalDate.parse(dateTo, timeFormater);
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, timeFormater);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int salaryCount = 0;
            for (String el : data) {
                LocalDate date = LocalDate.parse(el.split(" ")[INDEX_OF_DATE], timeFormater);
                String[] inputData = el.split(" ");
                String nameParsed = el.split(" ")[INDEX_OF_NAME];
                if ((nameParsed.contains(name) && (date.isBefore(dateToLocal))
                        && date.isAfter(dateFromLocal))
                        || nameParsed.contains(name) && (date.isEqual(dateFromLocal)
                        || date.isEqual(dateToLocal))) {
                    salaryCount += Integer.parseInt(inputData[INDEX_OF_HOURS])
                            * Integer.parseInt(inputData[INDEX_OF_SALARY]);
                }
            }
            builder.append(salaryCount);
        }
        return builder.toString();
    }
}
