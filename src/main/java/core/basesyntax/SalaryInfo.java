package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PAY_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] parseData = data[i].split(" ");
                LocalDate dateData = LocalDate.parse(parseData[DATE_INDEX], FORMATTER);
                String nameData = parseData[NAME_INDEX];
                int hours = Integer.parseInt(parseData[HOURS_INDEX]);
                int payHour = Integer.parseInt(parseData[PAY_HOUR_INDEX]);
                if (nameData.equals(name) && (dateData.isEqual(localDateFrom)
                        || dateData.isAfter(localDateFrom) && (dateData.isEqual(localDateTo)
                        || dateData.isBefore(localDateTo)))) {
                    salary += hours * payHour;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
