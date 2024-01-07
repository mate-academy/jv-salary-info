package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static int NEGATIVE = -1;
    private static int POSITIVE = 1;
    private static final String HEADER = "Report for period ";
    private  static final String DELIMITER = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        if ((names != null) && (data != null)) {
            LocalDate dateLocalFrom = LocalDate.parse(dateFrom, PATTERN);
            LocalDate dateLocalTo = LocalDate.parse(dateTo, PATTERN);
            int[] salaries = new int[names.length];
            for (String line : data) {
                String[] strings = line.split(SEPARATOR);
                for (int j = 0; j < names.length; j++) {
                    if (dateLocalFrom.compareTo(LocalDate.parse(strings[0], PATTERN)) < POSITIVE
                            && dateLocalTo.compareTo(LocalDate.parse(strings[0], PATTERN)) > NEGATIVE) {
                        if (names[j].equals(strings[1])) {
                            salaries[j] += Integer.parseInt(strings[2])
                                    * Integer.parseInt(strings[3]);
                        }
                    }
                }
            }
            report.append(HEADER).append(dateFrom).append(DELIMITER).append(dateTo);
            for (int i = 0; i < names.length; i++) {
                report.append(System.lineSeparator()).append(names[i]).append(DELIMITER).append(salaries[i]);
            }
        }
        return report.toString();
    }
}
