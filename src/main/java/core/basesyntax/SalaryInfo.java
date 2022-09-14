package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Integer HOURS_OF_WORK_INDEX = 2;
    private static final Integer SALARY_PER_HOUR_INDEX = 3;
    private static final Integer DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startCounting = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endCounting = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int sumForName = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                if (line.contains(name)) {
                    LocalDate date = LocalDate
                            .parse(splittedLine[DATE_INDEX], FORMATTER);
                    int sum = Integer.parseInt(splittedLine[HOURS_OF_WORK_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                    if ((date.isAfter(startCounting) || date.isEqual(startCounting))
                            && (date.isBefore(endCounting) || date.isEqual(endCounting))) {
                        sumForName += sum;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(sumForName);
        }
        return stringBuilder.toString();
    }
}
