package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int COST_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        reportBuilder.append("Report for period ")
                     .append(DATE_FORMATTER.format(startDate))
                     .append(" - ")
                     .append(DATE_FORMATTER.format(endDate));
        int salary;
        for (String name : names) {
            salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate salaryDate = LocalDate.parse(splittedLine[DATE_INDEX], DATE_FORMATTER);
                boolean isValidDate = ((salaryDate.equals(startDate) || salaryDate.equals(endDate))
                        || (salaryDate.isAfter(startDate) && salaryDate.isBefore(endDate)));
                if (isValidDate && name.equals(splittedLine[NAME_INDEX])) {
                    salary +=
                            Integer.parseInt(splittedLine[HOURS_INDEX])
                                    * Integer.parseInt(splittedLine[COST_INDEX]);
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
