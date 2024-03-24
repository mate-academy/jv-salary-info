package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final String REPORT_PERIOD_HEADER = "Report for period ";
    private static final String DASH = " - ";
    private static final int NAMES_CONST = 0;
    private static final int HOURS_CONST = 2;
    private static final int INCOME_PER_HOUR_CONST = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_PERIOD_HEADER).append(dateFrom).append(DASH).append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            String name = names[i];

            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[NAMES_CONST], dateFormatter);

                if (!date.isBefore(fromDate) && !date.isAfter(toDate) && parts[1].equals(name)) {
                    int hoursWorked = Integer.parseInt(parts[HOURS_CONST]);
                    int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_CONST]);
                    salary += hoursWorked * incomePerHour;
                }
            }

            builder.append(name).append(DASH).append(salary).append(System.lineSeparator());
        }

        int lastIndex = builder.lastIndexOf(System.lineSeparator());
        return builder.delete(lastIndex, builder.length()).toString();
    }
}
