package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataLine = datum.split(" ");
                LocalDate dateFromData = LocalDate.parse(dataLine[DATE_INDEX], FORMATTER);
                if (dataLine[NAME_INDEX].equals(name)
                        && (dateFromData.isAfter(firstDate) || dateFromData.equals(firstDate))
                        && (dateFromData.isBefore(lastDate) || dateFromData.equals(lastDate))) {
                    salary += Integer.parseInt(dataLine[HOURS_INDEX])
                            * Integer.parseInt(dataLine[SALARY_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
