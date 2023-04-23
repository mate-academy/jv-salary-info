package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            for (int b = 0; b < data.length; b++) {
                if (names[i].equals(getNameFromData(data[b]))) {
                    String dataFromArray = data[b].substring(0, data[b].indexOf(" "));
                    if (compareDates(dateFrom, dataFromArray, dateTo)) {
                        sum += getSalary(data[b]);
                    }
                }
            }
            if (i < names.length - 1) {
                builder.append(names[i]).append(" - ").append(sum).append("\n");
            } else {
                builder.append(names[i]).append(" - ").append(sum);
            }
            sum = 0;
        }
        return builder.toString();
    }

    public boolean compareDates(String dateFrom, String dateFromArray, String dateTo) {
        try {
            Date dateOne = simpleDateFormat.parse(dateFrom);
            Date dateTwo = simpleDateFormat.parse(dateFromArray);
            Date dateThree = simpleDateFormat.parse(dateTo);
            if (dateOne.before(dateTwo)
                    && (dateTwo.before(dateThree) || dateTwo.equals(dateThree))) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNameFromData(String name) {
        int index = name.indexOf(" ");
        String infoWithoutData = name.substring(index + 1);
        index = infoWithoutData.indexOf(" ");
        return infoWithoutData.substring(0, index);
    }

    public int getSalary(String data) {
        int index = data.indexOf(" ");
        String dataSubstring = data.substring(index + 1);
        index = dataSubstring.indexOf(" ");
        dataSubstring = dataSubstring.substring(index + 1);
        index = dataSubstring.indexOf(" ");
        int hour = Integer.parseInt(dataSubstring.substring(0, index));
        dataSubstring = dataSubstring.substring(index + 1);
        int rate = Integer.parseInt(dataSubstring);
        return hour * rate;

    }
}

