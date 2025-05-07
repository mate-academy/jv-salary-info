package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = getLocalDate(dateFrom);
        LocalDate dateFinish = getLocalDate(dateTo);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;
        for (String name : names) {
            for (String row : data) {
                String[] values = row.split(" ");
                String selectedDate = values[0];
                String selectedName = values[1];
                String selectedHours = values[2];
                String selectedIncomePerHour = values[3];
                if (name.equals(selectedName)) {
                    LocalDate dateWork = getLocalDate(selectedDate);
                    if (dateWork.isAfter(dateStart.minusDays(1))
                            && dateWork.isBefore(dateFinish.plusDays(1))) {
                        salary += Integer.parseInt(selectedHours)
                                * Integer.parseInt(selectedIncomePerHour);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }

    private LocalDate getLocalDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", date);
            throw exc;
        }
        return localDate;
    }
}
