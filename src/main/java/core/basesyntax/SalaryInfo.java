package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_WORKING_HOURS = 2;
    private static final int INDEX_AMOUNT = 3;
    private static final String SEPARATOR = " ";
    private static final String DELIMITER = " - ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String MESSAGE_FOR_PERIOD = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, format);
        LocalDate localDateTo = LocalDate.parse(dateTo, format);
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
            for (int j = 0; j < data.length; j++) {
                String[] information = data[j].split(SEPARATOR);
                LocalDate compareDate = LocalDate.parse(information[INDEX_DATE],format);
                if (compareDates(compareDate, localDateFrom, localDateTo)) {
                    if (information[INDEX_NAME].equals(names[i])) {
                        employees[i].setName(names[i]);
                        employees[i].setSalary(Integer.parseInt(information[INDEX_WORKING_HOURS])
                                * Integer.parseInt(information[INDEX_AMOUNT]));
                    }
                }
            }
        }
        return createReport(employees, dateFrom, dateTo);
    }

    private boolean compareDates(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        if (date == null || dateFrom == null || dateTo == null) {
            return false;
        }
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }

    private String createReport(Employee[] employees, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append(MESSAGE_FOR_PERIOD).append(dateFrom).append(DELIMITER).append(dateTo);
        for (int i = 0; i < employees.length; i++) {
            builder.append(System.lineSeparator())
                    .append(employees[i].getName())
                    .append(DELIMITER)
                    .append(employees[i].getSalary());
        }
        return builder.toString();
    }
}
