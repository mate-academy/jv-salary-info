package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateFinal = LocalDate.parse(dateTo, DATE_FORMAT);

        for (String name : names) {
            int salary = 0;
            for (String dataParts : data) {
                String[] dataArrayParts = dataParts.split(" ");
                LocalDate day = LocalDate.parse(dataArrayParts[0], DATE_FORMAT);
                if (dataArrayParts[1].equals(name) & (day.equals(dateStart)
                        || day.isEqual(dateFinal) || day.isAfter(dateStart)
                        & day.isBefore(dateFinal))) {
                    salary += Integer.parseInt(dataArrayParts[2])
                            * Integer.parseInt(dataArrayParts[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
