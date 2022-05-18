package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();

        for (String i : names) {
            int employeeSalarySum = 0;
            for (String j : data) {
                String[] splitData = j.split(" ");
                LocalDate particularDate = LocalDate.parse(splitData[0], formatter);
                LocalDate convertedDateFrom = LocalDate.parse(dateFrom, formatter);
                LocalDate convertedDateTO = LocalDate.parse(dateTo, formatter);
                int compareDateFrom = particularDate.compareTo(convertedDateFrom);
                int compareDateTo = particularDate.compareTo(convertedDateTO);
                if (splitData[1].equals(i) && (compareDateFrom >= 0 && compareDateTo <= 0)) {
                    employeeSalarySum += Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(i)
                    .append(" - ").append(employeeSalarySum);
        }
        return "Report for period " + dateFrom + " - " + dateTo + reportBuilder;
    }
}
