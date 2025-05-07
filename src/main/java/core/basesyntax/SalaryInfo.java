package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DATA_SEPARATOR = " - ";
    public static final String STRING_SPLIT_VALUE = " ";
    public static final String START_STRING = "Report for period ";
    public static final String ERROR_MSG = "%s is not parsable!";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(START_STRING).append(dateFrom)
                .append(DATA_SEPARATOR).append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            builder.append(name).append(DATA_SEPARATOR);
            for (String dataUnit : data) {
                String[] dataUnitToArray = dataUnit.split(STRING_SPLIT_VALUE);
                if (dataUnit.contains(name)
                        && isValidDate(dataUnitToArray[0], dateFrom, dateTo)) {
                    salary += Integer.parseInt(dataUnitToArray[2])
                            * Integer.parseInt(dataUnitToArray[3]);
                }
            }
            builder.append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private boolean isValidDate(String date, String dateFrom, String dateTo) {
        try {
            LocalDate currentDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMATTER);
            return (!currentDate.isBefore(dateStart))
                    && (!currentDate.isAfter(dateEnd));
        } catch (DateTimeParseException exc) {
            System.out.printf(ERROR_MSG + System.lineSeparator(), date);
            System.out.printf(ERROR_MSG + System.lineSeparator(), dateFrom);
            System.out.printf(ERROR_MSG + System.lineSeparator(), dateTo);
        }
        return false;
    }
}




