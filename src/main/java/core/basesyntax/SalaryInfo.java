package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DASH = " - ";
    private static final int NAME_INDEX = 1;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final int HOURS_AMOUNT_INDEX = 2;
    private static final int DATE_INDEX = 0;
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

    private String createReport(String[] names, String[] data, LocalDate from, LocalDate to) {
        StringBuilder report = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String entryData : data) {
                String[] splitData = entryData.split(SPLIT_REGEXP);
                LocalDate entryDate = LocalDate.parse(
                        splitData[DATE_INDEX], DATE_FORMATE
                );
                if (isDateInRange(entryDate,
                        from,
                        to)
                        && name.equals(splitData[NAME_INDEX])) {
                    salary += Integer.parseInt(splitData[HOURS_AMOUNT_INDEX])
                            * Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
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
