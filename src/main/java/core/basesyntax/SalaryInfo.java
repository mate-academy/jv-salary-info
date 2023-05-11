package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        String[] rowSplitedData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                rowSplitedData = line.split(" ");
                LocalDate date = LocalDate.parse(rowSplitedData[DATE_POSITION], FORMATTER);
                if (name.equals(rowSplitedData[NAME_POSITION])) {
                    if (date.isEqual(localDateFrom) || date.isAfter(localDateFrom)
                            && (date.isEqual(localDateTo) || date.isBefore(localDateTo))) {
                        salary += (Integer.parseInt(rowSplitedData[HOURS_POSITION])
                                * Integer.parseInt(rowSplitedData[SALARY_POSITION]));
                    }
                }
            }
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
