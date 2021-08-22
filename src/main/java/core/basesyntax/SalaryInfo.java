package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dateFromSplit = dateFrom.split("\\.");
        String[] dateToSplit = dateTo.split("\\.");

        LocalDate dateFromConvert = LocalDate.of(Integer.parseInt(dateFromSplit[2]),
                Integer.parseInt(dateFromSplit[1]),
                Integer.parseInt(dateFromSplit[0]));
        LocalDate dateToConvert = LocalDate.of(Integer.parseInt(dateToSplit[2]),
                Integer.parseInt(dateToSplit[1]),
                Integer.parseInt(dateToSplit[0]));

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                LocalDate checkDate =
                        LocalDate.of(Integer.parseInt(data[j].split(" ")[0].split("\\.")[2]),
                        Integer.parseInt(data[j].split(" ")[0].split("\\.")[1]),
                        Integer.parseInt(data[j].split(" ")[0].split("\\.")[0]));
                if (!checkDate.isBefore(dateFromConvert) && !checkDate.isAfter(dateToConvert)) {
                    if (data[j].split(" ")[1].equals(names[i])) {
                        salary[i] += Integer.parseInt(data[j].split(" ")[2])
                                * Integer.parseInt(data[j].split(" ")[3]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }

        return result.toString();
    }
}
