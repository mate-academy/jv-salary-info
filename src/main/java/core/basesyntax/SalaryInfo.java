package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT_FOR_PERIOD = "Report for period ";
    private static final int DATE_ELEMENT = 0;
    private static final int NAME_ELEMENT = 1;
    private static final int HOURS_ELEMENT = 2;
    private static final int RATE_ELEMENT = 3;
    private static final String FORMAT_LOCAL_DATA = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern(FORMAT_LOCAL_DATA);
    private static final String SEPARATOR_IN_DATA = " ";
    private static final String SEPARATOR_IN_REPORT = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_FOR_PERIOD).append(dateFrom).append(SEPARATOR_IN_REPORT)
                .append(dateTo).append(System.lineSeparator());
        int salary = 0;
        for (String name : names) {
            for (String element : data) {
                String[] splitData = element.split(SEPARATOR_IN_DATA);
                LocalDate parseSplitData = LocalDate.parse(splitData[DATE_ELEMENT], FORMATTER);
                if (name.equals(splitData[NAME_ELEMENT])
                        && (parseSplitData.isAfter(parseDateFrom)
                        || parseSplitData.equals(parseDateFrom))
                        && (parseSplitData.isBefore(parsDateTo)
                        || parseSplitData.equals(parsDateTo))) {
                    int partOfSalary = Integer.parseInt(splitData[HOURS_ELEMENT])
                            * Integer.parseInt(splitData[RATE_ELEMENT]);
                    salary += partOfSalary;
                }
            }
            builder.append(name).append(SEPARATOR_IN_REPORT)
                    .append(salary).append(System.lineSeparator());
            salary = 0;
        }
        return builder.toString().trim();
    }
}
