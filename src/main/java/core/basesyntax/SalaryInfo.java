package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeSalaryOfPeriod = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        int salary = 0;
        LocalDate localDate;
        LocalDate localDateFrom;
        LocalDate localDateTo;
        localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (int j = 0; j < names.length; j++) {
            salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[][] dataInfo = new String[data.length][4];
                dataInfo[i] = data[i].split(" ");
                localDate = LocalDate.parse(dataInfo[i][0], FORMATTER);
                String employeeNames = dataInfo[i][1];
                int workingHours = Integer.parseInt(dataInfo[i][2]);
                int salaryForHours = Integer.parseInt(dataInfo[i][3]);
                if (names[j].equals(employeeNames) && localDate.plusDays(1).isAfter(localDateFrom)
                        && localDate.minusDays(1).isBefore(localDateTo)) {
                    salary += workingHours * salaryForHours;
                }
            }
            employeeSalaryOfPeriod
                    .append(System.lineSeparator())
                    .append(names[j]).append(" - ")
                    .append(salary);
        }
        return employeeSalaryOfPeriod.toString();
    }
}
