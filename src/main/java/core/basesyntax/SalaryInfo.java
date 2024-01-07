package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder strB = new StringBuilder();
        if ((names != null) && (data != null)) {
            LocalDate dateLocalFrom = LocalDate.parse(dateFrom, PATTERN);
            LocalDate dateLocalTo = LocalDate.parse(dateTo, PATTERN);
            int[] salaries = new int[names.length];
            for (String line : data) {
                String[] strings = line.split(" ");
                for (int j = 0; j < names.length; j++) {
                    if (dateLocalFrom.compareTo(LocalDate.parse(strings[0], PATTERN)) < 1
                            && dateLocalTo.compareTo(LocalDate.parse(strings[0], PATTERN)) > -1) {
                        if (names[j].equals(strings[1])) {
                            salaries[j] += Integer.parseInt(strings[2])
                                    * Integer.parseInt(strings[3]);
                        }
                    }
                }

            }
            strB.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
            for (int i = 0; i < names.length; i++) {
                strB.append("\r\n").append(names[i]).append(" - ").append(salaries[i]);
            }
        }
        return strB.toString();
    }
}
