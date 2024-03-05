package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] salaries = new String[names.length];
        int index = 0;
        int income = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate finishDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] personalData = data[i].split(" ");
                LocalDate currentDate = LocalDate.parse(personalData[0], formatter);
                if (name.equals(personalData[1]) && currentDate.compareTo(startDate) >= 0
                        && currentDate.compareTo(finishDate) <= 0) {
                    income += Integer.parseInt(personalData[2]) * Integer.parseInt(personalData[3]);
                }
            }
            salaries[index] = new StringBuilder().append(name).append(" - ")
                    .append(income).toString();
            index++;
            income = 0;
        }
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String salary : salaries) {
            result.append(System.lineSeparator()).append(salary);
        }
        return result.toString();
    }
}
