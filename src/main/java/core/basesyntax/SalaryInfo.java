package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int TIME_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = getReportTitle(dateFrom, dateTo);
        return populateReportRows(names, data, localDateFrom, localDateTo, report);
    }

    private String populateReportRows(String[] names, String[] data, LocalDate localDateFrom,
                                    LocalDate localDateTo, StringBuilder report) {
        for (String name : names) {
            report.append(System.lineSeparator());
            int totalSalary = getTotalSalary(data, localDateFrom, localDateTo, name);
            report.append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return report.toString();
    }

    private StringBuilder getReportTitle(String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        return report;
    }

    private int getTotalSalary(String[] data, LocalDate localDateFrom,
                               LocalDate localDateTo, String name) {
        int total = 0;
        for (String record : data) {
            String[] recordValue = record.split(" ");

            String dateFromRecord = recordValue[DATE_INDEX];
            String nameFromRecord = recordValue[NAME_INDEX];
            int salaryFromRecord = Integer.valueOf(recordValue[SALARY_INDEX]);
            int timeFromRecord = Integer.valueOf(recordValue[TIME_INDEX]);

            total = validateRecord(localDateFrom, localDateTo, name, total,
                    dateFromRecord, nameFromRecord, salaryFromRecord, timeFromRecord);
        }
        return total;
    }

    private int validateRecord(LocalDate localDateFrom, LocalDate localDateTo,
                               String name, int total, String dateFromRecord,
                               String nameFromRecord, int salaryFromRecord, int timeFromRecord) {

        if (name.equals(nameFromRecord)) {
            LocalDate localDate = LocalDate.parse(dateFromRecord, FORMATTER);

            if (!localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo)) {
                total += salaryFromRecord * timeFromRecord;
            }
        }
        return total;
    }

}
