package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate datefrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateto = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder result = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String dates : data) {
                String[] parsedData = dates.split(" ");
                LocalDate dateFromData = LocalDate.parse(parsedData[DATE_INDEX], DATE_FORMATTER);
                if (parsedData[NAME_INDEX].equals(name)
                        && ((dateFromData.isAfter(datefrom) || dateFromData.isEqual(datefrom))
                        && ((dateFromData.isBefore(dateto)) || dateFromData.isEqual(dateto)))) {
                    salary += Integer.parseInt(parsedData[HOURS_INDEX])
                            * Integer.parseInt(parsedData[PER_HOUR_INDEX]);
                }
            }

            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + result;
    }
}
