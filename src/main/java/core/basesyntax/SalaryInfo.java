package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] result = new String[names.length + 1];
        String[] dataDB;
        int salary;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromAsDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toAsDate = LocalDate.parse(dateTo, formatter);

        result[0] = String.format("Report for period %s - %s", dateFrom, dateTo);
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String dateElem:data) {
                dataDB = dateElem.split(" ");
                boolean dateInRange = LocalDate.parse(dataDB[0], formatter).isAfter(fromAsDate)
                        && LocalDate.parse(dataDB[0], formatter).isBefore(toAsDate)
                        || LocalDate.parse(dataDB[0], formatter).isEqual(fromAsDate)
                        || LocalDate.parse(dataDB[0], formatter).isEqual(toAsDate);
                if (!names[i].isEmpty() && names[i].equals(dataDB[1]) && dateInRange) {
                    salary += Integer.parseInt(dataDB[2]) * Integer.parseInt(dataDB[3]);
                }
            }
            result[i + 1] = String.format("%s - %d", names[i], salary);
        }
        return String.join(System.lineSeparator(), result);
    }
}
