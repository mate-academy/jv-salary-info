package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        int salaryCalculator;

        for (String name : names) {
            salaryCalculator = 0;
            result.append(System.lineSeparator()).append(name)
                    .append(" - ");

            for (String employeeData : data) {
                String[] spllitedDate = employeeData.split("\\s+");

                try {
                    LocalDate startDate = stringToLocalDate(dateFrom);
                    LocalDate endDate = stringToLocalDate(dateTo);
                    LocalDate date = stringToLocalDate(spllitedDate[0]);

                    if (name.equals(spllitedDate[1])
                            && !date.isAfter(endDate)
                            && !date.isBefore(startDate)) {
                        salaryCalculator += Integer.parseInt(spllitedDate[2])
                                * Integer.parseInt(spllitedDate[3]);
                    }
                } catch (ParseException e) {
                    System.out.println("Date parsing exception");
                }
            }
            result.append(salaryCalculator);
        }
        return result.toString();
    }

    public LocalDate stringToLocalDate(String string) throws ParseException {
        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }
}
