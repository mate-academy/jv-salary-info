package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final short DATE_INDEX = 0;
    private static final short NAME_INDEX = 1;
    private static final short NUMBER_OF_HOURS_INDEX = 2;
    private static final short HOURLY_PAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate digitDateFrom = digitalDate(dateFrom);
        LocalDate digitDateTo = digitalDate(dateTo);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String dataElement: data) {
                String[] dataFields = dataElement.split(" ");
                if (name.equals(dataFields[NAME_INDEX])) {
                    if ((digitalDate(dataFields[DATE_INDEX]).isAfter(digitDateFrom)
                            && digitalDate(dataFields[DATE_INDEX]).isBefore(digitDateTo))
                            || (digitalDate(dataFields[DATE_INDEX]).equals(digitDateFrom)
                            || digitalDate(dataFields[DATE_INDEX]).equals(digitDateTo))) {
                        salary += Integer.parseInt(dataFields[NUMBER_OF_HOURS_INDEX])
                                * Integer.parseInt(dataFields[HOURLY_PAY_INDEX]);
                    }
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }

    private LocalDate digitalDate(String date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, timeFormatter);
    }
}
