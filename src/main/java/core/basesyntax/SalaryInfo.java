package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, dateFormatter).minusDays(1);
        LocalDate dateEnd = LocalDate.parse(dateTo, dateFormatter).plusDays(1);
        LocalDate currentDate;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] currentLine = data[j].split(" ");
                currentDate = LocalDate.parse(currentLine[0], dateFormatter);
                if (currentDate.isAfter(dateStart) && currentDate.isBefore(dateEnd)
                        && currentLine[1].equals(names[i])) {
                    salaries[i] += Integer.parseInt(currentLine[2])
                            * Integer.parseInt(currentLine[3]);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append("\n").append(names[i]).append(" - ").append(salaries[i]);
        }
        return stringBuilder.toString();
    }
}
