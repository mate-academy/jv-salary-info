package core.basesyntax;

import static java.lang.String.valueOf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 0;
    private static final int SALARY_INDEX = 1;

    public String getSalaryInfo(String[]names, String[]data, String dateFrom, String dateTo) {
        String[][] resultArray = new String[names.length][2];
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultArray[i][NAME_INDEX] = names[i];
            resultArray[i][SALARY_INDEX] = "0";
            for (int j = 0; j < data.length; j++) {
                String[] divideByWhitespace = data[j].split(" ");
                LocalDate ddate = LocalDate.parse(divideByWhitespace[0], FORMATTER);
                LocalDate ddateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate ddateTo = LocalDate.parse(dateTo, FORMATTER);
                if (((ddate.isAfter(ddateFrom) && ddate.isBefore(ddateTo))
                        || ddate.isEqual(ddateFrom) || ddate.isEqual(ddateTo))
                        && divideByWhitespace[1].equals(resultArray[i][NAME_INDEX])) {
                    resultArray[i][SALARY_INDEX] = valueOf(Integer
                            .parseInt(resultArray[i][SALARY_INDEX])
                            + Integer.parseInt(divideByWhitespace[2])
                            * Integer.parseInt(divideByWhitespace[3]));
                }
            }
            resultBuilder.append(System.lineSeparator()).append(resultArray[i][NAME_INDEX])
                    .append(" - ").append(resultArray[i][1]);
        }
        return resultBuilder.toString();
    }
}
