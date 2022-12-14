package core.basesyntax;

public class SalaryInfo {
    static final int INDEX_START_DAY_DATE = 0;
    static final int INDEX_END_DAY_DATE = 2;
    static final int INDEX_START_MONTH_DATE = 3;
    static final int INDEX_END_MONTH_DATE = 5;
    static final int INDEX_START_YEAR_DATE = 6;
    static final int INDEX_END_YEAR_DATE = 10;

    int parseInt(int index1, int index2, String s) {
        return Integer.parseInt(s.substring(index1, index2));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int[] salary = new int[names.length];

        int dayInDate = parseInt(INDEX_START_DAY_DATE, INDEX_END_DAY_DATE, dateFrom);
        int monthInDate = parseInt(INDEX_START_MONTH_DATE, INDEX_END_MONTH_DATE, dateFrom);
        int yearInDate = parseInt(INDEX_START_YEAR_DATE, INDEX_END_YEAR_DATE, dateFrom);
        int dayInToDate = parseInt(INDEX_START_DAY_DATE, INDEX_END_DAY_DATE, dateTo);
        int monthInToDate = parseInt(INDEX_START_MONTH_DATE, INDEX_END_MONTH_DATE, dateTo);
        int yearInToDate = parseInt(INDEX_START_YEAR_DATE, INDEX_END_YEAR_DATE, dateTo);

        for (String s : data) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] datum = s.toCharArray();
            int length = datum.length - 1;

            int indexStartName = s.indexOf(" ") + 1;
            int indexEndName = s.indexOf(" ", indexStartName);
            String nameInData = s.substring(indexStartName, indexEndName);
            int dayInData = parseInt(INDEX_START_DAY_DATE, INDEX_END_DAY_DATE, s);
            int monthInData = parseInt(INDEX_START_MONTH_DATE, INDEX_END_MONTH_DATE, s);
            int yearInData = parseInt(INDEX_START_YEAR_DATE, INDEX_END_YEAR_DATE, s);

            if (((((dayInData >= dayInDate && monthInData <= monthInDate)
                    || ((dayInData <= dayInDate && monthInData > monthInDate)))
                    && ((dayInData <= dayInToDate && monthInData == monthInToDate)
                    || dayInData >= dayInToDate && monthInData < monthInToDate)))
                    && (yearInData >= yearInDate && yearInData <= yearInToDate)) {

                do {
                    stringBuilder.append(datum[length]);
                    length--;
                } while (datum[length] != ' ');
                int hours = Integer.parseInt(s.substring(indexEndName + 1, length));

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(nameInData)) {
                        String reversed = String.valueOf(stringBuilder.reverse());
                        int sum = Integer.parseInt(reversed) * hours;

                        salary[i] += sum;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {

            if (i == 0) {
                result.append("Report for period ")
                        .append(dateFrom).append(" - ").append(dateTo).append("\r\n");
            }

            if (i == names.length - 1) {
                result.append(names[i]).append(" - ").append(salary[i]);
            } else {
                result.append(names[i]).append(" - ").append(salary[i]).append("\r\n");
            }

        }
        return result.toString();
    }

}
