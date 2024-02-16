package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS_WORKED = 2;
    private static final int INDEX_INCOME_PER_HOUR = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] employeeSalaries = new int[names.length];

        for (String datum : data) {
            String[] dataParts = datum.split(" ");
            String date = dataParts[INDEX_DATE];
            String name = dataParts[INDEX_NAME];
            int hoursWorked = Integer.parseInt(dataParts[INDEX_HOURS_WORKED]);
            int incomePerHour = Integer.parseInt(dataParts[INDEX_INCOME_PER_HOUR]);

            if (isDateInRange(date, dateFrom, dateTo)) {
                int employeeIndex = Arrays.asList(names).indexOf(name);
                if (employeeIndex != -1) {
                    int totalEarnings = hoursWorked * incomePerHour;
                    employeeSalaries[employeeIndex] += totalEarnings;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            int totalEarnings = employeeSalaries[i];
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(totalEarnings);
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
