package core.basesyntax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Input arrays and dates must not be null.");
        }

        LocalDate dateFrom1 = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate dateTo1 = LocalDate.parse(dateTo.trim(), FORMATTER);
        StringBuilder result = new StringBuilder();
        long[] totals = new long[names.length];

        for (String row : data) {
            String[] parts = row.split("\s+");
            if (parts.length < 4) {
                continue;
            }
            String employee = parts[1];
            int idx = indexOfName(names, employee);
            int hours = Integer.parseInt(parts[2]);
            int pay = Integer.parseInt(parts[3]);
            LocalDate workedDay = LocalDate.parse(parts[0], FORMATTER);

            if ((workedDay.isAfter(dateFrom1) || workedDay.isEqual(dateFrom1))
                    && (workedDay.isEqual(dateTo1) || workedDay.isBefore(dateTo1))) {
                totals[idx] += (long) hours * (long) pay;
                }
            }

        result.append("Report for period ")
                .append(FORMATTER.format(dateFrom1))
                .append(" - ")
                .append(FORMATTER.format(dateTo1));

        for (int i = 0; i < names.length; i++) {
                    result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(totals[i]);
            }
    return result.toString();
    }
    private int indexOfName(String[] names, String target) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}




