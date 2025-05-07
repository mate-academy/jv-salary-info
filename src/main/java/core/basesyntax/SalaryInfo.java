package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String SPLITERATOR = " ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            DATE_FORMAT);
    private static final String DELIMITER = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salarySum = new int[names.length];
        StringBuilder result = new StringBuilder(
                "Report for period " + dateFrom + DELIMITER + dateTo);

        LocalDate parseDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataInfo = data[j].split(SPLITERATOR);
                LocalDate dataDate = LocalDate.parse(dataInfo[DATE_INDEX], DATE_FORMATTER);
                if (dateBetweenDates(dataDate, parseDateFrom, parseDateTo)
                        && (dataInfo[NAME_INDEX].equals(names[i]))) {
                    salarySum[i] += (Integer.parseInt(dataInfo[HOURS_INDEX])
                            * Integer.parseInt(dataInfo[INCOME_INDEX]));
                }
            }
            result.append("\n" + names[i] + DELIMITER + salarySum[i]);
        }
        return result.toString();
    }

    private boolean dateBetweenDates(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return ((date.isAfter(dateFrom) || date.isEqual(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo)));
    }
}
