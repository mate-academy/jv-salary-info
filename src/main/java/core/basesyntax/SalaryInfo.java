package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_QUANTITY_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int totalSalary;
        for (String name : names) {
            totalSalary = 0;
            for (String dataLine : data) {
                String[] dataElements = dataLine.split("\\s");
                if (name.equals(dataElements[NAME_INDEX])
                        && isDateInRange(dataElements[DATE_INDEX], dateFrom, dateTo)) {
                    totalSalary += Integer.parseInt(dataElements[HOUR_QUANTITY_INDEX])
                            * Integer.parseInt(dataElements[SALARY_PER_HOUR_INDEX]);
                }
            }
            salaryInfo.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        return LocalDate.parse(date, FORMATTER).isEqual(LocalDate.parse(dateFrom, FORMATTER))
                || LocalDate.parse(date, FORMATTER).isEqual(LocalDate.parse(dateTo, FORMATTER))
                || LocalDate.parse(date, FORMATTER).isBefore(LocalDate.parse(dateTo, FORMATTER))
                && LocalDate.parse(date, FORMATTER).isAfter(LocalDate.parse(dateFrom, FORMATTER));
    }
}
