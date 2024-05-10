package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = convertToDate(dateFrom);
        LocalDate toDate = convertToDate(dateTo);
        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String rowData : data) {
                String[] rowDataArray = rowData.split(" ");
                LocalDate currentDate = convertToDate(rowDataArray[0]);
                if (isDateInRange(currentDate, fromDate, toDate) && name
                        .equals(rowDataArray[1])) {
                    totalSalary += Integer.parseInt(rowDataArray[2]) * Integer
                            .parseInt(rowDataArray[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }

    private static boolean isDateInRange(LocalDate date, LocalDate fromDate, LocalDate toDate) {
        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                && (date.isEqual(toDate) || date.isBefore(toDate));
    }

    private static LocalDate convertToDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }
}
