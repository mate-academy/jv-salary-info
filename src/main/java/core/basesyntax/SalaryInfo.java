package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final String WORDS_SEPARATOR = " - ";
    private static final String SYSTEM_SEPARATOR = System.lineSeparator();
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        int[] salaryCount = new int[names.length];
        for (String datum : data) {
            String[] dataAboutEmployee = datum.split(" ");
            if (dataValidation(dataAboutEmployee[0], dateFrom, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(dataAboutEmployee[1])) {
                        salaryCount[i] += Integer.parseInt(dataAboutEmployee[2])
                                * Integer.parseInt(dataAboutEmployee[3]);
                    }
                }
            }
        }
        return getReport(names, salaryCount, dateFrom, dateTo);
    }

    private boolean dataValidation(String date, String dateFrom, String dateTo) {
        try {
            if (DATE_FORMAT.parse(date).compareTo(DATE_FORMAT.parse(dateFrom)) >= 0
                             && DATE_FORMAT.parse(date).compareTo(DATE_FORMAT.parse(dateTo)) <= 0) {
                return true;
            }
        } catch (ParseException e) {
            throw new RuntimeException("Invalid input date format", e);
        }
        return false;
    }

    private String getReport(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + WORDS_SEPARATOR + dateTo + SYSTEM_SEPARATOR);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(WORDS_SEPARATOR).append(salary[i])
                    .append(SYSTEM_SEPARATOR);
        }
        return stringBuilder.toString().trim().replaceAll("\\n+$", "");
    }
}
