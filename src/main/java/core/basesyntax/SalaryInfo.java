package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String SPLIT = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();

        for (String i : names) {
            int employeeSalarySum = DATE_INDEX;
            LocalDate convertedDateFrom = LocalDate.parse(dateFrom, formatter);
            LocalDate convertedDateTO = LocalDate.parse(dateTo, formatter);
            for (String j : data) {
                String[] splitData = j.split(SPLIT);
                LocalDate particularDate = LocalDate.parse(splitData[DATE_INDEX], formatter);
                int compareDateFrom = particularDate.compareTo(convertedDateFrom);
                int compareDateTo = particularDate.compareTo(convertedDateTO);
                if (splitData[NAME_INDEX].equals(i) && (compareDateFrom >= DATE_INDEX
                        && compareDateTo <= DATE_INDEX)) {
                    employeeSalarySum += Integer.parseInt(splitData[WORKING_HOUR_INDEX])
                            * Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(i)
                    .append(" - ").append(employeeSalarySum);
        }
        return "Report for period " + dateFrom + " - " + dateTo + reportBuilder;
    }
}
