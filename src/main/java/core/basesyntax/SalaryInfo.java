package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    final static int FIRST_ELEMENT_OF_ARRAY = 0;
    final static int SECOND_ELEMENT_OF_ARRAY = 1;
    final static int THIRD_ELEMENT_OF_ARRAY = 2;
    final static int FOURTH_ELEMENT_OF_ARRAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeSalaryOfPeriod = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        dateBegin = dateBegin.minusDays(1);
        dateEnd = dateEnd.plusDays(1);
        for (String name : names) {
            int totalSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] temp = data[i].split(" ");
                if (LocalDate.parse(temp[FIRST_ELEMENT_OF_ARRAY], FORMATTER).isAfter(dateBegin)
                        && LocalDate.parse(temp[FIRST_ELEMENT_OF_ARRAY], FORMATTER).isBefore(dateEnd)
                        && name.equals(temp[SECOND_ELEMENT_OF_ARRAY])) {
                    int hours = Integer.parseInt(temp[THIRD_ELEMENT_OF_ARRAY]);
                    int hourSalary = Integer.parseInt(temp[FOURTH_ELEMENT_OF_ARRAY]);
                    totalSalary = totalSalary + hourSalary * hours;
                }
            }
            employeeSalaryOfPeriod
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return employeeSalaryOfPeriod.toString();
    }
}
