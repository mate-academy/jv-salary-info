package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final short DATE_INDEX = 0;
    private static final short NAME_INDEX = 1;
    private static final short NUMBER_OF_HOURS_INDEX = 2;
    private static final short HOURLY_PAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate digitDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate digitDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate currentDate;
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String dataElement: data) {
                String[] dataFields = dataElement.split(" ");
                currentDate = LocalDate.parse(dataFields[DATE_INDEX], FORMATTER);
                if (name.equals(dataFields[NAME_INDEX])
                        && ((currentDate
                        .isAfter(digitDateFrom)
                        && currentDate
                        .isBefore(digitDateTo))
                        || currentDate
                        .equals(digitDateFrom)
                        || currentDate
                        .equals(digitDateTo))) {
                    salary += Integer.parseInt(dataFields[NUMBER_OF_HOURS_INDEX])
                            * Integer.parseInt(dataFields[HOURLY_PAY_INDEX]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
