package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String[] entrySplit = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(entrySplit[DATA_INDEX], FORMATTER);
                String entryName = entrySplit[NAME_INDEX];

                if (entryName.equals(name)) {
                    if (!(entryDate.isBefore(localDateFrom) || entryDate.isAfter(localDateTo))) {

                        int hoursInData = Integer.parseInt(entrySplit[HOURS_INDEX]);
                        int salaryPerHour = Integer.parseInt(entrySplit[SALARY_PER_HOUR_INDEX]);

                        salary += hoursInData * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
