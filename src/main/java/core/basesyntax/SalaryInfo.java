package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int[] totalSalary = new int[names.length];
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < names.length; i++) {
            for (String dateInfo : data) {
                String[] salaryData = dateInfo.split(" ");
                LocalDate date = LocalDate.parse(salaryData[DATE_INDEX], DATE_FORMAT);
                if (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    int salaryForDay = Integer.parseInt(salaryData[HOURS_INDEX])
                                * Integer.parseInt(salaryData[PRICE_INDEX]);
                    if (names[i].equals(salaryData[NAME_INDEX])) {
                        totalSalary[i] += salaryForDay;
                    }
                }
            }
            report.append("\n").append(names[i]).append(" - ").append(totalSalary[i]);
        }
        return "Report for period " + dateFrom + " - " + dateTo + report;
    }
}
