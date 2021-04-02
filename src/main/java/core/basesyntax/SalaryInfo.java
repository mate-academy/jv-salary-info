package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            String name = names[i];

            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(name)) {
                    String[] dataArray = data[j].split(" ");
                    LocalDate workerDate = LocalDate.parse(dataArray[0], FORMATTER);

                    if (workerDate.isAfter(localDateFrom)
                            && workerDate.isBefore(localDateTo.plusDays(1))) {
                        salary[i] += Integer.parseInt(dataArray[3])
                                * Integer.parseInt(dataArray[2]);
                    }
                }
            }
            builder.append(name).append(" - ").append(salary[i]).append("\n");
        }
        String result = builder.toString().strip();
        return result;
    }
}
