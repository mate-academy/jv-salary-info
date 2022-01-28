package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate DATE_FROM;
        LocalDate DATE_TO;
        LocalDate DAY_USER_WORKS;
        String pattern = "dd.MM.yyyy";
        StringBuilder output = new StringBuilder();

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(pattern);
            DATE_FROM = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
           throw e;
        }

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(pattern);
            DATE_TO = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }

        output.append("Report for period ");
        output.append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            String currentUser = names[i];
            int currentUserSumSalary = 0;

            for (int k = 0; k < data.length; k++) {
                String[] arrayOfEmployees = data[k].split(" ");
                try {
                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern(pattern);
                    DAY_USER_WORKS = LocalDate.parse(arrayOfEmployees[0], formatter);
                } catch (DateTimeParseException e) {
                    throw e;
                }

                if ((DATE_FROM.isBefore(DAY_USER_WORKS) && (DATE_TO.isAfter(DAY_USER_WORKS))
                        && currentUser.equals(arrayOfEmployees[1]))
                        || (DATE_FROM.isEqual(DAY_USER_WORKS) && currentUser.equals(arrayOfEmployees[1]))
                        || (DATE_TO.isEqual(DAY_USER_WORKS) && currentUser.equals(arrayOfEmployees[1]))) {
                    currentUserSumSalary += (Integer.parseInt(arrayOfEmployees[2])
                            * Integer.parseInt(arrayOfEmployees[3]));
                }
            }
            output.append(currentUser)
                    .append(" - ")
                    .append(currentUserSumSalary);
                    if (i < names.length - 1) {
                    output.append(System.lineSeparator());
                    }
        }

        return output.toString();
    }
}
