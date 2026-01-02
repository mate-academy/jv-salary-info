package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int i0 = 0;
    private static final int in = 1;
    private static final int ih = 2;
    private static final int is = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryPerPerson = new int[names.length];

        LocalDate df = LocalDate.parse(dateFrom, formatter);
        LocalDate dt = LocalDate.parse(dateTo, formatter);

        for (String day : data) {
            String line = day.trim();
            String[] res = line.split("\s+");
            LocalDate date = LocalDate.parse(res[i0], formatter);
            if ((date.isEqual(df) || date.isAfter(df)) && (date.isEqual(dt) || date.isBefore(dt))) {
                for (int i = 0; i < names.length; i++) {
                    if (res[in].equals(names[i])) {
                        salaryPerPerson[i] += Integer.parseInt(res[ih]) * Integer.parseInt(res[is]);
                        break;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                stringBuilder.append(names[i])
                        .append(" - ")
                        .append(salaryPerPerson[i]);
                break;
            }
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salaryPerPerson[i])
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

}
