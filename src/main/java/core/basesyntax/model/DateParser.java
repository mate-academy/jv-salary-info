package core.basesyntax.model;

/**
 * Реализовать методы:
 * - getYear() возвращает год в виде числа.
 * - getMonth() возвращает месяц в виде числа.
 * - getDay() возвращает день в в виде числа.
 * - parseDateToInt() возвращает дату в целочисленном формате.
 * - splitDate() разделить строку даты по символу '.'
 * - parseEmployeeDate() вернуть кол-во часов и доход в час.
 */

public class DateParser {
    public int getYear(String date) {
        String[] dates = splitDate(date);
        return parseDateToInt(dates[2]);
    }

    public int getMonth(String date) {
        String[] dates = splitDate(date);
        return parseDateToInt(dates[1]);
    }

    public int getDay(String date) {
        String[] dates = splitDate(date);
        return parseDateToInt(dates[0]);
    }

    private String[] splitDate(String date) {
        return date.replace(".", " ").split(" ");
    }

    private int parseDateToInt(String date) {
        return Integer.parseInt(date);
    }

    /**
     * Пример входных данных:
     * current => [26.04.2019 John 4 50]
     * regex => [26.04.2019 John ]
     * return [4 50]
     */

    public String parseEmployeeDate(String current, String regex) {
        return current.replaceAll(regex, "");
    }

}
