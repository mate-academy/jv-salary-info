package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int datePosition = 0;
        final int employeePosition = 1;
        final int hoursPosition = 2;
        final int ratePosition = 3;

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryReport = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            salaryReport.append(names[i]).append(" - 0");
            if (i < names.length - 1) {
                salaryReport.append(System.lineSeparator());
            }
        }

        for (int i = 0; i < data.length; i++) {
            LocalDate parseDate = LocalDate.parse(data[i].split(" ")[datePosition], FORMATTER);
            String name = data[i].split(" ")[employeePosition];
            int hoursLogged = Integer.parseInt(data[i].split(" ")[hoursPosition]);
            int perHourRate = Integer.parseInt(data[i].split(" ")[ratePosition]);
            int salary = hoursLogged * perHourRate;
            if (parseDate.isEqual(parsedDateFrom) || parseDate.isAfter(parsedDateFrom)
                    && (parseDate.isEqual(parsedDateTo) || parseDate.isBefore(parsedDateTo))) {
                int namePosition = salaryReport.indexOf(name);
                int salaryStart = namePosition + name.length() + 3;
                int salaryEnd = salaryReport.indexOf(System.lineSeparator(), salaryStart) < 0
                        ? salaryReport.length()
                        : salaryReport.indexOf(System.lineSeparator(), salaryStart);
                int sumSalary = salary + Integer.parseInt(
                        salaryReport.substring(salaryStart, salaryEnd));
                salaryReport.replace(salaryStart, salaryEnd, String.valueOf(sumSalary));
            }
        }

        return salaryReport.toString();
    }
}
