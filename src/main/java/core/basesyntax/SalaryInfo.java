package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = convertToLocalDate(dateFrom);
        LocalDate localDateTo = convertToLocalDate(dateTo);
        LocalDate localDateTemp;

        int[] salary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            localDateTemp = convertToLocalDate(data[i]);
            if ((localDateTemp.isAfter(localDateFrom)
                    || localDateTemp.isEqual(localDateFrom))
                    && (localDateTemp.isBefore(localDateTo)
                    || localDateTemp.isEqual(localDateTo))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(data[i].split(" ")[1])) {
                        salary[j] += getSalary(data[i]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }

    private LocalDate convertToLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date.split(" ")[0], formatter);
    }

    private int getSalary(String data) {

        int hours = Integer.parseInt(data.split(" ")[2]);
        int hourlyWage = Integer.parseInt(data.split(" ")[3]);

        return hours * hourlyWage;
    }
}
