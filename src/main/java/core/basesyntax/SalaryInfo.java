package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
            LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException exc) {
            System.out.print("Invalid date format, correct date format is dd.mm.yyyy");
            throw exc;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        if (names != null || data != null) {

            for (String searchedName : names) {
                int salary = 0;

                for (String tempDate : data) {
                    String[] dividedData = tempDate.split(" ");
                    LocalDate currentDate = LocalDate.parse(dividedData[0], formatter);
                    String currentName = dividedData[1];
                    int currentMultiplier = Integer.parseInt(dividedData[2]);
                    int currentAmount = Integer.parseInt(dividedData[3]);

                    if (searchedName.equals(currentName)
                            && currentDate.compareTo(dateFromFormatted) >= 0
                            && currentDate.compareTo(dateToFormatted) <= 0) {
                        int currentSalary = currentMultiplier * currentAmount;
                        salary = currentSalary + salary;
                    }
                }
                builder.append(System.lineSeparator())
                        .append(searchedName)
                        .append(" - ")
                        .append(salary);
            }
            return builder.toString();
        }
        return null;
    }
}
