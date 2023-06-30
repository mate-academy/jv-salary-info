package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] lineInfo;
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                lineInfo = data[j].split(" ");
                LocalDate date = LocalDate.parse(lineInfo[DATE_INDEX], FORMATTER);
                if (names[i].equals(lineInfo[NAME_INDEX]) && (date.isAfter(from)
                        && date.isBefore(to) || date.isEqual(to))) {
                    salary += Integer.parseInt(lineInfo[WORKING_HOURS_INDEX])
                            * Integer.parseInt(lineInfo[INCOME_PER_HOUR_INDEX]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
