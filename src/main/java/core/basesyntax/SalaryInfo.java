package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder buildAppend = new StringBuilder();
        buildAppend.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate localFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate localTo = LocalDate.parse(dateTo,formatter);

        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] arr = data[i].split(" ");
                LocalDate currentDate = LocalDate.parse(arr[0],formatter);
                if (name.equals(arr[1])
                        && (currentDate.isAfter(localFrom) || currentDate.equals(localFrom))
                        && (currentDate.isBefore(localTo) || currentDate.equals(localTo))) {
                    salary += Integer.parseInt(arr[2]) * Integer.parseInt(arr[3]);
                }
            }
            buildAppend.append(name).append(" - ").append(salary).append("\n");
        }
        return buildAppend.toString().trim();
    }

}
