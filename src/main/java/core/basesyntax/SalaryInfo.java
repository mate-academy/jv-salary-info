package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, sdf);
        LocalDate stopDate = LocalDate.parse(dateTo, sdf);
        String[] splitData;
        LocalDate date;
        String dataName;
        int hours;
        int wage;
        int salary;

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String line : data) {
                splitData = line.split(" ");
                date = LocalDate.parse(splitData[0], sdf);
                dataName = splitData[1];
                hours = Integer.parseInt(splitData[2]);
                wage = Integer.parseInt(splitData[3]);

                if (dataName.equals(names[i])) {
                    if (date.compareTo(startDate) >= 0 && date.compareTo(stopDate) <= 0) {
                        salary += wage * hours;
                    }
                }
            }
            sb.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return sb.toString();
    }
}
