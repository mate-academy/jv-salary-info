package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder info = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        int sum;
        for (int i = 0; i < names.length; i++) {
            sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] line = data[j].split(" ");
                if (line[1].equals(names[i])
                        && ((LocalDate.parse(line[0], formatter).isAfter(from)
                        || from.equals(LocalDate.parse(line[0], formatter)))
                        && (to.isAfter(LocalDate.parse(line[0], formatter))
                        || to.equals(LocalDate.parse(line[0], formatter))))) {
                    sum += Integer.parseInt(line[2]) * Integer.parseInt(line[3]);
                }
            }
            info.append(System.lineSeparator()).append(names[i]).append(" - ").append(sum);
        }
        return info.toString();
    }
}
