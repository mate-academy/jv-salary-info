package core.basesyntax;

import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int lower = 0;
        int higher = 0;
        sortData(data);
        if (convertedDateToInt(trimDataEntryToDate(data[0]))
                <= convertedDateToInt(dateTo)) {
            higher = data.length;
            for (int i = 0; i < data.length; i++) {
                if (convertedDateToInt(trimDataEntryToDate(data[i]))
                        >= convertedDateToInt(dateFrom)) {
                    lower = i;
                    break;
                }
            }
            for (int i = lower; i < data.length; i++) {
                if (convertedDateToInt(trimDataEntryToDate(data[i]))
                        > convertedDateToInt(dateTo)) {
                    higher = i;
                    break;
                }
            }
        }
        String[] dataCut = new String[higher - lower];
        System.arraycopy(data, lower, dataCut, 0, higher - lower);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int sum = 0;
            for (String workday : dataCut) {
                if (workday.contains(name)) {
                    sum += sumWorkday(workday);
                }
            }
            stringBuilder.append(sum);
        }
        return stringBuilder.toString();
    }

    private int convertedDateToInt(String date) {
        return Integer.parseInt(convertDate(date));
    }

    private String convertDate(String date) {
        date = date.replaceAll("^(\\d+)\\D(\\d+)\\D([0-9]+)", "$3$2$1");
        return date;
    }

    private String trimDataEntryToDate(String data) {
        return (data.replaceAll("\\s\\w+\\s\\d+\\s\\d+$", ""));
    }

    private int sumWorkday(String workday) {
        String numWorkday = workday.replaceAll("^\\d+\\s\\w+\\s", "");
        return Integer.parseInt(numWorkday.replaceAll("^\\d+\\s", ""))
                * Integer.parseInt(numWorkday.replaceAll("\\s\\d+$", ""));
    }

    private String[] sortData(String[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = convertDate(data[i]);
        }
        Arrays.sort(data);
        return data;
    }
}
