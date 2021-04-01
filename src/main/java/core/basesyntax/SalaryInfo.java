package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitedField;
        int[] dateFromArray;
        int[] dateToArray;
        int[] dateArray;
        int[] resultNumber = new int[names.length];
        StringBuilder result = new StringBuilder();

        for (String field: data) {
            splitedField = field.split(" ");
            dateArray = analyseDate(splitedField[0]);
            dateToArray = analyseDate(dateTo);
            dateFromArray = analyseDate(dateFrom);
            if (checkDates(dateArray, dateFromArray, dateToArray)) {
                resultNumber[findIndex(names, splitedField[1])]
                        += Integer.parseInt(splitedField[2]) * Integer.parseInt(splitedField[3]);
            }
        }

        for (int i = 0; i < names.length; i++) {
            result.append(String.format("\n%s - %d", names[i], resultNumber[i]));
        }

        return String.format("Report for period %s - %s"
                + "%s", dateFrom, dateTo, result.toString());
    }

    public static int findIndex(String[] arr, String t) {
        int i = 0;
        if (arr == null) {
            return -1;
        }
        while (i < arr.length) {
            if (arr[i].equals(t)) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    public boolean checkDates(int[] date, int[] dateFrom, int[] dateTo) {
        return (date[2] >= dateFrom[2] && date[2] <= dateTo[2])
                && (date[1] >= dateFrom[1] && date[1] <= dateTo[1])
                && ((date[1] != dateFrom[1] || dateFrom[1] != dateTo[1])
                || (date[0] >= dateFrom[0] && date[0] <= dateTo[0]))
                && ((date[1] != dateFrom[1]) || (date[0] >= dateFrom[0]))
                && ((date[1] != dateTo[1]) || (date[0] <= dateTo[0]));
    }

    public int[] analyseDate(String data) {
        String[] fullDate = data.split("\\.");
        int day = Integer.parseInt(fullDate[0]);
        int month = Integer.parseInt(fullDate[1]);
        int year = Integer.parseInt(fullDate[2]);
        return new int[]{day, month, year};
    }
}
