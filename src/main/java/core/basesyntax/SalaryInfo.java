package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INCOME_PER_HOUR = 3;
    private static final int HOURS_WORKED = 2;
    private static final int EMPLOYEE_NAME = 1;
    private static final int CURRENT_DATE = 0;
    private static final int NAME_NOT_FOUND = -1;
    private static final int NO_LINE_SEPARATOR = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportForPeriod = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        LocalDate start = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate stop = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        int[] salaries = new int[names.length];

        for (String string : data) {
            String[] words = string.split(" ");
            LocalDate current = LocalDate.parse(words[CURRENT_DATE], DATE_TIME_FORMATTER);

            if (!current.isBefore(start) && !current.isAfter(stop)) {
                String employeeName = words[EMPLOYEE_NAME];
                int hoursWorked = Integer.parseInt(words[HOURS_WORKED]);
                int incomePerHour = Integer.parseInt(words[INCOME_PER_HOUR]);

                int index = NAME_NOT_FOUND;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        index = i;
                        break;
                    }
                }

                if (index != NAME_NOT_FOUND) {
                    salaries[index] += hoursWorked * incomePerHour;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            if (i == names.length - NO_LINE_SEPARATOR) {
                reportForPeriod.append(names[i]).append(" - ").append(salaries[i]);
                break;
            }
            reportForPeriod.append(names[i]).append(" - ")
                    .append(salaries[i]).append(System.lineSeparator());
        }
        return reportForPeriod.toString();
    }
}
