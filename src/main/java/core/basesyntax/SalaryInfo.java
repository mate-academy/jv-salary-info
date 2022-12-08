package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX_IN_DATA = 0;
    private static final int NAME_INDEX_IN_DATA = 1;
    private static final int HOURS_INDEX_IN_DATA = 2;
    private static final int SALARY_INDEX_IN_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            LocalDate dateFromData;
            int salary = 0;
            for (String datum : data) {
                String[] dataLine = datum.split(" ");
                dateFromData = LocalDate.parse(dataLine[DATE_INDEX_IN_DATA], FORMATTER);
                if (dataLine[NAME_INDEX_IN_DATA].equals(name)
                        && (dateFromData.isAfter(firstDate) || dateFromData.equals(firstDate))
                        && (dateFromData.isBefore(lastDate) || dateFromData.equals(lastDate))) {
                    salary += Integer.parseInt(dataLine[HOURS_INDEX_IN_DATA])
                            * Integer.parseInt(dataLine[SALARY_INDEX_IN_DATA]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
