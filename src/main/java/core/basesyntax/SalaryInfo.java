package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom). append(" - ").append(dateTo);
        LocalDate lowBound = getLocalDate(dateFrom).minusDays(1);
        LocalDate topBound = getLocalDate(dateTo).plusDays(1);
        for (String name : names) {
            builder.append(System.lineSeparator());
            builder.append(getEarning(name, data, lowBound, topBound));
        }
        return builder.toString();
    }

    private String getEarning(String name, String[] data,
                                     LocalDate dateFrom, LocalDate dateTo) {
        StringBuilder builder = new StringBuilder();
        int workingHours = 0;
        String[] workingDayOfEmployee;
        for (int i = 0; i < data.length; i++) {
            workingDayOfEmployee = data[i].split(" ");
            if (name.equals(workingDayOfEmployee[1])) {
                if (dataValidate(getLocalDate(workingDayOfEmployee[0]), dateFrom, dateTo)) {
                    workingHours += Integer.parseInt(workingDayOfEmployee[2])
                            * Integer.parseInt(workingDayOfEmployee[3]);
                }
            }
        }
        builder.append(name).append(" - ").append(workingHours);
        return builder.toString();
    }

    private LocalDate getLocalDate(String date) {
        String[] dateMassive = date.split("\\.");
        return LocalDate.of(Integer.parseInt(dateMassive[2]),
                Integer.parseInt(dateMassive[1]), Integer.parseInt(dateMassive[0]));
    }

    private boolean dataValidate(LocalDate dataFromList,
                                        LocalDate dateFrom, LocalDate dateTo) {
        return dataFromList.isAfter(dateFrom) && dataFromList.isBefore(dateTo);
    }
}
