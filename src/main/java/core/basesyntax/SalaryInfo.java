package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate to = LocalDate.parse(dateTo,dateFormatter);
        StringBuilder info = new StringBuilder("Report for period "
                                                + dateFrom + " - " + dateTo + "\n");

        for (int i = 0; i < names.length; i++) {
            //update variable
            salary = 0;
            for (int k = 0; k < data.length; k++) {
                String[] date = data[k].split(" "); // parse string to part "25.04.2019 John 60 50"
                LocalDate current = LocalDate.parse(date[0], dateFormatter);

                if (names[i].equals(date[1])) {
                    if (from.isBefore(current) && current.isBefore(to)
                            || from.isBefore(current) && current.equals(to)) {
                        int hours = Integer.parseInt(date[2]);
                        int salaryPerHour = Integer.parseInt(date[3]);
                        salary = salary + (salaryPerHour * hours);
                    }
                }
            }
            info.append(names[i]).append(" - ").append(salary).append("\n");
        }
        return info.toString().trim();
    }
}
