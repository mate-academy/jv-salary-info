package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String REGEX = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        LocalDate particularDay;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        String[] info;
        StringBuilder employeeSalary = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name: names) {
            for (String dataPerDay: data) {
                info = dataPerDay.split(REGEX);
                particularDay = LocalDate.parse(info[DATE_INDEX], FORMATTER);

                if (name.equals(info[NAME_INDEX])
                        && particularDay.compareTo(localDateFrom) >= 0
                        && particularDay.compareTo(localDateTo) <= 0) {
                    salary += Integer.parseInt(info[HOURS_INDEX])
                            * Integer.parseInt(info[INCOME_INDEX]);
                }
            }
            employeeSalary.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
            salary = 0;
        }

        return employeeSalary.toString();
    }
}
