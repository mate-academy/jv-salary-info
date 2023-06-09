package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String records : data) {
                String[] splittedData = records.split(" ");
                String dateFromData = splittedData[0];
                String nameFromData = splittedData[1];
                String hourPerDayFromData = splittedData[2];
                String salaryFromData = splittedData[3];
                if (nameFromData.equals(name)) {
                    if (isValidDate(dateFrom, dateTo, dateFromData)) {
                        salary += Integer.parseInt(hourPerDayFromData)
                                * Integer.parseInt(salaryFromData);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private boolean isValidDate(String dateFromString, String dateToString, String dateString) {
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date.isAfter(dateFrom) && date.isBefore(dateTo) || date.isEqual(dateTo);
    }
}
