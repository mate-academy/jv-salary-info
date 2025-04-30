package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int PER_HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        StringBuilder result = new StringBuilder();
        for (String name : names) {
            int sumOfEarnedMoney = 0;
            for (String strData : data) {
                String[] split = strData.split(" ");
                LocalDate date = parseDate(split[DATE_INDEX]);
                if (date.isAfter(from) && date.minusDays(1).isBefore(to)) {
                    if (name.equals(split[NAME_INDEX])) {
                        sumOfEarnedMoney += Integer.parseInt(split[WORKING_HOURS_INDEX])
                                          * Integer.parseInt(split[PER_HOUR_SALARY_INDEX]);
                    }
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(sumOfEarnedMoney)
                    .append(System.lineSeparator());
            sumOfEarnedMoney = 0;
        }
        return "Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator()
                + result.toString().trim();
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Date is is not parsable!",
                    date, e.getErrorIndex(), e);
        }
    }
}
