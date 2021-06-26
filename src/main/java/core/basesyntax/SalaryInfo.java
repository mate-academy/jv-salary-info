package core.basesyntax;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        int year = 0;
        int month = 0;
        int dayOfMonth = 0;

        int countForEach = 0;

        for (String value : dateFrom.split("\\.")) {
            if (countForEach == 0) {
                dayOfMonth = Integer.parseInt(value);
            } else if (countForEach == 1) {
                month = Integer.parseInt(value);
            } else if (countForEach == 2) {
                year = Integer.parseInt(value);
            }

            countForEach++;
        }

        countForEach = 0;

        LocalDate ldBegin = LocalDate.of(year, month, dayOfMonth);

        year = 0;
        month = 0;
        dayOfMonth = 0;

        for (String value : dateTo.split("\\.")) {
            if (countForEach == 0) {
                dayOfMonth = Integer.parseInt(value);
            } else if (countForEach == 1) {
                month = Integer.parseInt(value);
            } else if (countForEach == 2) {
                year = Integer.parseInt(value);
            }

            countForEach++;
        }

        countForEach = 0;

        LocalDate ldEnd = LocalDate.of(year, month, dayOfMonth);
        int countValuesInDataUser = 4;

        String[] inputDataUser = new String[countValuesInDataUser];
        Map<String, Integer> userData = new HashMap<String, Integer>();

        for (int i = 0; i < data.length; i++) {

            for (String value : data[i].split(" ")) {
                inputDataUser[countForEach] = value;
                countForEach++;
            }

            countForEach = 0;

            for (String value : inputDataUser[0].split("\\.")) {
                if (countForEach == 0) {
                    dayOfMonth = Integer.parseInt(value);
                } else if (countForEach == 1) {
                    month = Integer.parseInt(value);
                } else if (countForEach == 2) {
                    year = Integer.parseInt(value);
                }

                countForEach++;
            }

            countForEach = 0;

            LocalDate ldUser = LocalDate.of(year, month, dayOfMonth);

            if ((ldUser.isAfter(ldBegin) || ldUser.isEqual(ldBegin)) && (
                    ldUser.isEqual(ldEnd) || ldUser.isBefore(ldEnd))) {
                int hours = Integer.parseInt(inputDataUser[2]);
                int salaryForHour = Integer.parseInt(inputDataUser[3]);
                Integer v = userData.get(inputDataUser[1]);

                if (v == null) {
                    userData.put(inputDataUser[1], hours * salaryForHour);
                } else {
                    userData.put(inputDataUser[1], v + hours * salaryForHour);
                }
            }

            year = 0;
            month = 0;
            dayOfMonth = 0;

        }

        StringBuilder stringBuilder = new StringBuilder();

        if (userData.isEmpty()) {
            stringBuilder.append("Report for period " + dateFrom + " - " + dateTo + "\n");
            stringBuilder.append(names[0] + " - " + 0 + "\n");
            stringBuilder.append(names[1] + " - " + 0 + "\n");
            stringBuilder.append(names[2] + " - " + 0);

            String result = stringBuilder.toString();
            return result;
        }

        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo + "\n");

        for (Map.Entry<String, Integer> item : userData.entrySet()) {
            if (item.getKey().equals(names[0])) {
                stringBuilder.append(item.getKey() + " - " + item.getValue() + "\n");
            }
        }

        for (Map.Entry<String, Integer> item : userData.entrySet()) {
            if (item.getKey().equals(names[1])) {
                stringBuilder.append(item.getKey() + " - " + item.getValue() + "\n");
            }
        }

        for (Map.Entry<String, Integer> item : userData.entrySet()) {
            if (item.getKey().equals(names[2])) {
                stringBuilder.append(item.getKey() + " - " + item.getValue());
            }
        }

        String result = stringBuilder.toString();

        return result;

    }
}
