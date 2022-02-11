package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);
        StringBuilder sb = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] employeeByDates = element.split(" ");
                LocalDate date = parseDate(employeeByDates[0]);
                if ((date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                        && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                    if (name.equals(employeeByDates[1])) {
                        salary += Integer.parseInt(employeeByDates[2])
                                * Integer.parseInt(employeeByDates[3]);
                    }
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return sb.toString();
    }

    private LocalDate parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable! Please use \"dd.MM.yyyy\" format. %n", input);
            throw e;
        }
    }
}
