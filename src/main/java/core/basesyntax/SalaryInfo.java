package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_POSITION_IN_DATA = 0;
    private static final int WORKING_HOURS_POSITION_IN_DATA = 2;
    private static final int HOUR_PAYMENT_POSITION_IN_DATA = 3;
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private String[] dataToStringArray;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int salary = 0;
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                dataToStringArray = data[j].split(" ");
                if (getLocalDate(dataToStringArray[DATE_POSITION_IN_DATA])
                        .compareTo(localDateFrom) >= 0
                        && getLocalDate(dataToStringArray[DATE_POSITION_IN_DATA])
                        .compareTo(localDateTo) <= 0
                        && data[j].contains(names[i])) {
                    salary += Integer.parseInt(dataToStringArray[WORKING_HOURS_POSITION_IN_DATA])
                            * Integer.parseInt(dataToStringArray[HOUR_PAYMENT_POSITION_IN_DATA]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            salary = 0;
        }
        return result.toString();
    }

    public LocalDate getLocalDate(String inputDate) {
        try {
            return LocalDate.parse(inputDate, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("%s is not parsable!%n", inputDate, 1);
        }
    }
}
