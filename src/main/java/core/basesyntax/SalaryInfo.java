package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int RATE = 3;
    private static final int HOURS = 2;
    private static final int NAME = 1;
    private static final int DATE = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0, salary = 0; i < names.length; i++, salary = 0) {
            for (int num = 0; num < data.length; num++) {
                if (names[i].equals(data[num].split(" ")[NAME])
                        && LocalDate.parse(data[num].split(" ")[DATE], DATE_FORMAT)
                        .isAfter(LocalDate.parse(dateFrom, DATE_FORMAT))
                        && (LocalDate.parse(data[num].split(" ")[DATE], DATE_FORMAT))
                        .isBefore((LocalDate.parse(dateTo, DATE_FORMAT)).plusDays(1))) {
                    salary += Integer.parseInt(data[num].split(" ")[HOURS])
                            * Integer.parseInt(data[num].split(" ")[RATE]);
                }
            }
            builder.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
