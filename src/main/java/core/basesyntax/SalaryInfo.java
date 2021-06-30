package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        int userValues = 4;

        String[] arrayOfData = new String[userValues];
        Map<String, Integer> userData = new HashMap<String, Integer>();

        for (String line : data) {
            arrayOfData = line.split(" ");
            LocalDate currentDate = LocalDate.parse(arrayOfData[0], FORMATTER);

            for (String name : names) {
                if ((name.equals(arrayOfData[1])) && (currentDate.isAfter(localDateFrom)
                        || currentDate.isEqual(localDateFrom))
                        && (currentDate.isEqual(localDateTo)
                        || currentDate.isBefore(localDateTo))) {
                    int hours = Integer.parseInt(arrayOfData[2]);
                    int salaryForHour = Integer.parseInt(arrayOfData[3]);

                    if (userData.get(arrayOfData[1]) == null) {
                        userData.put(arrayOfData[1], hours * salaryForHour);
                    } else {
                        userData.put(arrayOfData[1], userData.get(arrayOfData[1])
                                + hours * salaryForHour);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        if (userData.isEmpty()) {
            for (String name : names) {
                result.append(System.lineSeparator()).append(name).append(" - ").append(0);
            }

            return result.toString();
        }

        for (String name : names) {
            result.append(System.lineSeparator()).append(name).append(" - ")
                    .append(userData.get(name));
        }

        return result.toString();
    }
}
