package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final String lineSeparator = System.lineSeparator();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        StringBuilder reportBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            reportBuilder.append(lineSeparator)
                    .append(name)
                    .append(" - ");
            int salary = 0;
            for (String datum : data) {
                String[] datumSplit = datum.split(" ");
                if (datumSplit[NAME_INDEX].equals(name)) {
                    LocalDate date = LocalDate.parse(datumSplit[DATE_INDEX], dateFormatter);
                    int hours = Integer.parseInt(datumSplit[HOURS_INDEX]);
                    int perHour = Integer.parseInt(datumSplit[PER_HOUR_INDEX]);
                    int salaryThatDate = hours * perHour;
                    if (date.isAfter(fromDate) && date.isBefore(toDate)
                            || date.isEqual(fromDate) || date.isEqual(toDate)) {
                        salary += salaryThatDate;
                    }
                }
            }
            reportBuilder.append(salary);
        }
        return reportBuilder.toString();
    }
}
