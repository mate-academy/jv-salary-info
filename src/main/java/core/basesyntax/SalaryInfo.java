package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int namesArrSize = names.length;
        final int[] totalSalary = new int[namesArrSize];
        StringBuilder reportSalaryInfo = new StringBuilder();
        StringBuilder fullReport = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (int i = 0; i < namesArrSize; i++) {
            for (String dateInfo : data) {
                String[] salaryData = dateInfo.split(" ");
                LocalDate date = LocalDate.parse(salaryData[DATE_INDEX], DATE_FORMATTER);
                if (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    if (names[i].equals(salaryData[NAME_INDEX])) {
                        int salaryPerDay = Integer.parseInt(salaryData[HOURS_INDEX])
                                * Integer.parseInt(salaryData[PRICE_INDEX]);
                        totalSalary[i] += salaryPerDay;
                    }
                }
            }
            reportSalaryInfo.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(totalSalary[i]);
        }
        return fullReport.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(reportSalaryInfo).toString();
    }
}
