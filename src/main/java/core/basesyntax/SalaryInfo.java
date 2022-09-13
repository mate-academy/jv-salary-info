package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final Integer HOURS_OF_WORK_INDEX = 2;
    private static final Integer SALARY_PER_HOUR_INDEX = 3;
    private static final Integer POSITION_OF_DATE_IN_STRING = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startCounting = LocalDate.parse(dateFrom, formatter1);
        LocalDate endCounting = LocalDate.parse(dateTo, formatter1);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int sumForName = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                if (line.contains(name)) {
                    LocalDate date = LocalDate
                            .parse(splittedLine[POSITION_OF_DATE_IN_STRING], formatter1);
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
