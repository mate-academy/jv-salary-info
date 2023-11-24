package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String DELIMITER = " ";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, dateFormat);
        LocalDate endDate = LocalDate.parse(dateTo, dateFormat);
        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator());
            int salary = 0;
            for (String dates : data) {
                String[] parts = dates.split(DELIMITER);
                LocalDate localDate = LocalDate.parse(parts[DATE_INDEX], dateFormat);
                if ((localDate.isAfter(startDate) || localDate.isEqual(startDate))
                        && (localDate.isBefore(endDate) || localDate.isEqual(endDate))) {
                    if (name.equals(parts[NAME_INDEX])) {
                        salary = salary + Integer.parseInt(parts[HOURS_INDEX])
                                * Integer.parseInt(parts[RATE_INDEX]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
