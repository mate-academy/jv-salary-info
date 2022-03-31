package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate dayFrom = LocalDate.parse(dateFrom, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        int count = 0;
        for (String name : names) {
            int salary = 0;
            count++;
            for (String item : data) {
                String[] processData = item.split(" ");
                if (processData[1].equals(name)) {
                    if (!(result.toString().contains(name))) {
                        result.append(name).append(" - ");
                    }
                    LocalDate dayTo = LocalDate.parse(dateTo, formatter);
                    LocalDate empDays = LocalDate.parse(processData[0], formatter);
                    if (empDays.compareTo(dayFrom) >= 0 && (dayTo.compareTo(empDays) >= 0)) {
                        salary += (Integer.parseInt(processData[2])
                                * Integer.parseInt(processData[3]));
                    }
                }
            }
            if (count < names.length) {
                result.append(salary).append(System.lineSeparator());
            } else {
                result.append(salary);
            }
        }
        return result.toString();
    }
}
