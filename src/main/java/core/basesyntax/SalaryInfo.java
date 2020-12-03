package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int k = 0; k < data.length; k++) {
                String[] dataPats = data[k].split(" ");
                LocalDate localDate = LocalDate.parse(dataPats[0], DATE_FORMAT);
                if (dataPats[1].equals(names[i]) && (localDate.equals(startDate)
                        || localDate.equals(endDate) || localDate.isAfter(startDate)
                        && localDate.isBefore(endDate))) {
                    salary += Integer.parseInt(dataPats[2]) * Integer.parseInt(dataPats[3]);
                }
            }
            result.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return result.toString();
    }
}
