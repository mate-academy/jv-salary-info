package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate firstDate = LocalDate.parse(dateFrom, dmy);
        LocalDate secondDate = LocalDate.parse(dateTo, dmy);
        int[] totalSalary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String record : data) {
                String[] buffer = record.split(" ");
                if (names[i].equals(buffer[1])) {
                    LocalDate currentDate = LocalDate.parse(buffer[0], dmy);
                    if (currentDate.isAfter(firstDate)
                            && currentDate.isBefore(secondDate)
                            || currentDate.isEqual(firstDate)
                            || currentDate.isEqual(secondDate)) {
                        totalSalary[i] += Integer.parseInt(buffer[2]) * Integer.parseInt(buffer[3]);
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                      .append(firstDate.format(dmy))
                      .append(" - ")
                      .append(secondDate.format(dmy));
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append("\n")
                         .append(names[i])
                         .append(" - ")
                         .append(totalSalary[i]);
        }
        return stringBuilder.toString();
    }
}
