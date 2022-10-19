package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[][] salariesList = new String[names.length][2];
        for (String record : data) {
            String[] parcedRecord = record.split(" ");
            for (int i = 0; i < names.length; i++) {
                salariesList[i][0] = names[i];
                if (salariesList[i][1] == null) {
                    salariesList[i][1] = "0";
                }
                if (names[i].equals(parcedRecord[1])
                        && dateLimitsChecker(parcedRecord[0], dateFrom, dateTo)) {
                    salariesList[i][1] = calculateSalary(salariesList[i][1], parcedRecord[2],
                            parcedRecord[3]);
                }
            }

        }
        return getInfoString(salariesList, dateFrom, dateTo);
    }

    private String calculateSalary(String initialSalary,
                                   String workingHours,
                                   String incomePerHour) {
        return String.valueOf(Integer.parseInt(initialSalary)
                + Integer.parseInt(workingHours) * Integer.parseInt(incomePerHour));
    }

    private boolean dateLimitsChecker(String salaryDate, String dateFrom, String dateTo) {
        LocalDate salaryDateFormatted = LocalDate.parse(salaryDate, DATE_FORMATTER);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMATTER);
        return salaryDateFormatted.isAfter(dateFromFormatted)
                && salaryDateFormatted.isBefore(dateToFormatted)
                || salaryDate.equals(dateFrom)
                || salaryDate.equals(dateTo);
    }

    private String getInfoString(String[][] salaries, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String[] record : salaries) {
            stringBuilder.append(System.lineSeparator())
                    .append(record[0])
                    .append(" - ")
                    .append(record[1]);
        }
        return stringBuilder.toString();
    }
}
