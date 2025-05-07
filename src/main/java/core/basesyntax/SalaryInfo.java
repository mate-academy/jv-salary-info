package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int START_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate formatterDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formatterDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String dataValue : data) {
                String[] splitData = dataValue.split(" ");
                LocalDate current = LocalDate.parse(splitData[START_DATE_INDEX], FORMATTER);
                if (current.isAfter(formatterDateFrom)
                        && !current.isAfter(formatterDateTo)
                        && name.equals(splitData[NAME_INDEX])) {
                    salary += Integer.parseInt(splitData[HOURS_OF_WORK_INDEX])
                            * Integer.parseInt(splitData[SALARY_PER_HOUR_INDEX]);

                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
