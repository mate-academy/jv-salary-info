package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalaryInfo {
    public static final int INDEX_OF_DATE = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_OF_DAYS = 2;
    public static final int INDEX_OF_PRICE = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] finalSalary = new int[names.length];
        for (String line : data) {
            String[] personInformation = line.split(" ");
            LocalDate date = LocalDate.parse(personInformation[INDEX_OF_DATE], FORMATTER);
            String nameOfEmployee = personInformation[INDEX_OF_NAME];
            int hours = Integer.parseInt(personInformation[INDEX_OF_DAYS]);
            int salaryPerHour = Integer.parseInt(personInformation[INDEX_OF_PRICE]);
            if (List.of(names).contains(nameOfEmployee) && !localDateFrom.isAfter(date)
                    && !localDateTo.isBefore(date)) {
                finalSalary[List.of(names).indexOf(nameOfEmployee)] += hours * salaryPerHour;
            }
        }
        return dataToString(names, finalSalary, dateFrom, dateTo);
    }

    private String dataToString(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder resultString = new StringBuilder();
        resultString
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultString
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return resultString.toString();
    }
}
