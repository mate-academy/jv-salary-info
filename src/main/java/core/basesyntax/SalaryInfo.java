package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_INDEX = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                if (currentDate.compareTo(localDateFrom) >= 0
                        && currentDate.compareTo(localDateTo) <= 0
                        && names[i].equals(splitData[NAME_INDEX])) {
                    salary += Integer.parseInt(splitData[HOURS_INDEX])
                            * Integer.parseInt(splitData[SALARY_INDEX]);
                }
            }
            builder.append('\n').append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
