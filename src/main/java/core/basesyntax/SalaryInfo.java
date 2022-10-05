package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DataValidator dataValidator = new DataValidator();
        try {
            dataValidator.validate(names, data, dateFrom, dateTo);
        } catch (DataValidationException e) {
            return String.valueOf(e);
        }

        LocalDate localDateFrom;
        LocalDate localDateTo;
        try {
            localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
            localDateTo = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Format of date is invalid (should be dd.MM.yyyy):"
                    + LINE_SEPARATOR + "Date from: " + dateFrom + " "
                    + LINE_SEPARATOR + "Date to: " + dateTo);
        }
        int dataArrayLength = data.length;
        LocalDate[] datesArray = new LocalDate[dataArrayLength];
        String[] namesArray = new String[dataArrayLength];
        Integer[] hoursArray = new Integer[dataArrayLength];
        Integer[] incomeArray = new Integer[dataArrayLength];

        int arrayIndex = 0;

        for (String datum : data) {
            String[] splitData = datum.split(" ");
            if (arrayIndex < dataArrayLength) {
                try {
                    datesArray[arrayIndex] = LocalDate.parse(splitData[0], FORMATTER);
                    namesArray[arrayIndex] = splitData[1];
                    hoursArray[arrayIndex] = Integer.parseInt(splitData[2]);
                    incomeArray[arrayIndex] = Integer.parseInt(splitData[3]);
                    arrayIndex++;
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("Format of date is invalid "
                            + "(should be dd.MM.yyyy):" + LINE_SEPARATOR + datum);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Format of working hours and income "
                            + "per hour is invalid (should be number):"
                            + LINE_SEPARATOR + datum);
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period "
                + FORMATTER.format(localDateFrom) + " - " + FORMATTER.format(localDateTo));
        int namesArrayLength = names.length;
        int[] salary = new int[namesArrayLength];
        for (int i = 0; i < namesArrayLength; i++) {
            for (int j = 0; j < dataArrayLength; j++) {
                if (names[i].equals(namesArray[j]) && datesArray[j].compareTo(localDateFrom) >= 0
                        && datesArray[j].compareTo(localDateTo) <= 0) {
                    salary[i] += hoursArray[j] * incomeArray[j];
                }
            }
            builder.append(LINE_SEPARATOR).append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }
}
