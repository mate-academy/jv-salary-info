package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        LocalDate secondDate = LocalDate.parse(dateTo, formatter);
        for (String name: names) {
            int salary = 0;
            for (String dataLine: data) {
                String[] elementOfData = dataLine.split(" ");
                LocalDate date = LocalDate.parse(elementOfData[0], formatter);
                if ((date.isAfter(firstDate) || date.equals(firstDate))
                        && (date.isBefore(secondDate) || date.equals(secondDate))
                        && elementOfData[1].equals(name)) {
                    salary += Integer.parseInt(elementOfData[2])
                            * Integer.parseInt(elementOfData[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
