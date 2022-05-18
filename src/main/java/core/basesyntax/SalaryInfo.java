package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom). append(" - ").append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator());
            builder.append(getEarning(name, data, getLocalDate(dateFrom).minusDays(1),
                    getLocalDate(dateTo).plusDays(1)));
        }
        return builder.toString();
    }

    private StringBuilder getEarning(String name, String[] data,
                                     LocalDate dateFrom, LocalDate dateTo) {
        StringBuilder builder = new StringBuilder();
        int totalWorkingHours = 0;
        String[] dayNotations;
        for (int i = 0; i < data.length; i++) {
            dayNotations = data[i].split(" ");
            if (name.equals(dayNotations[1])) {
                if (dataValidate(getLocalDate(dayNotations[0]), dateFrom, dateTo)) {
                    totalWorkingHours += Integer.parseInt(dayNotations[2])
                            * Integer.parseInt(dayNotations[3]);
                }
            }
        }
        builder.append(name).append(" - ").append(totalWorkingHours);
        return builder;
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
