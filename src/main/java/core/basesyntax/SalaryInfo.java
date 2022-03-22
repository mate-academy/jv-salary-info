package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localeFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localeTo = LocalDate.parse(dateTo, FORMATTER);
        int salary = 0;
        StringBuilder report = new StringBuilder(" Report for period "
                + dateFrom + " - "
                + dateTo + System.lineSeparator());
        if (localeFrom.isAfter(localeTo)) {
            throw new RuntimeException("Invalid dates. Date from: "
                    + localeFrom + " could'd be after  date to: "
                    + localeTo);
        }
        for (String name : names) {
            for (String temp : data) {
                String[] dateName = temp.split(" ");
                LocalDate tempData = LocalDate.parse(dateName[0], FORMATTER);
                if (((tempData.isBefore(localeTo)
                        && tempData.isAfter(localeFrom))
                        || tempData.equals(localeTo))
                        && dateName[1].equals(name)) {
                    salary += Integer.parseInt(dateName[2]) * Integer.parseInt(dateName[3]);
                }
            }
            report.append(name).append(" - ").append(salary).append(System.lineSeparator());
            salary = 0;
        }
        return report.toString().trim();
    }
}
