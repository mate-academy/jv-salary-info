package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int RATE_POSITION = 3;
    private static final String DELIMITER = " - ";
    private static final String SPACE = " ";
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = parseLocalDate(dateFrom);
        LocalDate dateEnd = parseLocalDate(dateTo);
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] employeeInfo = record.split(SPACE);
            LocalDate currentDate = parseLocalDate(employeeInfo[DATE_POSITION]);
            String name = employeeInfo[NAME_POSITION];
            int workHours = parseInteger(employeeInfo[HOURS_POSITION]);
            int rate = parseInteger(employeeInfo[RATE_POSITION]);
            int salary = workHours * rate;
            for (int i = 0; i < names.length; i++) {
                if (!currentDate.isBefore(dateStart) && !currentDate.isAfter(dateEnd)
                        && names[i].equals(name)) {
                    salaries[i] += salary;
                    break;
                }
            }
        }
        StringBuilder builder = new StringBuilder(REPORT_HEADER);
        builder.append(dateFrom).append(DELIMITER).append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(DELIMITER)
                    .append(salaries[i]);
        }
        return builder.toString();
    }

    private LocalDate parseLocalDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse date: " + date);
        }
    }

    private int parseInteger(String workInfo) {
        try {
            return Integer.parseInt(workInfo);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Can't parse work info: " + workInfo);
        }
    }
}
