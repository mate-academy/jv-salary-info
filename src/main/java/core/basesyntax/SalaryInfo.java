package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private StringBuilder builder = new StringBuilder("Report for period");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        String[] start = dateFrom.split("\\.");
        String[] end = dateTo.split("\\.");
        LocalDate startToWork = LocalDate.of(Integer.valueOf(start[2]),
                Integer.valueOf(start[1]), Integer.valueOf(start[0]));
        LocalDate endToWork = LocalDate.of(Integer.valueOf(end[2]),
                Integer.valueOf(end[1]), Integer.valueOf(end[0]));
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ");
            int sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataSeparated = data[j].split(" ");
                String[] workD = dataSeparated[0].split("\\.");
                LocalDate workingDay = LocalDate.of(Integer.valueOf(workD[2]),
                        Integer.valueOf(workD[1]), Integer.valueOf(workD[0]));
                if (!(workingDay.getDayOfYear() >= startToWork.getDayOfYear()
                        && workingDay.getDayOfYear() <= endToWork.getDayOfYear())) {
                    continue;
                }
                if (names[i].equals(dataSeparated[1])) {
                    sum += Integer.valueOf(dataSeparated[2]) * Integer.valueOf(dataSeparated[3]);
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
