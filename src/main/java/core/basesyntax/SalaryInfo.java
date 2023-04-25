package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SalaryInfo {

    record DataRow(LocalDate date, String name, int hours, int rate) {
    }

    public static final DateTimeFormatter DDMMYYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static DataRow parse(String str) {
        String[] cols = str.split("\\s+");
        return new DataRow(
                LocalDate.parse(cols[0], DDMMYYYY),
                cols[1],
                Integer.parseInt(cols[2]),
                Integer.parseInt(cols[3])
        );
    }

    public static boolean between(LocalDate d, LocalDate from, LocalDate to) {
        return (d.isAfter(from) || d.isEqual(from)) && (d.isBefore(to) || d.isEqual(to));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DDMMYYYY);
        LocalDate to = LocalDate.parse(dateTo, DDMMYYYY);

        Set<String> set = Set.of(names);

        Map<String, Integer> salaryMap = Arrays.stream(data)
                .map(SalaryInfo::parse)
                .filter(row -> set.contains(row.name()) && between(row.date(), from, to))
                .collect(Collectors.groupingBy(
                        DataRow::name,
                        Collectors.summingInt(row -> row.hours() * row.rate())
                ));

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append("\r\n");
        Arrays.stream(names)
                .forEach(name -> sb
                        .append(name)
                        .append(" - ")
                        .append(salaryMap.containsKey(name)
                                ? String.valueOf(salaryMap.get(name))
                                : "0"
                        )
                        .append("\r\n")
                );
        return sb.toString().strip();
    }
}

