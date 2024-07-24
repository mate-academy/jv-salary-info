package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromForm = parseDate(dateFrom);
        LocalDate dateToForm = parseDate(dateTo);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String part : data) {
                String[] parts = part.split(" ");
                String dateStr = parts[0];
                String employeeName = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);
                LocalDate workedDate = parseDate(dateStr);

                if (!workedDate.isBefore(dateFromForm) && !workedDate.isAfter(dateToForm)
                        && employeeName.equals(name)) {
                    salary += hoursWorked * incomePerHour;
                }

            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString().trim();
    }

    private LocalDate parseDate(String data) {
        return LocalDate.parse(data, DATE_TIME_FORMATTER);
    }
}

