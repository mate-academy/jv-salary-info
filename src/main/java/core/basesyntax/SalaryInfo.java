package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        final LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        final int[] salaries = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String dataElement : data) {
                final String[] tempDataPerDate = dataElement.split(" ");
                salaries[i] += checkDateAndCalculateSalary(startDate, endDate,
                        tempDataPerDate, names[i]);
            }
        }
        return printInfo(names, salaries, dateFrom, dateTo);
    }

    private int checkDateAndCalculateSalary(LocalDate dateFrom, LocalDate dateTo,
                                            String[] data, String currentName) {
        final LocalDate currentDate = LocalDate.parse(data[INDEX_OF_DATE], DATE_FORMAT);
        final String name = data[INDEX_OF_NAME];
        if (currentDate.isBefore(dateTo.plusDays(1)) && currentDate.isAfter(dateFrom.minusDays(1))
                && currentName.equals(name)) {
            final int workingHours = Integer.parseInt(data[INDEX_OF_WORKING_HOURS]);
            final int incomePerHour = Integer.parseInt(data[INDEX_OF_INCOME_PER_HOUR]);
            return workingHours * incomePerHour;
        }
        return 0;
    }

    private String printInfo(String[] names, int[] incomes, String startDate, String endDate) {
        final String hyphenAppender = " - ";
        final StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(startDate)
                .append(hyphenAppender)
                .append(endDate);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(hyphenAppender)
                    .append(incomes[i]);
        }
        return builder.toString();
    }
}
