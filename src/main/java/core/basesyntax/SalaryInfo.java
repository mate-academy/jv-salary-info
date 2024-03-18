package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final HashMap<String, Integer> hashMap = new HashMap<>();

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        hashMap.clear();
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String itemNames : names) {
            for (String itemDate : data) {
                String[] arrayElements = itemDate.split(" ");
                String employeeName = arrayElements[1];
                Date dateWork = null;
                try {
                    dateWork = dateFormat.parse(arrayElements[0]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (arrayElements[1].equals(itemNames) && dateWork.getTime() <= toDate.getTime()
                        && dateWork.getTime() >= fromDate.getTime()) {
                    int salary = Integer.parseInt(arrayElements[3]);
                    int day = Integer.parseInt(arrayElements[2]);
                    if (hashMap.containsKey(employeeName)) {
                        hashMap.put(employeeName, hashMap.get(employeeName) + salary * day);
                    } else {
                        hashMap.put(employeeName, salary * day);
                    }
                }
            }
        }

        if (hashMap.isEmpty()) {
            for (String item : names) {
                hashMap.put(item, 0);
            }
        }

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\r\n");
        for (int i = 0; i < names.length; i++) {
            Integer salary = hashMap.get(names[i]);
            if (salary != null) {
                if (i < names.length - 1) {
                    resultBuilder.append(names[i]).append(" - ").append(salary).append("\r\n");
                } else {
                    resultBuilder.append(names[i]).append(" - ").append(salary);
                }
            }
        }
        return resultBuilder.toString();
    }
}
