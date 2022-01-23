package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return calculate(names, data, dateFrom, dateTo);
    }

    public LocalDate parseData(String input) {
        final String pattern_data = "dd.MM.yyyy";
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(pattern_data);
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", input);
            throw exc;
        }
    }

    public String calculate(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder calculation = new StringBuilder();
        calculation.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String inputNames : names) {
            int finalSalary = 0;
            calculation.append(System.lineSeparator()).append(inputNames).append(" - ");
            for (int d = 0; d < data.length; d++) {
                String[] period = data[d].split(" ");
                LocalDate today = parseData(period[0]);
                if (inputNames.equals(period[1]) && today.isAfter(parseData(dateFrom))
                        && today.isBefore(parseData(dateTo).plusDays(1))) {
                    finalSalary = finalSalary
                            + (Integer.parseInt(period[2]) * Integer.parseInt(period[3]));
                }
            }
            calculation.append(finalSalary);
        }

        return calculation.toString();
    }
}


