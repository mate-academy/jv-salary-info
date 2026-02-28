package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int MULTIPLIER_INDEX = 2;
    public static final int MONEY_INDEX = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        int[] salaries = new int[names.length];
        List<String> namesList = Arrays.asList(names);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        for (String entry : data) {
            String[] line = entry.split("\\s+");
            String name = line[NAME_INDEX];
            LocalDate date = LocalDate.parse(line[DATE_INDEX], FORMATTER);

            if (!namesList.contains(name) || date.isBefore(from) || date.isAfter(to)) {
                continue;
            }

            salaries[namesList.indexOf(name)] +=
                    Integer.parseInt(line[MULTIPLIER_INDEX])
                            * Integer.parseInt(line[MONEY_INDEX]);
        }

        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            sb.append(SEPARATOR).append(names[i]).append(" - ").append(salaries[i]);
        }

        return sb.toString();
    }
}
