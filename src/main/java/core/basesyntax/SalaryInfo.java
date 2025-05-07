package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);
        for (String name : names) {
            int income = 0;
            for (String record : data) {
                income += incomeFor(name, record, startDate, endDate);
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return result.toString();
    }

    private int incomeFor(String name, String record, LocalDate dareFrom, LocalDate dateTo) {
        String[] recordParts = record.split("\\s+");
        String recordName = recordParts[NAME_INDEX];
        if (!recordName.equals(name)) {
            return 0;
        }
        LocalDate date = parseDate(recordParts[DATE_INDEX]);
        if (date.isBefore(dareFrom) || date.isAfter(dateTo)) {
            return 0;
        }
        int incomePerHour = Integer.parseInt(recordParts[INCOME_INDEX]);
        int hours = Integer.parseInt(recordParts[HOURS_INDEX]);
        return incomePerHour * hours;
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }
}
