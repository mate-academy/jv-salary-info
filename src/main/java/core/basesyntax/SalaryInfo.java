package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_PER_HOUR_INDEX = 3;
    public static final String REPORT_BEGINNING = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder resultBuilder = new StringBuilder(REPORT_BEGINNING)
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            resultBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            int totalSalary = calculateSalary(name, data, localDateFrom, localDateTo);
            resultBuilder.append(totalSalary);
        }
        return resultBuilder.toString();
    }

    private int calculateSalary(String name, String[] data,
                                LocalDate localDateFrom, LocalDate localDateTo) {
        int totalSalary = 0;
        for (String lineData : data) {
            String[] arrData = lineData.split(" ");
            String nameData = arrData[NAME_INDEX];
            if (name.equals(nameData)) {
                LocalDate dateData = LocalDate.parse(arrData[DATE_INDEX], FORMATTER);
                if (dateData.compareTo(localDateFrom) >= 0
                        && dateData.compareTo(localDateTo) <= 0) {
                    totalSalary += Integer.valueOf(arrData[HOURS_INDEX])
                            * Integer.valueOf(arrData[SALARY_PER_HOUR_INDEX]);
                }
            }
        }
        return totalSalary;
    }
}
