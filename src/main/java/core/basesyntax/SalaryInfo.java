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
        LocalDate dateOne = LocalDate.from(formatter.parse(dateFrom));
        LocalDate dateThree = LocalDate.from(formatter.parse(dateTo));
        StringBuilder builder = new StringBuilder();
        builder.append(MASSAGE_FOR_REPORT).append(dateFrom)
                .append(DASH).append(dateTo).append(System.lineSeparator());
        for (int namesIndex = 0; namesIndex < names.length; namesIndex++) {
            for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
                if (names[namesIndex].equals(getNameFromData(data[dataIndex]))) {
                    String[] dataArray = data[dataIndex].split(" ");
                    if (compareDates(dateOne, dataArray[0], dateThree)) {
                        periodSalary += getSalary(data[dataIndex]);
                    }
                }
            }
            if (namesIndex < names.length - 1) {
                builder.append(names[namesIndex]).append(DASH)
                        .append(periodSalary).append(System.lineSeparator());
            } else {
                builder.append(names[namesIndex]).append(DASH).append(periodSalary);
            }
            periodSalary = 0;
        }
        return builder.toString();
    }

    private boolean compareDates(LocalDate dateOne, String dateFromArray, LocalDate dateThree) {
        LocalDate dateTwo = LocalDate.from(formatter.parse(dateFromArray));
        if (dateOne.isBefore(dateTwo) && dateTwo.compareTo(dateThree) <= 0) {
            return true;
        }
        return false;
    }

    private String getNameFromData(String name) {
        int index = name.indexOf(SPACE);
        String infoWithoutData = name.substring(index + 1);
        index = infoWithoutData.indexOf(SPACE);
        return infoWithoutData.substring(0, index);
    }

    private int getSalary(String data) {
        int index1 = data.lastIndexOf(SPACE);
        int rate = Integer.parseInt(data.substring(index1 + 1));
        data = data.substring(0, index1);
        index1 = data.lastIndexOf(SPACE);
        int hour = Integer.parseInt(data.substring(index1 + 1));
        return hour * rate;
    }
}

