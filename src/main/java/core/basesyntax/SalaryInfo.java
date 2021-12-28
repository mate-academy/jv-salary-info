package core.basesyntax;

public class SalaryInfo {
    public boolean isLessOrEqual(String firstDate, String secondDate) {
        String[] first = firstDate.split("\\.");
        String[] second = secondDate.split("\\.");
        if (Integer.parseInt(first[2]) < Integer.parseInt(second[2])) {
            return true;
        }
        if (Integer.parseInt(first[2]) > Integer.parseInt(second[2])) {
            return false;
        }
        if (Integer.parseInt(first[1]) < Integer.parseInt(second[1])) {
            return true;
        }
        if (Integer.parseInt(first[1]) > Integer.parseInt(second[1])) {
            return false;
        }
        if (Integer.parseInt(first[0]) < Integer.parseInt(second[1])) {
            return true;
        }
        return Integer.parseInt(first[0]) <= Integer.parseInt(second[0]);
    }

    public int findIndexOfName(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        for (String info : data) {
            String[] separatedInfo = info.split(" ");
            if (isLessOrEqual(separatedInfo[0], dateTo)
                    && isLessOrEqual(dateFrom, separatedInfo[0])) {
                salaries[findIndexOfName(names, separatedInfo[1])]
                        += Integer.parseInt(separatedInfo[2]) * Integer.parseInt(separatedInfo[3]);
            }
        }
        String result = "Report for period " + dateFrom + " - " + dateTo;
        for (int i = 0; i < names.length; i++) {
            result += System.lineSeparator() + names[i] + " - " + salaries[i];
        }
        return result;
    }
}
