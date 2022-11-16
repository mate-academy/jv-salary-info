package core.basesyntax;

import static java.lang.String.valueOf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 0;
    private static final int SALARY_INDEX = 1;
    private static final int DIVIDER_DATE = 0;
    private static final int DIVIDER_NAME = 1;
    private static final int DIVIDER_HOURS = 2;
    private static final int DIVIDER_INCOME = 3;

    public String getSalaryInfo(String[]names, String[]data, String dateFrom, String dateTo) {
        String[][] resultArray = new String[names.length][2];
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultArray[i][NAME_INDEX] = names[i];
            resultArray[i][SALARY_INDEX] = "0";
            for (int j = 0; j < data.length; j++) {
                String[] divideByWhitespace = data[j].split(" ");
                LocalDate ddate = LocalDate.parse(divideByWhitespace[DIVIDER_DATE], FORMATTER);
                LocalDate ddateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate ddateTo = LocalDate.parse(dateTo, FORMATTER);
                if (((ddate.isAfter(ddateFrom) && ddate.isBefore(ddateTo))
                        || ddate.isEqual(ddateFrom) || ddate.isEqual(ddateTo))
                        && divideByWhitespace[DIVIDER_NAME].equals(resultArray[i][NAME_INDEX])) {
                    resultArray[i][SALARY_INDEX] = valueOf(Integer
                            .parseInt(resultArray[i][SALARY_INDEX])
                            + Integer.parseInt(divideByWhitespace[DIVIDER_HOURS])
                            * Integer.parseInt(divideByWhitespace[DIVIDER_INCOME]));
                }
            }
            resultBuilder.append(System.lineSeparator()).append(resultArray[i][NAME_INDEX])
                    .append(" - ").append(resultArray[i][SALARY_INDEX]);
        }
        return resultBuilder.toString();
    }
}
