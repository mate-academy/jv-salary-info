package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] infoSalary = new String[names.length];
        int salary = 0;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] infoToday = data[j].split(" ");
                if (infoToday[1].equals(names[i])) {
                    LocalDate currentDate = LocalDate.parse(infoToday[0], dateTimeFormatter);
                    if (LocalDate.parse(dateFrom, dateTimeFormatter).isAfter(currentDate)
                            || LocalDate.parse(dateTo, dateTimeFormatter).isBefore(currentDate)) {
                        continue;
                    }
                    salary += Integer.parseInt(infoToday[2]) * Integer.parseInt(infoToday[3]);
                }
            }
            infoSalary[i] = names[i] + " - " + salary;
            salary = 0;
        }

        String lineSeparator = System.lineSeparator();
        return "Report for period " + dateFrom + " - " + dateTo + lineSeparator + String.join(
                lineSeparator, infoSalary);
    }
}
