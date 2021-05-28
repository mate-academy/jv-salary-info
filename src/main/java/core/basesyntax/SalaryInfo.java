package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TDF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        int salary = 0;
        LocalDate firstDate = LocalDate.parse(dateFrom, TDF);
        LocalDate secondDate = LocalDate.parse(dateTo, TDF);
        String[] splitData;

        for (String name: names) {
            for (int i = 0; i < data.length; i++) {
                splitData = data[i].split(" ");
                if (splitData[1].equals(name)
                        && (firstDate.isBefore(LocalDate.parse(splitData[0], TDF))
                        || firstDate.equals(LocalDate.parse(splitData[0], TDF)))
                        && (secondDate.isAfter(LocalDate.parse(splitData[0], TDF))
                        || secondDate.equals(LocalDate.parse(splitData[0], TDF)))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}
