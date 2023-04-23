package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String MASSAGE_FOR_REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int periodSalary = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(MASSAGE_FOR_REPORT).append(dateFrom)
                .append(DASH).append(dateTo).append(System.lineSeparator());
        for (int namesIndex = 0; namesIndex < names.length; namesIndex++) {
            for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
                if (names[namesIndex].equals(getNameFromData(data[dataIndex]))) {
                    if (compareDates(dateFrom, data[dataIndex].substring(0, data[dataIndex].indexOf(SPACE)), dateTo)) {
                        periodSalary += getSalary(data[dataIndex]);
                    }
                }
            }
            if (namesIndex < names.length - 1) {
                builder.append(names[namesIndex]).append(DASH).append(periodSalary).append(System.lineSeparator());
            } else {
                builder.append(names[namesIndex]).append(DASH).append(periodSalary);
            }
            periodSalary = 0;
        }
        return builder.toString();
    }

    public boolean compareDates(String dateFrom, String dateFromArray, String dateTo) {
        LocalDate dateOne = LocalDate.from(formatter.parse(dateFrom));
        LocalDate dateTwo = LocalDate.from(formatter.parse(dateFromArray));
        LocalDate dateThree = LocalDate.from(formatter.parse(dateTo));
        if (dateOne.isBefore(dateTwo)
                && (dateTwo.isBefore(dateThree) || dateTwo.equals(dateThree))) {
            return true;
            }
        return false;
    }

    public String getNameFromData(String name) {
        int index = name.indexOf(SPACE);
        String infoWithoutData = name.substring(index + 1);
        index = infoWithoutData.indexOf(SPACE);
        return infoWithoutData.substring(0, index);
    }

    public int getSalary(String data) {
        int index1 = data.lastIndexOf(SPACE);
        int rate = Integer.parseInt(data.substring(index1 + 1));
        data = data.substring(0, index1);
        index1 = data.lastIndexOf(SPACE);
        int hour = Integer.parseInt(data.substring(index1 + 1));
        return hour * rate;
    }
}

