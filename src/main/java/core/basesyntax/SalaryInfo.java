package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFormatFrom = parse(dateFrom, dateTimeFormatter);
        LocalDate dateFormatTo = parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String n : data) {
                String[] s = n.split(" ");
                LocalDate day = parse(s[DAY_INDEX], dateTimeFormatter);
                if (s[NAME_INDEX].equals(name)) {
                    if ((day.isAfter(dateFormatFrom)
                            || day.isEqual(dateFormatFrom))
                            && (day.isBefore(dateFormatTo)
                            || day.isEqual(dateFormatTo))) {
                        totalSalary += Integer.parseInt(s[WORKING_HOURS])
                                * Integer.parseInt(s[SALARY_PER_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
            totalSalary = 0;

        }
        return builder.toString();
    }
}
