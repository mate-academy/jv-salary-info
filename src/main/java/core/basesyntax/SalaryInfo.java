package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int RATE_INDEX = 2;
    static final int TIME_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate localDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDate1 = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder();
        int sumSalary;
        LocalDate workDate;
        String name;
        int hours;
        int salaryHour;
        result.append("Report for period ")
                .append(dateFrom);
        result.append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            result.append(names[i])
                    .append(" - ");
            sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] words = data[j].split(" ");
                workDate = LocalDate.parse(words[DATE_INDEX], dateTimeFormatter);
                name = words[NAME_INDEX];
                hours = Integer.parseInt(words[TIME_INDEX]);
                salaryHour = Integer.parseInt(words[RATE_INDEX]);
                if (name.equals(names[i])) {
                    if (workDate.compareTo(localDate1) <= 0 && workDate.compareTo(localDate) >= 0) {
                        sumSalary += salaryHour * hours;
                    }
                }
            }
            result.append(sumSalary);
        }
        return result.toString();
    }
}

