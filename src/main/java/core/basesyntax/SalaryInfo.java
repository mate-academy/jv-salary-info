package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            String name = names[i];

            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[0], dateFormatter);

                if (!date.isBefore(fromDate) && !date.isAfter(toDate) && parts[1].equals(name)) {
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int incomePerHour = Integer.parseInt(parts[3]);
                    salary += hoursWorked * incomePerHour;
                }
            }

            builder.append(name).append(" - ").append(salary);

            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
