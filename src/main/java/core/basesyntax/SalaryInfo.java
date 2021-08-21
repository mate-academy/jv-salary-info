package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
    throws DateTimeException {
        LocalDate startDate;
        LocalDate endDate;
        LocalDate dateInArray;
        int[] salaryOfEmployees = new int[names.length];
        try {
            startDate = LocalDate.parse(dateFrom, FORMATTER);
            endDate = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeException e) {
            System.out.println("Cannot parse date");
            throw e;
        }
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                try {
                    dateInArray = LocalDate.parse(data[j].split(" ")[0], FORMATTER);
                } catch (DateTimeException e) {
                    System.out.println("Cannot parse date from data");
                    throw e;
                }
                String nameFromData = data[j].split(" ")[1];
                int hours = Integer.parseInt(data[j].split(" ")[2]);
                int salaryPerHour = Integer.parseInt(data[j].split(" ")[3]);

                if (nameFromData.equals(names[i]) && dateInArray.isAfter(startDate)
                        && dateInArray.isBefore(endDate)) {
                    salary += hours * salaryPerHour;
                }
                System.out.println(salary);;
            }
            salaryOfEmployees[i] = salary;
        }
        return names[1] + " " + salaryOfEmployees[1];
    }
}
