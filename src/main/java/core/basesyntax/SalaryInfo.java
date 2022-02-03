package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate localDateFrom;
        final LocalDate localDateTo;
        LocalDate employeeWorks;

        StringBuilder output = new StringBuilder();

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(PATTERN);
            localDateFrom = LocalDate.parse(dateFrom, formatter);
            localDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Parsing date error!", e);
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
                            DateTimeFormatter.ofPattern(PATTERN);
                    employeeWorks = LocalDate.parse(arrayOfEmployees[0], formatter);
                } catch (DateTimeParseException e) {
                    throw e;
                }

                if ((localDateFrom.isBefore(employeeWorks) && (localDateTo.isAfter(employeeWorks))
                        && currentUser.equals(arrayOfEmployees[1]))
                        || (localDateFrom.isEqual(employeeWorks)
                        && currentUser.equals(arrayOfEmployees[1]))
                        || (localDateTo.isEqual(employeeWorks)
                        && currentUser.equals(arrayOfEmployees[1]))) {
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
