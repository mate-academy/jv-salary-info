package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryBuilder = new StringBuilder("Report for period ");
        salaryBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int salary = 0;

            for (String day: data) {
                String[] arrayData = day.split(" ");

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate currentDate = LocalDate.parse(arrayData[0], dateFormat);
                LocalDate dateStart = LocalDate.parse(dateFrom, dateFormat);
                LocalDate dateEnd = LocalDate.parse(dateTo, dateFormat);

                if (day.contains(name)
                        && (currentDate.isAfter(dateStart) || currentDate.isEqual(dateStart))
                        && (currentDate.isBefore(dateEnd) || currentDate.isEqual(dateEnd))
                ) {
                    salary += Integer.parseInt(arrayData[2]) * Integer.parseInt(arrayData[3]);
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
