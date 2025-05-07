package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private LocalDate parseDate(String currentDateString) {
        LocalDate localDate;
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
            localDate = LocalDate.parse(currentDateString, formatter);

        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", currentDateString);
            throw exc;
        }

        return localDate;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String title = "Report for period " + dateFrom + " - " + dateTo;
        LocalDate dateFromLocal = parseDate(dateFrom);
        LocalDate dateToLocal = parseDate(dateTo);
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < names.length; i++) {

            sb.append(names[i])
                    .append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] temp = data[j].split(" ");
                LocalDate t = parseDate(temp[0]);
                if (temp[1].equals(names[i]) && t.isAfter(dateFromLocal) && t.isBefore(dateToLocal)
                        || temp[1].equals(names[i]) && t.equals(dateFromLocal)
                        || temp[1].equals(names[i]) && t.equals(dateToLocal)) {
                    int hours = Integer.parseInt(temp[2]);
                    int salary = Integer.parseInt(temp[3]);
                    sum += hours * salary;
                }
            }
            sb.append(sum).append(System.lineSeparator());
            sum = 0;
        }
        String report = title + System.lineSeparator() + sb.toString().trim();
        return report;
    }
}
