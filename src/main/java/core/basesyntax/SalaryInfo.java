package core.basesyntax;

import java.time.LocalDate;
import java.util.Formatter;
import java.time.format;
import java.util.Date;
import java.util.Calendar;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate date = LocalDate.parse(input, formatter);
            System.out.printf("%s%n", date);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", input);
            throw exc;
        }
        final LocalDate DAY_USER_WORKS;
        final LocalDate DATE_FROM;
        final LocalDate DATE_TO;
        String currentUser;
        int currentUserSumSalary;
        StringBuilder output = new StringBuilder();

        return output.toString();
    }
}
