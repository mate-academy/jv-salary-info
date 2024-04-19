package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String dash = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFrom1 = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateTo1 = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        HashMap<String, Integer> salary = new HashMap<>();
        for (String entry : data) {
            String[] datas = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(datas[0], DATE_TIME_FORMATTER);
            String entryNames = datas[1];
            int hours = Integer.parseInt(datas[2]);
            int taksa = Integer.parseInt(datas[3]);
            if (!entryDate.isBefore(dateFrom1) && !entryDate.isAfter(dateTo1)) {
                int currentSalary = salary.getOrDefault(entryNames, 0);
                int fullSalary = currentSalary + (hours * taksa);
                salary.put(entryNames, fullSalary);
            }
        }

        StringBuilder str = new StringBuilder();
        str.append("Report for period ")
                .append(dateFrom).append(dash)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int value = salary.getOrDefault(name, 0);
            str.append(name).append(dash).append(value).append(System.lineSeparator());
        }
        return str.substring(0, str.length() - System.lineSeparator().length());
    }
}
