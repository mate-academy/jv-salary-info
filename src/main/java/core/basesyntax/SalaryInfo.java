package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class SalaryInfo {
    private static final String GIVEN_DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(GIVEN_DATE_FORMAT, Locale.ENGLISH);
    private static final String DATA_ELEMENTS_SPLITERATOR = " ";
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_OF_WORK_POSITION = 2;
    private static final int INCOME_PER_HOUR_POSITION = 3;

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        LocalDate localDateFrom = parseDateFromData(dateFrom);
        LocalDate localDateTo = parseDateFromData(dateTo);
        if (names.length > 0) {
            int[] incomes = new int[names.length];
            for (String dataElement : data) {
                String[] temporarilyDividedData = dataElement.split(DATA_ELEMENTS_SPLITERATOR);
                LocalDate dateFromData =
                        parseDateFromData(temporarilyDividedData[DATE_POSITION]);
                String nameFromDataPosition = temporarilyDividedData[NAME_POSITION];
                int hoursOfWork = parseIntegerFromData(temporarilyDividedData,
                        HOURS_OF_WORK_POSITION);
                int incomePerHourPosition = parseIntegerFromData(temporarilyDividedData,
                        INCOME_PER_HOUR_POSITION);
                int incomeFromData = hoursOfWork * incomePerHourPosition;
                if (isDateSufficient(dateFromData, localDateFrom, localDateTo)) {
                    int index = indexOfValue(names, nameFromDataPosition);
                    if (index >= 0) {
                        incomes[index] += incomeFromData;
                    }
                }
            }
            return generateReport(names, incomes, dateFrom, dateTo);
        } else {
            throw new EmptyNamesListException("Names list length should have been at least 1"
                    + " to generate a report but was " + names.length);
        }
    }

    private static LocalDate parseDateFromData(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException dtpe) {
            throw new RuntimeException("Couldn't parse parameter: " + date);
        }
    }

    private static int parseIntegerFromData(String[] temporarilyDividedData, int position) {
        try {
            return Integer.parseInt(temporarilyDividedData[position]);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(
                    "Couldn't parse integer for " + temporarilyDividedData[position]);
        }
    }

    private static boolean isDateSufficient(LocalDate dateFromData, LocalDate localDateFrom,
                                            LocalDate localDateTo) {
        return (dateFromData.isAfter(localDateFrom)
                || dateFromData.isEqual(localDateFrom))
                && (dateFromData.isBefore(localDateTo)
                || dateFromData.isEqual(localDateTo));
    }

    private static int indexOfValue(String[] names, String targetName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }

    private static String generateReport(String[] names, int[] incomes, String dateFrom,
                                         String dateTo) {
        StringBuilder report = new StringBuilder();
        builder.append(HEADER).append(dateFrom).append(SEPARATOR).append(dateTo)
                .append(System.lineSeparator());
        int incomesLoopCounter = 0;
        for (String name : names) {
            builder.append(name).append(" - ").append(incomes[incomesLoopCounter++])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
