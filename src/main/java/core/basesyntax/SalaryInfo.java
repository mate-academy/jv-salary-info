package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                if (name.equals(splittedLine[NAME_INDEX])) {
                    LocalDate dataDate = LocalDate.parse(splittedLine[DATE_INDEX], DATE_FORMATTER);
                    if ((dataDate.isEqual(fromDate) || dataDate.isAfter(fromDate))
                            && (dataDate.isEqual(toDate) || dataDate.isBefore(toDate))) {
                        salary += Integer.valueOf(splittedLine[HOURS_INDEX])
                                * Integer.valueOf(splittedLine[INCOME_INDEX]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
