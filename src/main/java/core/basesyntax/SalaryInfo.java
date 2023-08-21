package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX_IN_REPORT_LINE = 0;
    private static final int NAME_INDEX_IN_REPORT_LINE = 1;
    private static final int WORKING_HOURS_INDEX_IN_REPORT_LINE = 2;
    private static final int INCOME_PER_HOUR_INDEX_IN_REPORT_LINE = 3;
    private static final String SEPARATOR_IN_REPORT_LINE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append(String.format("Report for period %s - %s",
                                            startDate.format(DATE_FORMATTER),
                                            endDate.format(DATE_FORMATTER)));
        LocalDate reportDate;

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ");
            int currentWorkerSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] reportLine = data[j].split(SEPARATOR_IN_REPORT_LINE);
                reportDate = LocalDate.parse(reportLine[DATE_INDEX_IN_REPORT_LINE], DATE_FORMATTER);
                if (reportLine[NAME_INDEX_IN_REPORT_LINE].equals(names[i])
                        && (reportDate.isAfter(startDate) || reportDate.isEqual(startDate))
                        && (reportDate.isBefore(endDate) || reportDate.isEqual(endDate))) {
                    currentWorkerSalary
                            += Integer.parseInt(reportLine[WORKING_HOURS_INDEX_IN_REPORT_LINE])
                            * Integer.parseInt(reportLine[INCOME_PER_HOUR_INDEX_IN_REPORT_LINE]);
                }
            }
            report.append(currentWorkerSalary);
        }
        return report.toString();
    }
}
