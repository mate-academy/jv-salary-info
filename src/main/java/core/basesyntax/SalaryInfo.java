package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_POSITION_IN_ARRAY = 0;
    public static final int NAME_POSITION_IN_ARRAY = 1;
    public static final int HOURS_POSITION_IN_ARRAY = 2;
    public static final int HOURLY_RATE_POSITION_IN_ARRAY = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate toDate = parse(dateTo, formatter);
        LocalDate fromDate = parse(dateFrom, formatter);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salaryArray = 0;
            for (String info : data) {
                String[] parts = info.split(" ");
                LocalDate actualDate = parse(parts[DATE_POSITION_IN_ARRAY], formatter);
                if (parts[NAME_POSITION_IN_ARRAY].equals(name)
                        && (actualDate.isBefore(toDate) || actualDate.isEqual(toDate))
                        && (actualDate.isAfter(fromDate) || actualDate.isEqual(fromDate))) {
                    salaryArray += Integer.parseInt(parts[HOURS_POSITION_IN_ARRAY])
                            * Integer.parseInt(parts[HOURLY_RATE_POSITION_IN_ARRAY]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(salaryArray);
        }
        return report.toString();
    }
}
