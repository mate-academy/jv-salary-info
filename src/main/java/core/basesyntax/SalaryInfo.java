package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int DATE_DAY_WORK_INDEX = 2;
    private static final int SALARY_FOR_TIME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStartWork = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinishWork = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[DATA_INDEX], FORMATTER);
                if (workDay.isAfter(dateStartWork.minusDays(DATE_INDEX))
                        && workDay.isBefore(dateFinishWork.plusDays(DATE_INDEX))
                        && name.equals(splittedLine[DATE_INDEX])) {
                    salary += Integer.parseInt(splittedLine[DATE_DAY_WORK_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_FOR_TIME_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

