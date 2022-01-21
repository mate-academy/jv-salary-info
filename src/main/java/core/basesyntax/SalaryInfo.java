package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataForDay = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(dataForDay[0], FORMATTER);
                if ((currentDate.isAfter(dateBegin) && (currentDate.isBefore(dateEnd))
                        || currentDate.isEqual(dateEnd)) && name.equals(dataForDay[1])) {
                    salary += Integer.parseInt(dataForDay[2]) * Integer.parseInt(dataForDay[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
