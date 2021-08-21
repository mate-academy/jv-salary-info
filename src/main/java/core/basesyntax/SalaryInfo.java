package core.basesyntax;

public class SalaryInfo {
    private static final int COUNT_DATA_VARIABLES = 4;
    private static final int INDEX_YEAR_BEGIN = 6;
    private static final int INDEX_MONTH_BEGIN = 3;
    private static final int INDEX_MONTH_END = 5;
    private static final int INDEX_DAY_BEGIN = 0;
    private static final int INDEX_DAY_END = 2;
    private static final int INDEX_OUTDATA_NAME = 0;
    private static final int INDEX_OUTDATA_MONEY = 1;
    private static final int INDEX_OUTDATA_NAME_0 = 0;
    private static final int INDEX_OUTDATA_NAME_1 = 1;
    private static final int INDEX_OUTDATA_NAME_2 = 2;
    private static final int INDEX_OUTDATA_FIELDS_ALL = 2;
    private static final int INDEX_OUTDATA_NAMES_ALL = 3;
    private static final int INDEX_DATA_ZERO = 0;
    private static final int INDEX_DATA_NAME = 1;
    private static final int INDEX_DATA_TIME = 2;
    private static final int INDEX_DATA_MONEY = 3;

    public Integer stringToIntDate(String string) {
        return Integer.parseInt(new StringBuilder()
                .append(string.substring(INDEX_YEAR_BEGIN))
                .append(string.substring(INDEX_MONTH_BEGIN, INDEX_MONTH_END))
                .append(string.substring(INDEX_DAY_BEGIN, INDEX_DAY_END)).toString());
    }

    public String result(String[][] result, String dateFrom, String dateTo) {
        return new StringBuilder("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator())
                .append(result[INDEX_OUTDATA_NAME][INDEX_OUTDATA_NAME_0]).append(" - ")
                .append(result[INDEX_OUTDATA_MONEY][INDEX_OUTDATA_NAME_0])
                .append(System.lineSeparator())
                .append(result[INDEX_OUTDATA_NAME][INDEX_OUTDATA_NAME_1]).append(" - ")
                .append(result[INDEX_OUTDATA_MONEY][INDEX_OUTDATA_NAME_1])
                .append(System.lineSeparator())
                .append(result[INDEX_OUTDATA_NAME][INDEX_OUTDATA_NAME_2]).append(" - ")
                .append(result[INDEX_OUTDATA_MONEY][INDEX_OUTDATA_NAME_2]).toString();
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[][] table = new String[data.length][COUNT_DATA_VARIABLES];
        int dateFromInteger = stringToIntDate(dateFrom);
        int dateToInteger = stringToIntDate(dateTo);
        String[][] outData = new String[INDEX_OUTDATA_FIELDS_ALL][INDEX_OUTDATA_NAMES_ALL];
        for (int i = 0; i < outData[0].length; i++) {
            outData[INDEX_OUTDATA_NAME][i] = names[i];
            outData[INDEX_OUTDATA_MONEY][i] = "0";
        }
        for (int i = 0; i < data.length; i++) {
            table[i] = data[i].split(" ");
            table[i][INDEX_DATA_ZERO] = stringToIntDate(table[i][INDEX_DATA_ZERO]).toString();
            int tempDate = Integer.parseInt(table[i][INDEX_DATA_ZERO]);
            if (tempDate >= dateFromInteger && tempDate <= dateToInteger) {
                for (int j = 0; j < outData[0].length; j++) {
                    if (table[i][INDEX_DATA_NAME].equals(names[j])) {
                        outData[INDEX_OUTDATA_MONEY][j] = String.valueOf(Integer
                               .parseInt(outData[INDEX_OUTDATA_MONEY][j])
                               + Integer.parseInt(table[i][INDEX_DATA_TIME])
                               * Integer.parseInt(table[i][INDEX_DATA_MONEY]));
                    }
                }
            }
        }
        return result(outData, dateFrom, dateTo);
    }
}
