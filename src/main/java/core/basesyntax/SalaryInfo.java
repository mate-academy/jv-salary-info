package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        LocalDate workDate;

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int earnedMoney = 0;
            for (String element : data) {
                String[] dataLine = element.split(" ");
                startDate = LocalDate.parse(dateFrom, FORMATTER);
                endDate = LocalDate.parse(dateTo, FORMATTER);
                workDate = LocalDate.parse(dataLine[DATE_INDEX], FORMATTER);
                if (name.equals(dataLine[NAME_INDEX])
                        && workDate.isAfter(startDate.minusDays(1))
                        && workDate.isBefore(endDate.plusDays(1))) {
                    earnedMoney += Integer.parseInt(dataLine[WORKING_HOURS_INDEX])
                            * Integer.parseInt(dataLine[SALARY_PER_HOUR_INDEX]);
                }
            }
            report.append(name).append(" - ")
                    .append(earnedMoney).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
