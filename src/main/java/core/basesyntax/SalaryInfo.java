package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final char CHAR_SPACE = ' ';
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int INDEX_START_DATE = 0;
    private static final int INDEX_END_DATE = 10;
    private static LocalDate currentDay;
    private static String currentName;
    private static int indexEndName;
    private static LocalDate date;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeParseException, NumberFormatException {
        final LocalDate fromDay = getDate(dateFrom);
        final LocalDate toDay = getDate(dateTo);
        int[] moneyEarnedTotal = new int[names.length];

        for (String currentString : data) {
            currentDay = getDate(currentString.substring(INDEX_START_DATE,INDEX_END_DATE));

            if (currentDay.isEqual(fromDay)
                    || currentDay.isEqual(toDay)
                    || (currentDay.isAfter(fromDay)
                    && currentDay.isBefore(toDay))) {
                currentName = getName(currentString);

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(currentName)) {
                        int moneyEarnedDay = getMoneyEarnedDay(currentString);
                        moneyEarnedTotal[i] = moneyEarnedTotal[i] + moneyEarnedDay;
                    }
                }
            }
        }
        return showSalaryInfo(dateFrom, dateTo, names, moneyEarnedTotal);
    }

    private LocalDate getDate(String data) throws DateTimeParseException {
        try {
            date = LocalDate.parse(data, formatter);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", data);
            throw exc;
        }
        return date;
    }

    private String getName(String dataString) {
        indexEndName = dataString.indexOf(CHAR_SPACE, INDEX_END_DATE + 1);
        return dataString.substring(INDEX_END_DATE + 1, indexEndName);
    }

    private int getMoneyEarnedDay(String dataString) throws NumberFormatException {
        int workingHours = 0;
        int incomePerHour = 0;
        int indexEndHours = dataString.indexOf(CHAR_SPACE, indexEndName + 1);

        try {
            workingHours = Integer.parseInt(dataString.substring(indexEndName + 1, indexEndHours));
            incomePerHour = Integer.parseInt(dataString.substring(indexEndHours + 1));
        } catch (NumberFormatException e) {
            System.out.printf("Wrong format: %s%n", dataString);
        }
        return workingHours * incomePerHour;
    }

    private String showSalaryInfo(String dateFrom, String dateTo, String[] names, int[] money) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(LINE_SEPARATOR).append(names[i]).append(" - ").append(money[i]);
        }
        return report.toString();
    }
}
