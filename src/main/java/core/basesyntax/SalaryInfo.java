package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStartWork = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinishWork = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[DATE_INDEX], FORMATTER);
                if (workDay.isAfter(dateStartWork.minusDays(1))
                        && workDay.isBefore(dateFinishWork.plusDays(1))
                        && name.equals(splittedLine[NAME_INDEX])) {
                    salary += Integer.parseInt(splittedLine[HOURS_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

