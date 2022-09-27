package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        List<String> nameList = List.of(names);
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);
        int[] salaries = new int[names.length];
        for (String record : data) {
            String[] recordParts = record.split("\\s+");
            LocalDate recordDate = parseDate(recordParts[DATE_INDEX]);
            if (recordDate.isBefore(startDate) || recordDate.isAfter(endDate)) {
                continue;
            }
            String name = recordParts[NAME_INDEX];
            int hours = Integer.parseInt(recordParts[HOURS_INDEX]);
            int incomePerHour = Integer.parseInt(recordParts[INCOME_INDEX]);
            int income = hours * incomePerHour;
            int nameIndex = nameList.indexOf(name);
            if (nameIndex != -1) {
                salaries[nameIndex] += income;
            }
        }
        for (int i = 0; i < nameList.size(); i++) {
            result.append(System.lineSeparator())
                    .append(nameList.get(i))
                    .append(" - ")
                    .append(salaries[i]);
        }
        return result.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }
}
