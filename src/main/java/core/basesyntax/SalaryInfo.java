package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SalaryInfo {
    static  final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final String[] EMPTY_ARRAY = {};
    static final String EMPTY_STRING = null;
    static final String HEADER = "Report for period ";
    static final String DELIMITER = " - ";
    static final String SEPARATOR = " ";
    static final int DATE = 0;
    static final int NAME = 1;
    static final int HOUR = 2;
    static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(HEADER + dateFrom + DELIMITER + dateTo);

        if (names != EMPTY_ARRAY && data != EMPTY_ARRAY && dateFrom != EMPTY_STRING && dateTo != EMPTY_STRING) {
                LocalDate startDate = parseDate(dateFrom);
                LocalDate endDate = parseDate(dateTo);

                for (String name : names) {
                    int employeeSalary = calculateEmployeeSalary(data, name, startDate, endDate);
                    result.append(System.lineSeparator()).append(name)
                            .append(DELIMITER).append(employeeSalary);
                }
        }

        return result.toString();
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, FORMATER);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing date.", e);
        }
    }

    private int calculateEmployeeSalary(String[] data, String name,
                                        LocalDate startDate, LocalDate endDate) {
        int employeeSalary = 0;

        for (String day : data) {
            String[] dataDay = day.split(SEPARATOR);
            try {
                LocalDate itemDate = parseDate(dataDay[DATE]);

                if (name.equals(dataDay[NAME]) && (itemDate.isEqual(startDate)
                        || (itemDate.isAfter(startDate) && itemDate.isBefore(endDate))
                        || itemDate.isEqual(endDate))) {
                    employeeSalary += Integer.parseInt(dataDay[HOUR]) * Integer.parseInt(dataDay[SALARY]);
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Error parsing number: ");
            }
        }

        return employeeSalary;
    }
}
