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

    public boolean isDateBetweenDates(String firstDate, String date, String secondDate) {
        return isLessOrEqual(firstDate, date) && isLessOrEqual(date, secondDate);
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
            //data check
            if (Validator.isValidDate(separatedInfo[0], dateTo, dateFrom)
                    && isDateBetweenDates(dateFrom, separatedInfo[0], dateTo)
                    && Validator.isNumbers(separatedInfo[2], separatedInfo[3])) {
                salaries[findIndexOfName(names, separatedInfo[1])]
                        += Integer.parseInt(separatedInfo[2])
                        * Integer.parseInt(separatedInfo[3]);
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }
        return result.toString();
    }
}
