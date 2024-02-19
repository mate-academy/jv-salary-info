package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS_WORKED = 2;
    private static final int INDEX_INCOME_PER_HOUR = 3;
    private static final String DASH_SEPARATOR = " - ";

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] employeeSalaries = new int[names.length];

        for (String row : data) {
            String[] rowElement = row.split(" ");
            String date = rowElement[INDEX_DATE];
            String name = rowElement[INDEX_NAME];
            int hoursWorked = Integer.parseInt(rowElement[INDEX_HOURS_WORKED]);
            int incomePerHour = Integer.parseInt(rowElement[INDEX_INCOME_PER_HOUR]);

            if (isDateInRange(date, dateFrom, dateTo)) {
                int employeeIndex = -1;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        employeeIndex = i;
                        break;
                    }
                }

                if (employeeIndex != -1) {
                    int totalEarnings = hoursWorked * incomePerHour;
                    employeeSalaries[employeeIndex] += totalEarnings;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            int totalEarnings = employeeSalaries[i];
            result.append(System.lineSeparator())
                    .append(names[i]).append(DASH_SEPARATOR).append(totalEarnings);
        }

        return result.toString();
    }

    private static boolean isDateInRange(String date, String dateFrom, String dateTo) {
        LocalDate transactionDate = LocalDate.parse(date, DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);

        return !transactionDate.isBefore(localDateFrom) && !transactionDate.isAfter(localDateTo);
    }
}
