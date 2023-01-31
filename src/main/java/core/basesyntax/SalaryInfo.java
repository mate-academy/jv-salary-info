package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_COUNT_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String LINE_SPLIT = " ";
    private static final String DATE_SPLIT = "\\.";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate localDateFrom = getDateFromString(dateFrom);
        LocalDate localDateTo = getDateFromString(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] datas = data[j].split(LINE_SPLIT);
                LocalDate localDate = getDateFromString(datas[DATE_INDEX]);
                if (names[i].equals(datas[NAME_INDEX]) && (localDate.isAfter(localDateFrom)
                        || localDate.isEqual(localDateFrom))
                        && (localDate.isEqual(localDateTo)
                        || localDate.isBefore(localDateTo))) {
                    salary += Integer.parseInt(datas[HOUR_COUNT_INDEX])
                            * Integer.parseInt(datas[INCOME_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private LocalDate getDateFromString(String string) {
        String[] date = string.split(DATE_SPLIT);
        return LocalDate.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]),Integer.parseInt(date[0]));
    }
}
