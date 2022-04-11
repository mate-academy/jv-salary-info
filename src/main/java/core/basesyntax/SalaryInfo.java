package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int COST_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder reportBuilder = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate salaryDate = null;
        boolean isWithinRange = false;
        reportBuilder.append("Report for period ")
                     .append(DATE_FORMATTER.format(startDate))
                     .append(" - ")
                     .append(DATE_FORMATTER.format(endDate));
        String[] fields = new String[4];
        int salary;
        for (String name : names) {
            salary = 0;
            for (String dataStr : data) {
                fields = dataStr.split(" ");
                salaryDate = LocalDate.parse(fields[DATE_INDEX], DATE_FORMATTER);
                isWithinRange = ((salaryDate.equals(startDate) || salaryDate.equals(endDate))
                        || (salaryDate.isAfter(startDate) && salaryDate.isBefore(endDate)));
                if (isWithinRange && name.equals(fields[NAME_INDEX])) {
                    salary += Integer.parseInt(fields[HOURS_INDEX]) * Integer.parseInt(fields[COST_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return reportBuilder.toString();
    }
}