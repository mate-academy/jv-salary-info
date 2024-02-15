package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DASH = " - ";
    private static final int DATA_ARRAY_INDEX_FIRST = 1;
    private static final int DATA_ARRAY_INDEX_THREE = 3;
    private static final int DATA_ARRAY_INDEX_TWO = 2;
    private static final int DATA_ARRAY_INDEX_ZERO = 0;
    private static final String REPORT_TITLE = "Report for period ";
    private static final String SPLIT_REGEXP = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATE);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATE);
        StringBuilder report = new StringBuilder();
        String title = createTitle(dateFrom, dateTo);
        String salaryInfo = createReport(names, data, from, to);
        report.append(title)
                .append(salaryInfo);

        return report.toString().trim();
    }

    private String createReport(String[] names,
                                String[] data,
                                LocalDate from,
                                LocalDate to) {
        StringBuilder report = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String entryData : data) {
                String[] splitData = entryData.split(SPLIT_REGEXP);
                LocalDate entryDate = LocalDate.parse(
                        splitData[DATA_ARRAY_INDEX_ZERO],
                        DATE_FORMATE
                );
                if (isDateInRange(entryDate,
                        from,
                        to)
                        && name.equals(splitData[DATA_ARRAY_INDEX_FIRST])) {
                    salary += Integer.parseInt(splitData[DATA_ARRAY_INDEX_TWO])
                            * Integer.parseInt(splitData[DATA_ARRAY_INDEX_THREE]);
                }
            }
            report.append(name)
                    .append(DASH)
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return report.toString();
    }

    private String createTitle(String dateFrom, String dateTo) {
        return REPORT_TITLE
                + dateFrom
                + DASH
                + dateTo
                + System.lineSeparator();
    }

    private boolean isDateInRange(LocalDate entryDate, LocalDate dateFrom, LocalDate dateTo) {
        return (!entryDate.isBefore(dateFrom) && !entryDate.isAfter(dateTo));
    }
}
