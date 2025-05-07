package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        int salary;
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            salary = 0;
            for (String datum : data) {
                String[] lines = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(lines[0], formatter);
                String nameUser = lines[1];
                String workHours = lines[2];
                String incomePerHour = lines[3];
                if (!currentDate.isBefore(start) && !currentDate.isAfter(end)
                        && name.equals(nameUser)) {
                    salary += Integer.parseInt(workHours) * Integer.parseInt(incomePerHour);
                } else if (currentDate.isEqual(start) && name.equals(nameUser)
                        || currentDate.isEqual(end) && name.equals(nameUser)) {
                    salary += Integer.parseInt(workHours) * Integer.parseInt(incomePerHour);
                }
            }
            salaryInfo.append(System.lineSeparator());
            salaryInfo.append(name);
            salaryInfo.append(" - ");
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}
