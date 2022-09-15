package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate[] dateOfCalendar = new LocalDate[data.length];
        String[] nameOfEmployee = new String[data.length];
        int[] workingHours = new int[data.length];
        int[] incomePerHour = new int[data.length];
        int[] salaryOfEmployee = new int[names.length];
        LocalDate dateFromCalendar = LocalDate.of(
                Integer.parseInt(dateFrom.substring(6, 10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2)) - 1);

        LocalDate dateToCalendar = LocalDate.of(
                Integer.parseInt(dateTo.substring(6, 10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2)) + 1);

        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split("\\s");
            dateOfCalendar[i] = LocalDate.of(Integer.parseInt(data[i].substring(6, 10)),
                    Integer.parseInt(data[i].substring(3, 5)),
                    Integer.parseInt(data[i].substring(0, 2)));
            nameOfEmployee[i] = splitData[1];
            workingHours[i] = Integer.parseInt(splitData[2]);
            incomePerHour[i] = Integer.parseInt(splitData[3]);
        }

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((names[i].equals(nameOfEmployee[j])) && dateOfCalendar[j]
                        .isAfter(dateFromCalendar)
                        && dateOfCalendar[j].isBefore(dateToCalendar)) {
                    salaryOfEmployee[i] += workingHours[j] * incomePerHour[j];
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator() + names[i] + " - " + salaryOfEmployee[i]);
        }

        return report.toString();
    }
}
