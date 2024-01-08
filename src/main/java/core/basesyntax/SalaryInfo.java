package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_AMOUNT_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        if ((names != null) && (data != null)) {
            LocalDate dateLocalFrom = LocalDate.parse(dateFrom, PATTERN);
            LocalDate dateLocalTo = LocalDate.parse(dateTo, PATTERN);
            report.append(HEADER).append(dateFrom).append(DELIMITER).append(dateTo);
            int[] salaries = new int[names.length];
            for (int i = 0; i < names.length; i++) {
                for (String line : data) {
                    String[] strings = line.split(SEPARATOR);
                    LocalDate dateReport = LocalDate.parse(strings[DATE_INDEX], PATTERN);
                    if (!dateReport.isBefore(dateLocalFrom) && !dateReport.isAfter(dateLocalTo)) {
                        int indexFromData = Integer.parseInt(strings[SALARY_AMOUNT_INDEX]);
                        int salaryFromData = Integer.parseInt(strings[SALARY_INDEX]);
                        salaries[i] += (names[i].equals(strings[NAME_INDEX]))
                                ? (indexFromData * salaryFromData) : 0;
                    }
                }
                report.append(System.lineSeparator())
                        .append(names[i]).append(DELIMITER).append(salaries[i]);
            }
        }
        return report.toString();
    }
}
