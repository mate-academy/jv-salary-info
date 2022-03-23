package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        DateRangeValidator checker = new DateRangeValidator(startDate, endDate);

        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(names[i]);
        }

        for (int i = 0; i < names.length; i++) {
            int sunSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] arrayData = data[j].split(" ");
                LocalDate testDate = LocalDate.parse(arrayData[0], FORMATTER);
                if (names[i].equals(arrayData[1]) && checker.isWithinRange(testDate)) {
                    sunSalary += Integer.parseInt(arrayData[2])
                            * Integer.parseInt(arrayData[3]);
                }
                employees[i].setSalary(sunSalary);
            }
        }
        return getOutputString(employees, dateFrom, dateTo);
    }

    public String getOutputString(Employee[] arrayEmployee, String dateFrom, String dateTo) {
        String nextLine = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(nextLine);

        for (int i = 0; i < arrayEmployee.length; i++) {
            text.append(arrayEmployee[i].getName())
                    .append(" - ")
                    .append(arrayEmployee[i].getSalary())
                    .append(nextLine);
        }
        return text.toString().trim();
    }
}
