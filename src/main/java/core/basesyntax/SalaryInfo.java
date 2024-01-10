package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);
        String[] temp;
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            for (String oneData: data) {
                temp = oneData.split(" ");
                try {
                    if (names[i].equals(temp[1])
                            && ((sdf.parse(temp[0]).compareTo(sdf.parse(dateFrom))) >= 0
                            && (sdf.parse(temp[0]).compareTo(sdf.parse(dateTo))) <= 0)) {
                        sum = sum + Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            stringBuilder.append("\n").append(names[i]).append(" - ").append(sum);
            sum = 0;
            if (i == names.length - 1) {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
