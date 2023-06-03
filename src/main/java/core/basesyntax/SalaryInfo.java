package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;

            for (String employeeData : data) {
                String[] parts = employeeData.split(" ");
                final int dateIndex = 0;
                final int nameIndex = 1;
                final int workingHoursIndex = 2;
                final int salaryPerHourIndex = 3;

                if (name.equals(parts[nameIndex])) {
                    LocalDate date = LocalDate.parse(parts[dateIndex], formatter);
                    if (date.isEqual(localDateFrom)
                            || date.isAfter(localDateFrom)
                            && date.isEqual(localDateTo)
                            || date.isAfter(localDateFrom)
                            && date.isBefore(localDateTo)) {
                        salary += Integer.parseInt(parts[workingHoursIndex])
                                * Integer.parseInt(parts[salaryPerHourIndex]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}

