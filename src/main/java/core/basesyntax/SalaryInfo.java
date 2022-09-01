package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int SALARY_INDEX = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        result.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (final String name : names) {
            result.append(System.lineSeparator());
            int salary = 0;
            for (final String line : data) {
                String[] datumSplit = line.split(" ");
                LocalDate current = LocalDate.parse(datumSplit[DATE_INDEX], formatter);
                if (name.equals(datumSplit[NAME_INDEX])) {
                    if ((current.isAfter(from) || current.isEqual(from))
                            && (current.isBefore(to) || current.isEqual(to))) {
                        salary += Integer.parseInt(datumSplit[HOURS_INDEX])
                                * Integer.parseInt(datumSplit[SALARY_INDEX]);
                    }
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }
}
