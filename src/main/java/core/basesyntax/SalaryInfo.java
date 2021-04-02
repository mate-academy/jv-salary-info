package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMAT);

        String[] splitData;
        int[] salaries = new int[3];

        for (String info : data) {
            splitData = info.split(" ");
            LocalDate newDate = LocalDate.parse(splitData[0], DATE_FORMAT);
            if ((newDate.equals(dateEnd) || newDate.isBefore(dateEnd))
                    && (newDate.isAfter(dateStart) || newDate.equals(dateStart))) {
                for (int i = 0; i < 3; i++) {
                    if (names[i].equals(splitData[1])) {
                        salaries[i] += Integer.parseInt(splitData[2])
                                * Integer.parseInt(splitData[3]);
                        break;
                    }
                }
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo + "\nJohn - "
                + salaries[0] + "\nAndrew - " + salaries[1] + "\nKate - " + salaries[2];
    }
}

