package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final String REPORT_PERIOD_HEADER = "Report for period ";
    private static final String DASH = " - ";
    private static final int DATES_INDEX = 0;
    private static final int NAMES_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_PERIOD_HEADER).append(dateFrom).append(DASH).append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            String name = names[i];

            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[DATES_INDEX], dateFormatter);

                if (!date.isBefore(fromDate) && !date.isAfter(toDate) && parts[NAMES_INDEX]
                        .equals(name)) {
                    int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
                    salary += hoursWorked * incomePerHour;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(DASH).append(salary);
        }

        return builder.toString();
    }
}
