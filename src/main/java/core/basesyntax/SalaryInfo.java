package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        int salary = 0;
        String[] totallInformation = new String[names.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String datum : data) {
                LocalDate dataFromString = getDate(getInfoFromString(datum)[0]);
                if ((dataFromString.isAfter(localDateFrom)
                        || dataFromString.isEqual(localDateFrom))
                        && (dataFromString.isBefore(localDateTo)
                        || dataFromString.isEqual(localDateTo))) {
                    if (names[i].equals(getInfoFromString(datum)[1])) {
                        salary += Integer.parseInt(getInfoFromString(datum)[2])
                                * Integer.parseInt(getInfoFromString(datum)[3]);
                    }
                }
            }
            sb.append(names[i]).append(" - ").append(salary);
            totallInformation[i] = sb.toString();
            sb.delete(0, sb.length());
        }
        return showInfo(totallInformation, dateFrom, dateTo);
    }

    private String showInfo(String[] totallInformation, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String string : totallInformation) {
            sb.append(System.lineSeparator()).append(string);
        }
        return sb.toString();
    }

    private String[] getInfoFromString(String data) {
        return data.split(" ");
    }

    private LocalDate getDate(String dateFrom) {
        String[] date = dateFrom.split("\\.");
        return LocalDate.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[0]));
    }

}
