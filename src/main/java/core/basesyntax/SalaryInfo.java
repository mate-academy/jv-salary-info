package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private EmployeeWorkRecord createEmployeeWorkRecord(String data) {
        String[] employeeData = data.split(" ");

        return new EmployeeWorkRecord(
                LocalDate.parse(employeeData[DATE_INDEX], FORMATTER),
                employeeData[NAME_INDEX],
                Integer.parseInt(employeeData[HOURS_INDEX]),
                Integer.parseInt(employeeData[INCOME_INDEX])
        );
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null) {
            return null;
        }
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name: names) {
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(countSalaryByName(data, name, from, to));
        }

        return result.toString();
    }

    public int countSalaryByName(String[]data, String name, LocalDate from, LocalDate to) {
        int salary = 0;

        for (String infoByPerson: data) {
            EmployeeWorkRecord employeeWorkRecord = createEmployeeWorkRecord(infoByPerson);
            if (employeeWorkRecord.belongsTo(name)
                    && employeeWorkRecord.wasWorkingBetween(from, to)) {
                salary += employeeWorkRecord.calculateSalary();
            }
        }

        return salary;
    }
}
