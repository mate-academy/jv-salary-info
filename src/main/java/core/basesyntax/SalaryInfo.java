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
        int salaryPerDay = 0;

        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                String[] getInfoFromData = datum.split(" ");
                if (getInfoFromData[1].equals(names[i])) {
                    salaryPerDay += checkEmployee(getInfoFromData,
                            localDateFrom, localDateTo);
                }
            }
            formatName[i] = writeInfoAboutEmployee(names[i],
                    salaryPerDay);
            salaryPerDay = 0;
        }

        return writeInfoAboutAllEmployee(formatName,
                dateFrom,dateTo);
    }

    public int checkEmployee(String[] employee, LocalDate localDateFrom,
                              LocalDate localDateTo) {
        LocalDate workingDate = LocalDate.parse(employee[0], TIME_FORMATTER);
        int employeeWorkHour = Integer.parseInt(employee[2]);
        int employeeSalary = Integer.parseInt(employee[3]);

        if (workingDate.isAfter(localDateFrom)
                && workingDate.isBefore(localDateTo.plusDays(1))) {
            return salaryCalculator(employeeSalary, employeeWorkHour);
        }

        return 0;
    }

    public String writeInfoAboutEmployee(String name, int earnedMoney) {
        return name.concat(" - ").concat(String.valueOf(earnedMoney));
    }

    public int salaryCalculator(int salary, int workedHour) {
        return salary * workedHour;
    }

    public String writeInfoAboutAllEmployee(String[] employees, String dataFrom,
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
