package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_COUNT_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<String> list = new ArrayList<>();
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder info = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        int sum;
        for (String name : names) {
            sum = 0;
            for (String value : data) {
                String[] line = value.split(" ");
                if (line[INDEX_OF_NAME].equals(name)
                        && ((LocalDate.parse(line[INDEX_OF_DATE], FORMATTER).isAfter(from)
                        || from.equals(LocalDate.parse(line[INDEX_OF_DATE], FORMATTER)))
                        && (to.isAfter(LocalDate.parse(line[INDEX_OF_DATE], FORMATTER))
                        || to.equals(LocalDate.parse(line[INDEX_OF_DATE], FORMATTER))))) {
                    sum += Integer.parseInt(line[INDEX_OF_COUNT_HOURS])
                            * Integer.parseInt(line[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return info.toString();
    }
}
