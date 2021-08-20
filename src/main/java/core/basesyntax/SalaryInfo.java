package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX3 = 3;
    private static final int INDEX2 = 2;
    private static final int INDEX1 = 1;
    private static final int INDEX0 = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;
        for (int name = 0; name < names.length; name++) {
            for (int i = 0; i < data.length; i++) {
                if (names[name].equals(data[i].split(" ")[INDEX1])
                        && LocalDate.parse(data[i].split(" ")[INDEX0], DATE_FORMAT)
                        .isAfter(LocalDate.parse(dateFrom, DATE_FORMAT))
                        && (LocalDate.parse(data[i].split(" ")[INDEX0], DATE_FORMAT))
                        .isBefore((LocalDate.parse(dateTo, DATE_FORMAT)).plusDays(1))) {
                    salary += Integer.parseInt(data[i].split(" ")[INDEX2])
                            * Integer.parseInt(data[i].split(" ")[INDEX3]);
                }
            }
            builder.append("\n").append(names[name]).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
