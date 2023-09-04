package core.basesyntax;

public class SalaryInfo {
    static final int SIZE_ARRAY = 3;
    static final int SIZE_SPLIT_DATA_ARRAY = 4;
    static final int INDEX_DATE = 0;
    static final int INDEX_DAY = 0;
    static final int INDEX_MONTH = 1;
    static final int INDEX_YEAR = 2;
    static final int INDEX_NAME = 1;
    static final int INDEX_HOURS = 2;
    static final int INDEX_MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        final String[] dateFromSplit = dateFrom.split("\\.");
        final String[] dateToSplit = dateTo.split("\\.");
        StringBuilder result = new StringBuilder("Report for period ");

        result.append(dateFrom).append(" - ").append(dateTo);

        int[] salary = new int[names.length];
        String[] splitDataArray = new String[SIZE_SPLIT_DATA_ARRAY];
        String[] dateCurrentSplit = new String[SIZE_ARRAY];

        for (int i = 0; i < data.length; i++) {
            splitDataArray = data[i].split(" ");
            dateCurrentSplit = splitDataArray[INDEX_DATE].split("\\.");

            if (Integer.parseInt(dateCurrentSplit[INDEX_YEAR])
                    >= Integer.parseInt(dateFromSplit[INDEX_YEAR])
                    && Integer.parseInt(dateCurrentSplit[INDEX_YEAR])
                    <= Integer.parseInt(dateToSplit[INDEX_YEAR])) {

                if (Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                        >= Integer.parseInt(dateFromSplit[INDEX_MONTH])
                        && Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                        <= Integer.parseInt(dateToSplit[INDEX_MONTH])) {

                    if (Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                            == Integer.parseInt(dateFromSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            >= Integer.parseInt(dateFromSplit[INDEX_DAY])
                            && Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                            == Integer.parseInt(dateToSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            <= Integer.parseInt(dateToSplit[INDEX_DAY])

                            || Integer.parseInt(dateFromSplit[INDEX_MONTH])
                            != Integer.parseInt(dateToSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                            == Integer.parseInt(dateFromSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            >= Integer.parseInt(dateFromSplit[INDEX_DAY])

                            || Integer.parseInt(dateFromSplit[INDEX_MONTH])
                            != Integer.parseInt(dateToSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_MONTH])
                            == Integer.parseInt(dateToSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            <= Integer.parseInt(dateToSplit[INDEX_DAY])

                            || Integer.parseInt(dateFromSplit[INDEX_MONTH])
                            != Integer.parseInt(dateToSplit[INDEX_MONTH])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            >= Integer.parseInt(dateFromSplit[INDEX_DAY])
                            && Integer.parseInt(dateCurrentSplit[INDEX_DAY])
                            <= Integer.parseInt(dateToSplit[INDEX_DAY])) {

                        for (int j = 0; j < names.length; j++) {
                            if (names[j].equals(splitDataArray[INDEX_NAME])) {
                                salary[j] += Integer.parseInt(splitDataArray[INDEX_HOURS])
                                        * Integer.parseInt(splitDataArray[INDEX_MONEY]);
                            }
                        }

                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }

        return result.toString();
    }
}
