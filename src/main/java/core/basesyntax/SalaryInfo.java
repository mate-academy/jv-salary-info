package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int INDEX_DATUM = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_PER_HOUR = 2;
    private static final int HOURS_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ");
        salaryInfo.append(dateFrom).append(" - ").append(dateTo);
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] split = datum.split(" ");
                LocalDate date = LocalDate.parse(split[INDEX_DATUM], FORMATTER);
                if (name.equals(split[INDEX_NAME])) {
                    if ((date.isAfter(localFrom) || date.isEqual(localFrom))
                            && (date.isBefore(localTo) || date.isEqual(localTo))) {
                        salary += Integer.parseInt(split[HOURS_INDEX])
                                * Integer.parseInt(split[INDEX_PER_HOUR]);
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}
