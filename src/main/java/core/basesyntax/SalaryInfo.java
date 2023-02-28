package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;

        StringBuilder builder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            for (String info : data) {
                String[] employeeInfo = info.split(" ");

                if (name.equals(employeeInfo[NAME_INDEX])
                        && (strToDate(employeeInfo[DATE_INDEX]).isAfter(strToDate(dateFrom))
                        || strToDate(employeeInfo[DATE_INDEX]).isEqual(strToDate(dateFrom)))
                        && (strToDate(employeeInfo[DATE_INDEX]).isBefore(strToDate(dateTo))
                        || strToDate(employeeInfo[DATE_INDEX]).isEqual(strToDate(dateTo)))) {

                    salary += Integer.parseInt(employeeInfo[WORKING_HOUR_INDEX])
                            * Integer.parseInt(employeeInfo[INCOME_PER_HOUR_INDEX]);
                }
            }

            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }

        return builder.toString();
    }

    private LocalDate strToDate(String inputDate) {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(inputDate, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format");
        }

        return localDate;
    }
}
