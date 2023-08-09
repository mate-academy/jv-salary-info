package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = stringToLocalDate(dateFrom);
        LocalDate localDateTo = stringToLocalDate(dateTo);
        StringBuilder output = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String personData : data) {
                if (personData.contains(name)) {
                    salary += calculateIncome(personData, localDateFrom, localDateTo);
                }
            }
            output.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return output.toString();
    }

    public int calculateIncome(String personData, LocalDate localDateFrom, LocalDate localDateTo) {
        String[] personDataArray = personData.split(" ");
        LocalDate currentLocalDate = stringToLocalDate(personDataArray[0]);
        return (currentLocalDate.compareTo(localDateFrom) > -1)
                && (currentLocalDate.compareTo(localDateTo) < 1)
                ? Integer.parseInt(personDataArray[2]) * Integer.parseInt(personDataArray[3])
                : 0;
    }

    public LocalDate stringToLocalDate(String input) {
        LocalDate date;
        try {
            date = LocalDate.parse(input, FORMATTER);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", input);
            throw exc;
        }
        return date;
    }
}
