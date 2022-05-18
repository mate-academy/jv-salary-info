package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE_CELL = 0;
    private static final int NAME_CELL = 1;
    private static final int WORKING_HOURS_CELL = 2;
    private static final int PAY_PER_HOUR_CELL = 3;
    private static final int DAY_CELL = 0;
    private static final int MONTH_CELL = 1;
    private static final int YEAR_CELL = 2;

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
            if (name.equals(dayNotations[NAME_CELL])) {
                if (dataValidate(getLocalDate(dayNotations[DATE_CELL]), dateFrom, dateTo)) {
                    totalWorkingHours += Integer.parseInt(dayNotations[WORKING_HOURS_CELL])
                            * Integer.parseInt(dayNotations[PAY_PER_HOUR_CELL]);
                }
            }
        }
        builder.append(name).append(" - ").append(totalWorkingHours);
        return builder;
    }

    private LocalDate getLocalDate(String date) {
        String[] dateMassive = date.split("\\.");
        return LocalDate.of(Integer.parseInt(dateMassive[YEAR_CELL]),
                Integer.parseInt(dateMassive[MONTH_CELL]), Integer.parseInt(dateMassive[DAY_CELL]));
    }

    private boolean dataValidate(LocalDate dataFromList,
                                        LocalDate dateFrom, LocalDate dateTo) {
        return dataFromList.isAfter(dateFrom) && dataFromList.isBefore(dateTo);
    }
}
