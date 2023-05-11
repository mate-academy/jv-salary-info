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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int sum = 0;
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataPerName = data[j].split(WHITESPACE);
                LocalDate formattedDate = LocalDate.parse(dataPerName[0], FORMATTER);
                LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);

                if (dataPerName[1].equals(names[i]) && ((formattedDate.isAfter(formattedDateFrom)
                        && formattedDate.isBefore(formattedDateTo))
                        || (formattedDate.isEqual(formattedDateFrom)
                        || formattedDate.isEqual(formattedDateTo)))) {
                    sum = sum + (Integer.parseInt(dataPerName[2])
                            * Integer.parseInt(dataPerName[3]));
                }
            }
            salary[i] = sum;
            sum = 0;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_TEXT).append(dateFrom).append(DASH)
                .append(dateTo).append(LINE_BREAK);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < salary.length; j++) {
                if (i == j) {
                    builder.append(names[i]).append(DASH)
                            .append(salary[j]).append(LINE_BREAK);
                }
            }
        }
        String toReturn = builder.toString();
        return toReturn.substring(0, toReturn.length() - 1);
    }
}

