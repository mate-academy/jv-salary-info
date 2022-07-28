package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final short DATE_INDEX = 0;
    private static final short NAME_INDEX = 1;
    private static final short NUMBER_OF_HOURS_INDEX = 2;
    private static final short HOURLY_PAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate digitDateFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate digitDateTo = LocalDate.parse(dateTo, dateFormatter);
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String dataElement: data) {
                String[] dataFields = dataElement.split(" ");
                if (name.equals(dataFields[NAME_INDEX])
                        && ((LocalDate.parse(dataFields[DATE_INDEX],dateFormatter)
                        .isAfter(digitDateFrom)
                        && LocalDate.parse(dataFields[DATE_INDEX],dateFormatter)
                        .isBefore(digitDateTo))
                        || (LocalDate.parse(dataFields[DATE_INDEX],dateFormatter)
                        .equals(digitDateFrom)
                        || LocalDate.parse(dataFields[DATE_INDEX],dateFormatter)
                        .equals(digitDateTo)))) {
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
