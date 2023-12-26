package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_OUR = 3;
    private static final String REPORT = "Report for period ";
    private int salary = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            for (String employeeData : data) {
                if (employeeData.contains(name)) {
                    String[] dateNameHoursSalary = employeeData.split(" ");
                    LocalDate betweenDate = LocalDate.parse(
                            dateNameHoursSalary[INDEX_OF_DATE], FORMATTER);
                    if (betweenDate.isAfter(firstDate) && betweenDate.isBefore(lastDate)
                            || betweenDate.isEqual(lastDate)) {
                        salary += Integer.parseInt(dateNameHoursSalary[INDEX_OF_HOURS])
                                * Integer.parseInt(
                                dateNameHoursSalary[INDEX_OF_SALARY_PER_OUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
            salary -= salary;
        }
        return REPORT + dateFrom + " - " + dateTo
                + stringBuilder;
    }
}
