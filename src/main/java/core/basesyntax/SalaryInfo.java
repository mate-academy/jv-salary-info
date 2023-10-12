package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DDMMYYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THERE = 3;

    private static boolean between(LocalDate dayForAnalysis, LocalDate from, LocalDate to) {
        return (dayForAnalysis.isAfter(from) || dayForAnalysis.isEqual(from)) && (dayForAnalysis.isBefore(to) || dayForAnalysis.isEqual(to));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] salary = new int[names.length];
        LocalDate from = LocalDate.parse(dateFrom, DDMMYYYY);
        LocalDate to = LocalDate.parse(dateTo, DDMMYYYY);
        for (int i = 0; i < data.length; i++) {
            String[] pices = data[i].split(SEPARATOR);
            LocalDate dayForAnalysis = LocalDate.parse(pices[ZERO], DDMMYYYY);
            if ((between(dayForAnalysis, from, to))) {
                for (int j = 0; j < names.length; j++) {
                    if (pices[ONE].equals(names[j])) {
                        salary[j] += parseInt(pices[TWO]) * parseInt(pices[THERE]);
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder(HEADER);
        builder.append(dateFrom)
                .append(DELIMITER)
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i]).append(DELIMITER)
                    .append(salary[i]);
        }
        return builder.toString();
    }
}
