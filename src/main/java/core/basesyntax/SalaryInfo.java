package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo extends Exception {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int earnedsSalary = 0;
        StringBuilder report = new StringBuilder();
        String newLine = System.lineSeparator();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] spBf = data[i].split(" ");
                if (checkPeriod(dateFrom, dateTo, spBf[0])) {
                    if (name.equals(spBf[1])) {
                        earnedsSalary = earnedsSalary + Integer.parseInt(spBf[2])
                                * Integer.parseInt(spBf[3]);
                    }
                }
            }
            report.append(newLine).append(name).append(" - ").append(earnedsSalary);
            earnedsSalary = 0;
        }
        return report.toString();
    }

    private boolean checkPeriod(String dateFrom, String dateTo, String dateBase) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate formatDateFrom = LocalDate.parse(dateFrom, formatter);
            LocalDate formatDateTo = LocalDate.parse(dateTo, formatter);
            LocalDate formatDateBase = LocalDate.parse(dateBase, formatter);
            return (formatDateBase.compareTo(formatDateFrom) >= 0
                    && formatDateBase.compareTo(formatDateTo) <= 0);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return false;
    }
}
