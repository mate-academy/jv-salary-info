package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryBuilder = new StringBuilder("Report for period ");
        salaryBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int salary = 0;

            for (String day: data) {
                String[] arrayData = day.split(" ");

                LocalDate currentDate = LocalDate.parse(arrayData[DATE_INDEX], DATE_FORMAT);
                LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMAT);
                LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMAT);

                if (arrayData[NAME_INDEX].equals(name)
                        && (currentDate.isAfter(dateStart) || currentDate.isEqual(dateStart))
                        && (currentDate.isBefore(dateEnd) || currentDate.isEqual(dateEnd))
                ) {
                    salary += Integer.parseInt(arrayData[HOURS_INDEX])
                            * Integer.parseInt(arrayData[SALARY_INDEX]);
                }
            }

            salaryBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return salaryBuilder.toString();
    }
}
