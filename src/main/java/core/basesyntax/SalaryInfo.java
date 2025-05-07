package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormated = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate dateToFormated = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataSeparate = datum.split(" ");
                LocalDate dateFromData = LocalDate.parse(dataSeparate[DATE_INDEX], FORMATTER);
                if (name.equals(dataSeparate[NAME_INDEX])
                        && (dateFromData.isBefore(dateToFormated)
                        || dateFromData.isEqual(dateToFormated))
                        && (dateFromData.isAfter(dateFromFormated)
                        || dateFromData.isEqual(dateFromFormated))) {
                    salary += Integer.parseInt(dataSeparate[WORKING_HOURS_INDEX])
                            * Integer.parseInt(dataSeparate[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
