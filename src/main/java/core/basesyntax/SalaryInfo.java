package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);
        Set<String> set = Set.of(names);
        Map<String, Integer> salaryMap = Arrays.stream(data)
                .map(this::parse)
                .filter(row -> set.contains(row.name) && isDateInPeriod(row.date, from, to))
                .collect(Collectors.groupingBy(
                        DataRow::name,
                        Collectors.summingInt(row -> row.hours * row.rate)
                ));
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        Arrays.stream(names)
                .forEach(name -> sb
                        .append(name)
                        .append(" - ").append(salaryMap
                                .getOrDefault(name, 0)
                        )
                        .append(System.lineSeparator())
                );
        return sb.toString().strip();
    }

    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private DataRow parse(String line) {
        String[] cols = line.split("\\s+");
        int DATE_INDEX = 0;
        int NAME_INDEX = 1;
        int HOURS_INDEX = 2;
        int PERHOUR_INDEX = 3;
        return new DataRow(
                LocalDate.parse(cols[DATE_INDEX], DATE_FORMAT),
                cols[NAME_INDEX],
                Integer.parseInt(cols[HOURS_INDEX]),
                Integer.parseInt(cols[PERHOUR_INDEX])
        );
    }

    private boolean isDateInPeriod(LocalDate date, LocalDate from, LocalDate to) {
        return (date.isAfter(from) || date.isEqual(from))
                && (date.isBefore(to) || date.isEqual(to));
    }

    private static final class DataRow {
        private final LocalDate date;
        private final String name;
        private final int hours;
        private final int rate;

        DataRow(LocalDate date, String name, int hours, int rate) {
            this.date = date;
            this.name = name;
            this.hours = hours;
            this.rate = rate;
        }

        public String name() {
            return name;
        }
    }
}

