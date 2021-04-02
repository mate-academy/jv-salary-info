package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATION_WITH_DOT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, FORMATION_WITH_DOT);
        LocalDate dateToParsed = LocalDate.parse(dateTo, FORMATION_WITH_DOT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");

        for (String name: names) {
            int employeeSalary = 0;
            for (String datum : data) {
                String[] employeesInformation = datum.split(" ");
                String nameOfEmployee = employeesInformation[1];
                int timesGotSalary = Integer.parseInt(employeesInformation[2]);
                int salary = Integer.parseInt(employeesInformation[3]);
                String dateWithoutDot = employeesInformation[0];
                LocalDate date = LocalDate.parse(dateWithoutDot, FORMATION_WITH_DOT);

                if (dateFromParsed.minusDays(1).isBefore(date)
                        && dateToParsed.plusDays(1).isAfter(date)
                        && nameOfEmployee.equals(name)) {
                    employeeSalary += timesGotSalary * salary;
                } else {
                    employeeSalary += 0;
                }
            }
            stringBuilder.append(name).append(" - ").append(employeeSalary).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
