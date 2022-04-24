package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStartWork = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinishWork = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name: names) {
            int salary = ZERO;
            for (String line: data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[ZERO], FORMATTER);
                if (workDay.isAfter(dateStartWork.minusDays(ONE))
                        && workDay.isBefore(dateFinishWork.plusDays(ONE))
                        && name.equals(splittedLine[ONE])) {
                    salary += Integer.parseInt(splittedLine[TWO])
                            * Integer.parseInt(splittedLine[THREE]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}

