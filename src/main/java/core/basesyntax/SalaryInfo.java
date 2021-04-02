package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        String[] formatName = new String[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);
        int indexOfFormateName = 0;

        for (String name : names) {
            int salaryPerDay = 0;
            for (String datum : data) {
                String[] getInfoFromData = datum.split(" ");
                if (getInfoFromData[1].equals(name)) {
                    salaryPerDay += checkEmployee(getInfoFromData,
                            localDateFrom, localDateTo);
                }
            }
            formatName[indexOfFormateName] = writeInfoAboutEmployee(name,
                    salaryPerDay);
            indexOfFormateName++;
        }
        return writeInfoAboutAllEmployees(formatName,
                dateFrom,dateTo);
    }

    public int checkEmployee(String[] employee, LocalDate localDateFrom,
                              LocalDate localDateTo) {
        LocalDate workingDate = LocalDate.parse(employee[0], TIME_FORMATTER);
        int employeeWorkHour = Integer.parseInt(employee[2]);
        int employeeSalary = Integer.parseInt(employee[3]);

        if (workingDate.isAfter(localDateFrom.minusDays(1))
                && workingDate.isBefore(localDateTo.plusDays(1))) {
            return calculateSalary(employeeSalary, employeeWorkHour);
        }
        return 0;
    }

    public String writeInfoAboutEmployee(String name, int earnedMoney) {
        return name.concat(" - ").concat(String.valueOf(earnedMoney));
    }

    public int calculateSalary(int salary, int workedHour) {
        return salary * workedHour;
    }

    public String writeInfoAboutAllEmployees(String[] employees, String dataFrom,
                                             String dataTo) {
        StringBuilder allEmployee = new StringBuilder()
                .append("Report for period ").append(dataFrom)
                .append(" - ").append(dataTo);

        for (String employee : employees) {
            allEmployee.append("\n").append(employee);
        }
        return allEmployee.toString();
    }
}
