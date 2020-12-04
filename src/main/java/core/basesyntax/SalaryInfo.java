package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate end = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder wageInformation = new StringBuilder();
        wageInformation.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int wage = 0;
            for (String dataInformation : data) {
                String[] information = dataInformation.split(" ");
                LocalDate date = LocalDate.parse(information[0], DATE_FORMAT);
                if (name.equals(information[1]) && ((date.isAfter(start) && date.isBefore(end))
                        || date.isEqual(end) || date.isEqual(start))) {
                    wage += Integer.parseInt(information[2]) * Integer.parseInt(information[3]);
                }
            }
            wageInformation.append("\n").append(name).append(" - ").append(wage);
        }
        return wageInformation.toString();
    }
}
