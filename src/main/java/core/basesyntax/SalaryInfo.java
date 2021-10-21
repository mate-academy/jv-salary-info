package core.basesyntax;

import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            int salary = 0;
            for (String d : data) {
                String[] strings = d.split(" ");
                if (!name.equals(strings[1])) {
                    continue;
                } else if (dataRange(strings[0], dateFrom, dateTo)) {
                    salary += Integer.parseInt(strings[2]) * Integer.parseInt(strings[3]);
                }
            }
            stringBuilder.append(salary);

        }
        return stringBuilder.toString();
    }

    public int[] getArrayData(String data) {
        String[] strings = data.split("\\.");
        int[] ints = new int[3];
        for (int i = 0; i < 3; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    public boolean dataRange(String data, String dataFrom, String dataTo) {
        int[] dataInt = getArrayData(data);
        int[] dataFromInt = getArrayData(dataFrom);
        int[] dataToInt = getArrayData(dataTo);
        if (dataFromInt[2] == dataToInt[2] && dataFromInt[1] == dataToInt[1] && dataFromInt[0] == dataToInt[0]){
            return dataInt[2] == dataFromInt[2] && dataInt[1] == dataFromInt[1] && dataInt[0] == dataFromInt[0];
        } else if (dataFromInt[2] == dataToInt[2] && dataFromInt[1] == dataToInt[1]) {
            return dataInt[2] == dataFromInt[2] && dataInt[1] == dataFromInt[1]
                    && (dataInt[0] >= dataFromInt[0] && dataInt[0] <= dataToInt[0]);
        } else if (dataFromInt[2] == dataToInt[2]) {
            return dataInt[2] == dataFromInt[2]
                    && ((dataInt[1] > dataFromInt[1] && dataInt[1] < dataToInt[1])
                    || (dataInt[1] == dataFromInt[1] && dataInt[0] >= dataFromInt[0])
                    || (dataInt[1] == dataToInt[1] && dataInt[0] <= dataToInt[0]));
        } else {
                return false;               // ruled out the possibility of different years
        }
    }
}