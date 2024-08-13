package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryArr = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String line: data) {
                String[] s = line.split(" ");
                if (line.contains(names[i]) && isDateValid(s[0], dateFrom, dateTo)) {
                    salaryArr[i] += Integer.parseInt(s[2]) * Integer.parseInt(s[3]);
                }
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaryArr[i]);
        }
        return report.toString();
    }

    public static boolean isDateValid(String workDate, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parsedWorkDate = LocalDate.parse(workDate, formatter);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        if (parsedWorkDate.isEqual(parsedDateFrom) || parsedWorkDate.isEqual(parsedDateTo)) {
            return true;
        }
        return parsedWorkDate.isAfter(parsedDateFrom) && parsedWorkDate.isBefore(parsedDateTo);
    }
}
