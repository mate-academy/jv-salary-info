package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final DateUtils util = new DateUtils();

    private int getNameIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate from = util.convertString(dateFrom);
        LocalDate to = util.convertString(dateTo);
        int[] salaries = new int[names.length];

        builder.append("Report for period ")
                .append(from.format(util.getFormatter()))
                .append(" - ")
                .append(to.format(util.getFormatter()))
                .append(System.lineSeparator());

        for (String line : data) {
            Entry entry = new Entry(line);

            if (!entry.getDate().isBefore(from)
                    && !entry.getDate().isAfter(to)) {

                int index = getNameIndex(names, entry.getName());

                if (index != -1) {
                    salaries[index] += entry.getSalary() * entry.getHours();
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);

            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }

        return builder.toString();
    }
}
