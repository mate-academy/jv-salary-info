package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int RATE_INDEX = 2;
    private static final int TIME_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom);
        result.append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            result.append(names[i])
                    .append(" - ");
            LocalDate localDate = LocalDate.parse(dateFrom, dateTimeFormatter);
            LocalDate localDate1 = LocalDate.parse(dateTo, dateTimeFormatter);
            int sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] words = data[j].split(" ");
                LocalDate workDate = LocalDate.parse(words[DATE_INDEX], dateTimeFormatter);
                String name = words[NAME_INDEX];
                if (name.equals(names[i])) {
                    if (workDate.compareTo(localDate1) <= 0 && workDate.compareTo(localDate) >= 0) {
                        sumSalary += Integer.parseInt(words[RATE_INDEX])
                                * Integer.parseInt(words[TIME_INDEX]);
                    }
                }
            }
            result.append(sumSalary);
        }
        return result.toString();
    }
}
