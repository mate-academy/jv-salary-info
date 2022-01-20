package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        DateTimeFormatter formatter;
        formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());

        LocalDate fromDay = LocalDate.parse(dateFrom, formatter);
        LocalDate toDay = LocalDate.parse(dateTo, formatter);

        for (int j = 0; j < names.length; j++) {
            int totalSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] info = data[i].split(" ");
                LocalDate givenDay = LocalDate.parse(info[0], formatter);
                if (names[j].equals(info[1])
                        && (givenDay.isAfter(fromDay) || givenDay.isEqual(fromDay))
                        && (givenDay.isBefore(toDay) || givenDay.isEqual(toDay))) {
                    totalSalary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }
            }
            if (j == names.length - 1) {
                builder.append(names[j]).append(" - ").append(totalSalary);
            } else {
                builder.append(names[j]).append(" - ").append(totalSalary)
                        .append(System.lineSeparator());
            }
        }
        String result = builder.toString();
        return result;
    }
}
