package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeCalculator {
    public static final String DATA_FORMAT = "dd.MM.yyyy";
    private static final String COLUMN_SEPARATOR = "\\s";
    private static final byte USER_DATE_INDEX = 0;
    private static final byte USER_NAME_INDEX = 1;
    private static final byte USER_DAILY_HOURS_INDEX = 2;
    private static final byte USER_DAILY_INCOME_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATA_FORMAT);

    public IncomePerUser calculate(
            String[] names,
            String[] dataRows,
            String dateFrom,
            String dateTo
    ) {
        IncomePerUser incomesPerUser = new IncomePerUser(names);
        LocalDate startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateTimeFormatter);

        for (String dataRow : dataRows) {
            String[] dataColumns = dataRow.split(COLUMN_SEPARATOR);

            LocalDate workingDay = LocalDate.parse(dataColumns[USER_DATE_INDEX], dateTimeFormatter);

            if (isInRange(startDate, endDate, workingDay)) {
                int dailyHours = Integer.parseInt(dataColumns[USER_DAILY_HOURS_INDEX]);
                int dailyIncome = Integer.parseInt(dataColumns[USER_DAILY_INCOME_INDEX]);

                incomesPerUser.increment(dataColumns[USER_NAME_INDEX], dailyHours * dailyIncome);
            }
        }

        return incomesPerUser;
    }

    private boolean isInRange(LocalDate start, LocalDate end, LocalDate date) {
        return (date.isEqual(start) || date.isAfter(start))
                && (date.isBefore(end) || date.isEqual(end));
    }
}
