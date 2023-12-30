package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder results = new StringBuilder(
                ("Report for period ") + (dateFrom) + (" - ") + (dateTo));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        for (int i = 0; i < names.length; i++) {
            results.append(System.lineSeparator()).append(names[i]);
            int salary = 0;
            for (int y = 0; y < data.length; y++) {
                String[] nameData = data[y].split(" ");
                if (nameData[1].equals(names[i])) {
                    LocalDate date = LocalDate.parse(nameData[0], formatter);
                    if (date.isAfter(localDateFrom)
                            && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                        int hours = Integer.parseInt(nameData[2]);
                        int money = Integer.parseInt(nameData[3]);
                        salary += money * hours;
                    }
                }
            }
            results.append(" - ").append(salary);
        }
        return results.toString();
    }
}
