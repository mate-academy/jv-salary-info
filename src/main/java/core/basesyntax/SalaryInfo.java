package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;
        for (String name : names) {
            for (String dataLine : data) {
                String[] splitterData = dataLine.split(" ");
                LocalDate checkDate = LocalDate.parse(splitterData[DATE_INDEX], DATE_FORMATTER);
                if (name.equals(splitterData[NAME_INDEX])
                        && (checkDate.isAfter(dateStart) || checkDate.equals(dateStart))
                        && (checkDate.isBefore(dateEnd) || checkDate.equals(dateEnd))) {
                    salary += Integer.parseInt(splitterData[HOURS_INDEX])
                            * Integer.parseInt(splitterData[HOUR_SALARY_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return reportBuilder.toString();
    }
}
