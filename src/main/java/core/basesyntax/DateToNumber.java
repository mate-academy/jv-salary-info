package core.basesyntax;

public class DateToNumber {
    public char[] dateToChars(String date) {
        char[] dateChars = date.toCharArray();
        return dateChars;
    }

    public int charsToInt(char[] numbers) {
        StringBuilder stb = new StringBuilder();
        for (char number : numbers) {
            stb.append(number);
        }
        return Integer.parseInt(stb.toString());
    }

    public int getDay(String date) {
        char[] dayChars = {dateToChars(date)[0], dateToChars(date)[1]};
        return charsToInt(dayChars);
    }

    public int getMonth(String date) {
        char[] monthChars = {dateToChars(date)[3], dateToChars(date)[4]};
        return charsToInt(monthChars);
    }

    public int getYear(String date) {
        char[] yearChars = {dateToChars(date)[6],
            dateToChars(date)[7],
            dateToChars(date)[8],
            dateToChars(date)[9]};
        return charsToInt(yearChars);
    }

    public int getDayNumber(String date) {
        int month = getMonth(date);
        int year = getYear(date);
        int yearDays = (year) / 4 * 366 + (year - year / 4) * 365;
        int monthDays;
        switch (month) {
            case 1:
                monthDays = 0;
                break;
            case 2:
                monthDays = 31;
                break;
            case 3:
                monthDays = 31 + 28;
                break;
            case 4:
                monthDays = 31 + 28 + 31;
                break;
            case 5:
                monthDays = 31 + 28 + 31 + 30;
                break;
            case 6:
                monthDays = 31 + 28 + 31 + 30 + 31;
                break;
            case 7:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30;
                break;
            case 8:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30 + 31;
                break;
            case 9:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
                break;
            case 10:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
                break;
            case 11:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
                break;
            case 12:
                monthDays = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
                break;
            default:
                monthDays = 0;
                break;
        }
        
        return getDay(date) + monthDays + yearDays;
    }
}
