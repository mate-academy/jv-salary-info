package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_PER_HOUR = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate newDateFrom = parseDate(dateFrom);
        LocalDate newDateTo = parseDate(dateTo);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempArray = data[j].split(" ");
                LocalDate employeeDate = parseDate(tempArray[DATE_INDEX]);
                if (names[i].equals(tempArray[NAME_INDEX])) {
                    if (!employeeDate.isBefore(newDateFrom)
                            && !employeeDate.isAfter(newDateTo)) {
                        salary += Integer.parseInt(tempArray[HOURS_INDEX])
                                * Integer.parseInt(tempArray[SALARY_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }

    private LocalDate parseDate(String dateFrom) {
        return LocalDate.parse(dateFrom, FORMATTER);
    }
}
