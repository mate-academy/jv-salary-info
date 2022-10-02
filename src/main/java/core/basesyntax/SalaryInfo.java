package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEAD = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final int POSITION_DATE = 0;
    private static final int POSITION_NAME = 1;
    private static final int POSITION_WORKING_HOUR = 2;
    private static final int POSITION_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] resultSalary = new int[names.length];
        StringBuilder report = new StringBuilder();
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, formatter);
        for (String record : data) {
            if (record.isBlank()) {
                continue;
            };
            String[] parts = record.split(DELIMITER);
            LocalDate dateRecord = LocalDate.parse(parts[POSITION_DATE], formatter);
            if ((dateRecord.isAfter(dateFromLocalDate) && dateRecord.isBefore(dateToLocalDate))
                    || (dateRecord.equals(dateFromLocalDate))
                    || (dateRecord.equals(dateToLocalDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(parts[POSITION_NAME])) {
                        resultSalary[i] += Integer.parseInt(parts[POSITION_WORKING_HOUR])
                                * Integer.parseInt(parts[POSITION_INCOME_PER_HOUR]);
                    }
                }
            }
        }
        report.append(REPORT_HEAD).append(dateFrom).append(SEPARATOR).append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(SEPARATOR)
                    .append(String.valueOf(resultSalary[i]));
        }
        return report.toString();
    }
}
