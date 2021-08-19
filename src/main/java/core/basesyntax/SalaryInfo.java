package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] compareData;
        String[] localdates;
        int[] countSalaries = new int[names.length];
        LocalDate compareDate;
        localdates = dateFrom.split("\\.");
        LocalDate fromDate = LocalDate.of(Integer.parseInt(localdates[2]),
                Integer.parseInt(localdates[1]),
                Integer.parseInt(localdates[0]));
        localdates = dateTo.split("\\.");
        LocalDate toDate = LocalDate.of(Integer.parseInt(localdates[2]),
                Integer.parseInt(localdates[1]),
                Integer.parseInt(localdates[0]));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String splitedDate : data) {
            compareData = splitedDate.split(" ");
            localdates = compareData[0].split("\\.");
            compareDate = LocalDate.of(Integer.parseInt(localdates[2]),
                    Integer.parseInt(localdates[1]),
                    Integer.parseInt(localdates[0]));
            for (int j = 0; j < names.length; j++) {
                if (fromDate.compareTo(compareDate) <= 0
                        && toDate.compareTo(compareDate) >= 0
                        && names[j].equals(compareData[1])) {
                    countSalaries[j] += Integer.parseInt(compareData[2])
                            * Integer.parseInt(compareData[3]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(countSalaries[i])
                    .append(System.lineSeparator());
        }
        int indexDel = stringBuilder.lastIndexOf(System.lineSeparator());
        stringBuilder.delete(indexDel, stringBuilder.length());
        return stringBuilder.toString();
    }
}
