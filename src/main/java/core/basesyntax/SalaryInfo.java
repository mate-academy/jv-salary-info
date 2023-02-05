package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATA_ARRAY = 0;
    public static final int NAME_ARRAY = 1;
    public static final int HOURS_ARRAY = 2;
    public static final int COST_ARRAY = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        String separator = System.lineSeparator();
        int[] sumSalary = new int[names.length];
        for (String datum : data) {
            String[] personalArray = datum.split(" ");
            LocalDate dateNow = LocalDate.parse(personalArray[DATA_ARRAY], formatter);
            if ((dateNow.isAfter(dateStart) && (dateNow.isBefore(dateEnd))
                    || (dateNow.equals(dateStart)) || (dateNow.equals(dateEnd)))) {
                for (int j = 0; j < names.length; ++j) {
                    if (names[j].equals(personalArray[NAME_ARRAY])) {
                        sumSalary[j] += Integer.parseInt(personalArray[HOURS_ARRAY])
                            * Integer.parseInt(personalArray[COST_ARRAY]);
                    }
                }
            }
        }
        StringBuilder personSalary = new StringBuilder(names.length);
        personSalary.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int k = 0; k < names.length; k++) {
            personSalary.append(separator).append(names[k]).append(" - ").append(sumSalary[k]);
        }
        return personSalary.toString();
    }
}
