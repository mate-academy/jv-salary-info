package core.basesyntax;

import static java.lang.Integer.parseInt;
import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate firstDay = parse(dateFrom, FORMATTER);
        LocalDate lastDay = parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                String dayOfData = splittedLine[0];
                String nameOfData = splittedLine[1];
                String workTime = splittedLine[2];
                String salaryPerHour = splittedLine[3];
                LocalDate day = parse(dayOfData, FORMATTER);
                if (name.equals(nameOfData) && ((day.isAfter(firstDay) || day.equals(firstDay)))
                        && (day.isBefore(lastDay) || day.equals(lastDay))) {
                    salary += parseInt(workTime) * parseInt(salaryPerHour);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
