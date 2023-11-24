package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int COEFFICIENT_INDEX = 2;
    private static final int VALUE_INDEX = 3;
    private static final String HYPHEN_AND_SPACES = " - ";
    private static final String REPORT_START_TEXT = "Report for period ";
    private static final String SPACE_REGEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder(REPORT_START_TEXT)
                .append(dateFrom).append(HYPHEN_AND_SPACES)
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            result.append(getPersonalSalary(name, data, localFrom, localTo));
        }
        return result.toString().trim();
    }

    private String getPersonalSalary(
            String name, String[] data, LocalDate fromDate, LocalDate toDate) {
        int sum = 0;
        for (String dataUnit : data) {
            String[] dataSeparated = dataUnit.split(SPACE_REGEX);
            LocalDate dataDate = LocalDate.parse(dataSeparated[DATE_INDEX], FORMATTER);
            if (dataDate.isAfter(fromDate)
                    && dataDate.isBefore(toDate)
                    || dataDate.isEqual(fromDate)
                    || dataDate.isEqual(toDate)) {
                if (dataSeparated[NAME_INDEX].equals(name)) {
                    sum += Integer.parseInt(dataSeparated[COEFFICIENT_INDEX])
                            * Integer.parseInt(dataSeparated[VALUE_INDEX]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        return builder.append(name)
                .append(HYPHEN_AND_SPACES)
                .append(sum)
                .append(System.lineSeparator())
                .toString();
    }
}
