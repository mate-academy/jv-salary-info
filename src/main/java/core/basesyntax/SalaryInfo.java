package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null) {
            throw new SalaryDataException("Error! Input parameters are wrong (empty)");
        }

        int[] salarySum = new int[names.length];
        LocalDate localDateFrom;
        LocalDate localDateTo;

        try {
            localDateFrom = parseDate(dateFrom);
            localDateTo = parseDate(dateTo);
        } catch (DateTimeParseException e) {
            throw new SalaryDataException("Error! Input parameters are wrong. " + e);
        }

        for (String line : data) {
            SalaryPerDay salaryPerDay;
            try {
                salaryPerDay = new SalaryPerDay(line);
            } catch (DateTimeParseException | NumberFormatException | SalaryPerDayException e) {
                throw new SalaryDataException(
                        String.format("Error! Input parameters (%s) are wrong. %s", line, e));
            }

            if (salaryPerDay.getDate().isBefore(localDateFrom)
                    || salaryPerDay.getDate().isAfter(localDateTo)) {
                continue;
            }

            for (int i = 0; i < names.length; i++) {
                if (salaryPerDay.getName().equals(names[i])) {
                    salarySum[i] += salaryPerDay.getCost();
                    break;
                }
            }
        }
        return getInfo(localDateFrom, localDateTo, names, salarySum);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    private String getInfo(LocalDate dateFrom, LocalDate dateTo, String[] names, int[] salarySum) {
        StringBuilder salaryInfo = new StringBuilder();

        salaryInfo.append(String.format("Report for period %s - %s",
                dateFormatter.format(dateFrom), dateFormatter.format(dateTo)));

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(String.format("%s%s - %d",
                    System.lineSeparator(), names[i], salarySum[i]));
        }

        return salaryInfo.toString();
    }
}
