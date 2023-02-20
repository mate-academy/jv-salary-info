package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    private static final byte DATE_LENGTH = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        Date date1 = convertStringToDate(dateFrom);
        Date date2 = convertStringToDate(dateTo);

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (int k = 0; k < data.length; k++) {
                if (data[k].contains(names[i])) {
                    String[] separateData = data[k].split(" ");
                    Date date = convertStringToDate(separateData[0]);
                    if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) {
                        totalSalary += Integer.parseInt(separateData[2]) * Integer.parseInt(separateData[3]);
                    }
                }
            }
            result.append(names[i]).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        result.delete(result.length() - 2, result.length());
        return result.toString();

    }

    private static final Date convertStringToDate(String date) {
        String[] dateSeparateArray = date.split("\\.");
        int[] dateNumber = new int[dateSeparateArray.length];
        for (int i = 0; i < DATE_LENGTH; i++) {
            dateNumber[i] = Integer.parseInt(dateSeparateArray[i]);
        }
        return new Date(dateNumber[2], dateNumber[1], dateNumber[0]);
    }
}
