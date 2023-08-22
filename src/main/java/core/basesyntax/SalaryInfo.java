package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryInfo = new int[names.length];
        for (String stringData : data) {
            String[] dataEmployee = stringData.split(" ");
            if (isDateWithinRange(dataEmployee[0], dateFrom, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(dataEmployee[1])) {
                        salaryInfo[i] += Integer.parseInt(dataEmployee[2])
                                * Integer.parseInt(dataEmployee[3]);
                    }
                }
            }
        }
        return getReport(names, salaryInfo, dateFrom, dateTo);
    }

    private boolean isDateWithinRange(String date, String dateFrom, String dateTo) {
        String datePattern = "dd.MM.yyyy";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(datePattern);
        LocalDate parsedDate = LocalDate.parse(date, dateFormat);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, dateFormat);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, dateFormat);
        return !parsedDate.isAfter(parsedDateTo) && !parsedDate.isBefore(parsedDateFrom);
    }

    private String getReport(String[] name, int[] result, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + SEPARATOR + dateTo + System.lineSeparator());
        for (int i = 0; i < name.length; i++) {
            stringBuilder.append(name[i]).append(SEPARATOR).append(result[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
