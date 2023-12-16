package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int income = calculatePersonsIncome(name, data, dateFromFormatted, dateToFormatted);
            result.append(name).append(" - ").append(income).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    private boolean isInDateLimit(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (date.isAfter(dateFrom) || date.isEqual(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo));
    }

    private int calculatePersonsIncome(String person, String[] data,
                                       LocalDate dateFrom, LocalDate dateTo) {
        int income = 0;
        for (String dataSample : data) {
            String[] dataArray = dataSample.split(" ");
            LocalDate date = LocalDate.parse(dataArray[DATE_INDEX], formatter);
            String name = dataArray[NAME_INDEX];
            int workHour = Integer.parseInt(dataArray[WORK_HOUR_INDEX]);
            int incomePerHour = Integer.parseInt(dataArray[INCOME_PER_HOUR_INDEX]);
            if (name.equals(person)
                    && isInDateLimit(date, dateFrom, dateTo)) {
                income += workHour * incomePerHour;
            }
        }
        return income;
    }
}
