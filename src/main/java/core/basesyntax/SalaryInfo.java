package core.basesyntax;

import static java.lang.Integer.parseInt;
import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_TIME_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate firstDay = parse(dateFrom, FORMATTER);
        LocalDate lastDay = parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate day = parse(splittedLine[DAY_INDEX], FORMATTER);
                if (name.equals(splittedLine[NAME_INDEX]) && ((day.isAfter(firstDay)
                        || day.equals(firstDay))) && (day.isBefore(lastDay)
                        || day.equals(lastDay))) {
                    salary += parseInt(splittedLine[WORK_TIME_INDEX])
                            * parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
