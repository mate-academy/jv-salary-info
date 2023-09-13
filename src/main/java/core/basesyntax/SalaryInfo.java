package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPLITTER = " ";
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PRICE = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int[] totalSalary = new int[names.length];
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] salaryData = data[j].split(SPLITTER);
                LocalDate date = LocalDate.parse(salaryData[DATE], DATE_FORMAT);
                if (date.isAfter(fromDate) && date.isBefore(toDate)
                        || date.equals(fromDate) || date.equals(toDate)) {
                    int salaryPerDay = Integer.parseInt(salaryData[HOURS])
                            * Integer.parseInt(salaryData[PRICE]);
                    if (names[i].equals(salaryData[NAME])) {
                        totalSalary[i] += salaryPerDay;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append("\n").append(names[i]).append(" - ").append(totalSalary[i]);
        }
        return "Report for period " + dateFrom + " - " + dateTo + report;
    }
}
