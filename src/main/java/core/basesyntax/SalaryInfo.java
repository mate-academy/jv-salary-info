package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_DATE = 0;
    private static final int INDEX_FOR_NAME = 1;
    private static final int INDEX_FOR_COUNT_OF_HOURS = 2;
    private static final int INDEX_FOR_AMOUNT_OF_MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate finishDate;
        LocalDate dateOfWorkingDay;

        String separator = System.lineSeparator();
        StringBuilder reportResult = new StringBuilder();
        reportResult.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(separator);

        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] splitData = element.split(" ");
                startDate = LocalDate.parse(dateFrom, FORMATTER);
                finishDate = LocalDate.parse(dateTo, FORMATTER);
                dateOfWorkingDay = LocalDate.parse(splitData[INDEX_FOR_DATE], FORMATTER);
                if (name.equals(splitData[INDEX_FOR_NAME])
                        && dateOfWorkingDay.isAfter(startDate.minusDays(1))
                        && dateOfWorkingDay.isBefore(finishDate.plusDays(1))) {
                    salary += Integer.parseInt(splitData[INDEX_FOR_COUNT_OF_HOURS])
                            * Integer.parseInt(splitData[INDEX_FOR_AMOUNT_OF_MONEY]);
                }
            }

            reportResult.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(separator);
        }

        return reportResult.toString().trim();
    }
}
