package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_COUNT_INDEX = 2;
    private static final int MONEY_AMOUNT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate finishDate;
        LocalDate workingDateDay;
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] splitData = element.split(" ");
                startDate = LocalDate.parse(dateFrom, FORMATTER);
                finishDate = LocalDate.parse(dateTo, FORMATTER);
                workingDateDay = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                if (name.equals(splitData[NAME_INDEX])
                        && workingDateDay.isAfter(startDate)
                        && workingDateDay.isBefore(finishDate.plusDays(1))) {
                    salary += Integer.parseInt(splitData[HOURS_COUNT_INDEX])
                            * Integer.parseInt(splitData[MONEY_AMOUNT_INDEX]);
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
