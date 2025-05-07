package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_SALARY = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int earnedSalary = 0;
        StringBuilder report = new StringBuilder();
        String newLine = System.lineSeparator();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] bufferStringBase = data[i].split(" ");
                if (checkPeriod(dateFrom, dateTo, bufferStringBase[INDEX_DATE])) {
                    if (name.equals(bufferStringBase[INDEX_NAME])) {
                        earnedSalary = earnedSalary
                                + Integer.parseInt(bufferStringBase[INDEX_HOURS])
                                * Integer.parseInt(bufferStringBase[INDEX_SALARY]);
                    }
                }
            }
            report.append(newLine).append(name).append(" - ").append(earnedSalary);
            earnedSalary = 0;
        }
        return report.toString();
    }

    private boolean checkPeriod(String dateFrom, String dateTo, String dateBase) {
        try {
            LocalDate formatDateFrom = LocalDate.parse(dateFrom, formatter);
            LocalDate formatDateTo = LocalDate.parse(dateTo, formatter);
            LocalDate formatDateBase = LocalDate.parse(dateBase, formatter);
            return (formatDateBase.compareTo(formatDateFrom) >= 0
                    && formatDateBase.compareTo(formatDateTo) <= 0);
        } catch (Exception e) {
            throw new RuntimeException("Error during date parsing", e);
        }
    }
}
