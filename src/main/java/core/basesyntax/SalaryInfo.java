package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employeeData = data[j].split(" ");
                LocalDate date = LocalDate.parse(employeeData[0], DATE_FORMATTER);
                String employeeName = employeeData[1];
                int employeeHours = Integer.parseInt(employeeData[2]);
                int employeeHourRate = Integer.parseInt(employeeData[3]);

                if (employeeName.equals(names[i]) && !date.isBefore(from) && !date.isAfter(to)) {
                    totalSalary += employeeHours * employeeHourRate;
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
