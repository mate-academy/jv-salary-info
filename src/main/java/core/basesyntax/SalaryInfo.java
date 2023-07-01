package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String PATTERN = "dd.MM.yyyy";
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int PER_HOUR_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        StringBuilder result = new StringBuilder();
        for (String name : names) {
            int sumOfEarnedMoney = 0;
            for (String strData : data) {
                String[] split = strData.split(" ");
                LocalDate date = parseDate(split[DATE]);
                if (date.isAfter(from) && date.minusDays(1).isBefore(to)) {
                    if (name.equals(split[NAME])) {
                        sumOfEarnedMoney += Integer.parseInt(split[WORKING_HOURS])
                                          * Integer.parseInt(split[PER_HOUR_SALARY]);
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
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(PATTERN);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Date is is not parsable!",
                    date, e.getErrorIndex(), e);
        }
    }
}
