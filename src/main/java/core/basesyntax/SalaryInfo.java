package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String TITLE_LINE = "Report for period";
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_OUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(TITLE_LINE)
                .append(" " + dateFrom + " - " + dateTo)
                .append(getData(names, data, dateFrom, dateTo)).toString();
    }

    private String getData(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        int salary;

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] valuesFromData = data[j].split(" ");
                if (getLocalDate(valuesFromData[DATA_INDEX]).isAfter(getLocalDate(dateFrom))
                        && (getLocalDate(valuesFromData[DATA_INDEX]).isBefore(getLocalDate(dateTo))
                        || getLocalDate(valuesFromData[DATA_INDEX]).isEqual(getLocalDate(dateTo)))
                        && valuesFromData[NAME_INDEX].equals(names[i])) {
                    salary += Integer.parseInt(valuesFromData[WORKING_OUR_INDEX])
                            * Integer.parseInt(valuesFromData[INCOME_INDEX]);
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private LocalDate getLocalDate(String dataInString) {
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(dataInString, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(dataInString + " is not parsable", e);
        }
        return date;
    }
}
