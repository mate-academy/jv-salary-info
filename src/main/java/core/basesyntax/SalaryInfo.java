package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter tdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ");
        stringBuilder.append(dateFrom);
        stringBuilder.append(" - ");
        stringBuilder.append(dateTo);
        stringBuilder.append("\n");

        int salary = 0;
        LocalDate firstDate = LocalDate.parse(dateFrom, tdf);
        LocalDate secondDate = LocalDate.parse(dateTo, tdf);
        String[] splitData;

        for (String name: names) {
            for (int i = 0; i < data.length; i++) {
                splitData = data[i].split(" ");
                if (splitData[1].equals(name)
                        && (firstDate.isBefore(LocalDate.parse(splitData[0], tdf))
                        || firstDate.equals(LocalDate.parse(splitData[0], tdf)))
                        && (secondDate.isAfter(LocalDate.parse(splitData[0], tdf))
                        || secondDate.equals(LocalDate.parse(splitData[0], tdf)))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            stringBuilder.append(salary);
            stringBuilder.append("\n");
            salary = 0;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
