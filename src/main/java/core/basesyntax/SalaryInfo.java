package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String HYPHEN_SEPARATOR = " - ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String DATA_SPLITTER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] salaries = new int[names.length];
        StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom).append(HYPHEN_SEPARATOR).append(dateTo).append(LINE_SEPARATOR);
        for (int i = 0; i < names.length; i++) {
            for (String dataLine : data) {
                String[] dataArray = dataLine.split(DATA_SPLITTER);
                LocalDate date = LocalDate.parse(dataArray[DATE_INDEX], DATE_FORMATTER);
                String name = dataArray[NAME_INDEX];
                int hours = Integer.parseInt(dataArray[HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(dataArray[INCOME_INDEX]);
                if (name.equals((names[i]))
                        && (dateDateFrom.isBefore(date) && dateDateTo.isAfter(date)
                        || dateDateFrom.isEqual(date) || dateDateTo.isEqual(date))) {
                    salaries[i] += hours * incomePerHour;
                }
            }
            output.append(names[i]).append(HYPHEN_SEPARATOR)
                    .append(salaries[i]).append(LINE_SEPARATOR);
        }
        output.delete(output.indexOf(names[names.length - 1]) + names[names.length - 1].length()
                + Integer.toString(salaries[salaries.length - 1]).length() + 3, output.length());
        return output.toString();
    }
}
