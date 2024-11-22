package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        if (names == null || data == null || dateFrom == null
                || dateTo == null) {
            throw new DataGettingException("One or more input arguments are null");
        }

        LocalDate newDateFrom = parseDate(dateFrom);
        LocalDate newDateTo = parseDate(dateTo);

        if (newDateFrom.isAfter(newDateTo)) {
            throw new DataGettingException("Start date cannot be later than end date");
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] lineInArray = line.split(" ");
                LocalDate dateFromArray = parseDate(lineInArray[0]);

                if (lineInArray[1].equals(name)
                        && (dateFromArray.isAfter(newDateFrom) && dateFromArray.isBefore(newDateTo)
                        || dateFromArray.isEqual(newDateFrom) && dateFromArray.isBefore(newDateTo)
                        || dateFromArray.isAfter(newDateFrom)
                        && dateFromArray.isEqual(newDateTo))) {
                    salary += Integer.parseInt(lineInArray[2]) * Integer.parseInt(lineInArray[3]);
                }
            }

            if (result.indexOf(name) != -1) {
                int indexOfName = result.indexOf(name);
                int numberStart = result.indexOf(" - ", indexOfName) + 3;
                int numberEnd = result.indexOf("\n", numberStart);

                if (numberEnd == -1) {
                    numberEnd = result.length();
                }

                int oldSalary = Integer.parseInt(result.substring(numberStart, numberEnd).trim());
                int newSalary = oldSalary + salary;

                result.replace(numberStart, numberEnd, String.valueOf(newSalary));
            } else {
                result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            }
        }
        return result.toString();
    }

    public LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (Exception e) {
            throw new DataGettingException("Invalid date format. Excepted format: dd.MM.yyyy");
        }
    }
}
