package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator());
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            result.append(formatData(name, calculatePersonsIncome(name, data,
                    dateFromFormatted, dateToFormatted)));
        }
        return result.toString().trim();
    }

    private String formatData(String name, int income) {
        return name + " - " + income + System.lineSeparator();
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
            LocalDate date = LocalDate.parse(dataArray[0], formatter);
            String name = dataArray[1];
            int workHour = Integer.parseInt(dataArray[2]);
            int incomePerHour = Integer.parseInt(dataArray[3]);
            if (name.equals(person)
                    && isInDateLimit(date, dateFrom, dateTo)) {
                income += workHour * incomePerHour;
            }
        }
        return income;
    }
}
