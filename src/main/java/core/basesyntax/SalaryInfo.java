package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        String separator = System.lineSeparator();
        int[] sumSalary = new int[names.length];
        for (String datum : data) {
            String[] personalArray = datum.split(" ");
            LocalDate dateNow = LocalDate.parse(personalArray[0], formatter);
            if ((dateNow.isAfter(dateStart) && (dateNow.isBefore(dateEnd))
                    || (dateNow.equals(dateStart)) || (dateNow.equals(dateEnd)))) {
                for (int j = 0; j < names.length; ++j) {
                    if (names[j].equals(personalArray[1])) {
                        sumSalary[j] += Integer.parseInt(personalArray[2])
                            * Integer.parseInt(personalArray[3]);
                    }
                }
            }
        }
        StringBuilder personSalary = new StringBuilder(names.length);
        personSalary.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int k = 0; k < names.length; k++) {
            personSalary.append(separator).append(names[k]).append(" - ").append(sumSalary[k]);
        }
        return new String(personSalary);
    }
}

