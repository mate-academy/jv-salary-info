package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final int POSITION_FOR_DATE = 0;
    private static final int POSITION_FOR_NAME = 1;
    private static final int POSITION_FOR_HOURS = 2;
    private static final int POSITION_FOR_MONEY = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String period : data) {
                String[] dates = period.split(DELIMITER);
                String dateString = dates[POSITION_FOR_DATE];
                String localName = dates[POSITION_FOR_NAME];
                int hours = Integer.parseInt(dates[POSITION_FOR_HOURS]);
                int incomePerHour = Integer.parseInt(dates[POSITION_FOR_MONEY]);
                LocalDate date = LocalDate.parse(dateString, FORMATTER);
                if ((localName.equals(name)) && checkDate(date, localDateTo, localDateFrom)) {
                    salary += hours * incomePerHour;
                }
            }
            resultMessage.append(System.lineSeparator());
            resultMessage.append(name)
                    .append(" - ")
                    .append(salary);
        }
        return resultMessage.toString();
    }

    private boolean checkDate(LocalDate date, LocalDate localDateTo, LocalDate localDateFrom) {
        return (date.isAfter(localDateFrom)
                || date.isEqual(localDateFrom))
                && (date.isBefore(localDateTo)
                || date.isEqual(localDateTo));
    }
}
