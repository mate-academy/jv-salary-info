package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOURS_POSITION = 2;
    public static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalInfo = new StringBuilder(
                String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());

        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);
        int[] salaryInfo = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] splittedLine = data[i].split(" ");
            for (int j = 0; j < names.length; j++) {
                salaryInfo[j] += parseLine(splittedLine, names[j], from, to);
            }
        }
        for (int i = 0; i < names.length; i++) {
            finalInfo.append(names[i]).append(" - ").append(salaryInfo[i])
                    .append(System.lineSeparator());
        }

        return finalInfo.toString().trim();
    }

    private int parseLine(String[] splittedLine, String name,
                          LocalDate dateFrom, LocalDate dateTo) {
        if (splittedLine[NAME_POSITION].trim().equals(name)) {
            LocalDate date = LocalDate.parse(splittedLine[DATE_POSITION], DATE_FORMAT);
            if (date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0) {
                int hours = Integer.parseInt(splittedLine[HOURS_POSITION]);
                int hourCost = Integer.parseInt(splittedLine[SALARY_POSITION]);
                return hours * hourCost;
            }
        }
        return 0;
    }
}
