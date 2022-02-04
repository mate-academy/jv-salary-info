package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate[] datesFromArray = new LocalDate[data.length];
        String[] namesFromArray = new String[data.length];
        String[] hoursFromArray = new String[data.length];
        String[] salaryFromArray = new String[data.length];
        int[] salarys = new int[data.length];
        int[] salary = new int[names.length];
        String line = System.lineSeparator();
        StringBuilder str = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < data.length; i++) {
            String[] arr = data[i].split(" ");
            datesFromArray[i] = LocalDate.parse(arr[0], formatter);
            namesFromArray[i] = arr[1];
            hoursFromArray[i] = arr[2];
            salaryFromArray[i] = arr[3];
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if ((datesFromArray[i].isEqual(startDate)
                        || datesFromArray[i].isAfter(startDate))
                        && (datesFromArray[i].isEqual(endDate)
                        || datesFromArray[i].isBefore(endDate))) {
                    salarys[i] = Integer.parseInt(hoursFromArray[i])
                            * Integer.parseInt(salaryFromArray[i]);
                }
                if (names[j].equals(namesFromArray[i])) {
                    salary[j] += salarys[i];
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            str.append(line).append(names[i]).append(" - ").append(salary[i]);
        }
        System.out.println(str);

        return str.toString();
    }
}
