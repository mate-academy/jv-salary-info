package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final String REPORT_TEXT = "Report for period ";
    private static final String DASH = " - ";
    private static final String LINE_BREAK = "\n";
    private static final String WHITESPACE = " ";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int sum = 0;
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataPerName = data[j].split(WHITESPACE);
                LocalDate formattedDate = LocalDate.parse(dataPerName[INDEX_ZERO], FORMATTER);
                LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);

                if (dataPerName[INDEX_ONE].equals(names[i])
                        && ((formattedDate.isAfter(formattedDateFrom)
                        && formattedDate.isBefore(formattedDateTo))
                        || (formattedDate.isEqual(formattedDateFrom)
                        || formattedDate.isEqual(formattedDateTo)))) {
                    sum = sum + (Integer.parseInt(dataPerName[INDEX_TWO])
                            * Integer.parseInt(dataPerName[INDEX_THREE]));
                }
            }
            salary[i] = sum;
            sum = 0;
        }
        StringBuilder reportForm = new StringBuilder();
        reportForm.append(REPORT_TEXT).append(dateFrom).append(DASH)
                .append(dateTo).append(LINE_BREAK);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < salary.length; j++) {
                if (i == j) {
                    reportForm.append(names[i]).append(DASH)
                            .append(salary[j]).append(LINE_BREAK);
                }
            }
        }
        String report = reportForm.toString();

        return report.substring(0, report.length() - 1);
    }
}

