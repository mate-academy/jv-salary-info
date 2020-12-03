package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate begin = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate end = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder salaryInformation = new StringBuilder();
        salaryInformation.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataLine : data) {
                String[] line = dataLine.split(" ");
                if (name.equals(line[1])) {
                    LocalDate date = LocalDate.parse(line[0], DATE_FORMAT);
                    if ((date.isAfter(begin) && date.isBefore(end))
                            || date.isEqual(begin) || date.isEqual(end)) {
                        salary += Integer.parseInt(line[2]) * Integer.parseInt(line[3]);
                    }
                }
            }
            salaryInformation.append("\n").append(name).append(" - ").append(salary);
        }
        return salaryInformation.toString();
    }
}
