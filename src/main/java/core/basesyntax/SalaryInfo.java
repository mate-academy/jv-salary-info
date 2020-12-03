package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        String[] stringDateFromArray = dateFrom.split("\\.");
        String[] stringDateToArray = dateTo.split("\\.");
        LocalDate dateStart = LocalDate.of(Integer.parseInt(stringDateFromArray[2]),
                Integer.parseInt(stringDateFromArray[1]),
                Integer.parseInt(stringDateFromArray[0]));
        LocalDate dateEnd = LocalDate.of(Integer.parseInt(stringDateToArray[2]),
                Integer.parseInt(stringDateToArray[1]),
                Integer.parseInt(stringDateToArray[0]));
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] currentLine = data[j].split(" ");
                int[] currentDateInt = new int[3];
                String[] currentDateArray = currentLine[0].split("\\.");
                for (int k = 0; k < 3; k++) {
                    currentDateInt[k] = Integer.parseInt(currentDateArray[k]);
                }
                LocalDate currentDate = LocalDate.of(currentDateInt[2], currentDateInt[1],
                        currentDateInt[0]);
                if (currentDate.isAfter(dateStart.minusDays(1))
                        && currentDate.isBefore(dateEnd.plusDays(1))
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
