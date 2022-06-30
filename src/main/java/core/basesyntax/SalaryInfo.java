package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

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
                localDate = LocalDate.parse(dataInfo[i][DATE_INDEX], FORMATTER);
                String employeeNames = dataInfo[i][NAME_INDEX];
                int workingHours = Integer.parseInt(dataInfo[i][WORKING_HOURS_INDEX]);
                int salaryForHours = Integer.parseInt(dataInfo[i][SALARY_INDEX]);
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
