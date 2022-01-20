package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;
        StringBuilder fullSalaryInfo = new StringBuilder();
        fullSalaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            salary = 0;
            fullSalaryInfo.append(name).append(" - ");
            salary = getSalaryOfCurrentPerson(data, dateFrom, dateTo, salary, name);
            fullSalaryInfo.append(salary).append(System.lineSeparator());
        }
        return fullSalaryInfo.toString().trim();
    }

    private int getSalaryOfCurrentPerson(String[] data, String dateFrom,
                                         String dateTo, int salary, String name) {
        String[] splitData;
        for (String info : data) {
            splitData = info.split(" ");
            if (isDateBetweenDateFromAndDateTo(splitData[0], dateFrom, dateTo)
                    && name.equals(splitData[1])) {
                salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
            }
        }
        return salary;
    }

    private boolean isDateBetweenDateFromAndDateTo(String stringDate, String stringDateFrom,
                                                   String stringDateTo) {
        LocalDate date = LocalDate.parse(stringDate, DATE_FORMATTER);
        LocalDate dateFrom = LocalDate.parse(stringDateFrom, DATE_FORMATTER);
        LocalDate dateTo = LocalDate.parse(stringDateTo, DATE_FORMATTER);
        return date.plusDays(1).isAfter(dateFrom) && date.minusDays(1).isBefore(dateTo);
    }
}
