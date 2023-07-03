package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final int START_STRING = 0;
    private static final int END_STRING = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\r\n");
        for (int i = 0; i < names.length; i++) {
            int userSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempData = data[j].split(" ");
                try {
                    if (names[i].equals(tempData[1])
                            && simpleDateFormat.parse(dateFrom)
                            .compareTo(simpleDateFormat.parse(tempData[0])) <= 0
                            && simpleDateFormat.parse(dateTo)
                            .compareTo(simpleDateFormat.parse(tempData[0])) >= 0) {
                        userSalary += Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            salaryInfo.append(names[i]).append(" - ").append(userSalary).append("\r\n");
        }
        return salaryInfo.substring(START_STRING, salaryInfo.length() - END_STRING);
    }
}
