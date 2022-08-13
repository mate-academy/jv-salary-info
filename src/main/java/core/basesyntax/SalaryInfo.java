package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int income = 0;
            for (String dataInfo : data) {
                String[] dataParts = dataInfo.split(" ");
                if (formatData(dataParts[DATE]).isAfter(formatData(dateFrom).minusDays(1))
                        && formatData(dataParts[DATE]).isBefore(formatData(dateTo).plusDays(1))
                        && dataParts[NAME].equals(name)) {
                    income += calculateSalary(dataParts[HOURS], dataParts[SALARY]);
                }
            }
            builder.append(name).append(" - ").append(income).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private int calculateSalary(String hours, String salary) {
        return Integer.parseInt(hours) * Integer.parseInt(salary);
    }

    private LocalDate formatData(String data) {
        String[] arrData = data.split("\\.");
        return LocalDate.of(Integer.parseInt(arrData[2]),
                Integer.parseInt(arrData[1]), Integer.parseInt(arrData[0]));
    }
}
