package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final int ONE_DAY = 1;
    private static final String SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = parse(dateFrom, formatter);
        LocalDate dateEnd = parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ").append(dateStart.format(formatter))
                .append(SEPARATOR).append(dateEnd.format(formatter));

        for (String name : names) {
            int currentSalary = 0;
            for (String currentString : data) {
                String[] separetadString = currentString.split(" ");
                LocalDate currentDate = parse(separetadString[DATE_INDEX], formatter);
                if (name.equals(separetadString[NAME_INDEX])
                        && currentDate.isAfter(dateStart.minusDays(ONE_DAY))
                        && currentDate.isBefore(dateEnd.plusDays(ONE_DAY))) {
                    currentSalary += Integer.parseInt(separetadString[HOURS_INDEX])
                            * Integer.parseInt(separetadString[SALARY_PER_HOUR_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name).append(SEPARATOR).append(currentSalary);
        }
        return builder.toString();
    }
}
