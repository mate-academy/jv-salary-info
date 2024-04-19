package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String DASH = " - ";
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INCOME_INDEX = 3;
    private static final int HOURS = 2;
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        HashMap<String, Integer> salary = new HashMap<>();
        for (String entry : data) {
            String[] datas = entry.split(SPACE);
            LocalDate entryDate = LocalDate.parse(datas[DATA_INDEX], DATE_TIME_FORMATTER);
            String entryNames = datas[NAME_INDEX];
            int hours = Integer.parseInt(datas[HOURS]);
            int incomePerHour = Integer.parseInt(datas[INCOME_INDEX]);
            if (!entryDate.isBefore(localDateFrom) && !entryDate.isAfter(localDateTo)) {
                int currentSalary = salary.getOrDefault(entryNames, 0);
                int fullSalary = currentSalary + (hours * incomePerHour);
                salary.put(entryNames, fullSalary);
            }
        }

        StringBuilder str = new StringBuilder();
        str.append("Report for period ")
                .append(dateFrom).append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int value = salary.getOrDefault(name, 0);
            str.append(name).append(DASH).append(value).append(System.lineSeparator());
        }
        return str.substring(0, str.length() - System.lineSeparator().length());
    }
}
