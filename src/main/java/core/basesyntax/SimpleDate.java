package core.basesyntax;

public class SimpleDate {
    private final String rawDate; // Зберігає дату у форматі "дд.ММ.рррр"
    private final int day;
    private final int month;
    private final int year;

    public SimpleDate(String date) {
        this.rawDate = date;
        String[] parts = date.split("\\.");
        this.day = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    public static boolean isValidDate(String date) {
        String[] parts = date.split("\\.");
        if (parts.length != 3) {
            return false; // Неправильний формат
        }

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (year < 1 || month < 1 || month > 12 || day < 1) {
                return false;
            }

            // Перевірка кількості днів у місяці
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            return day <= daysInMonth[month - 1];
        } catch (NumberFormatException e) {
            return false; // Якщо числа не вдалося розпарсити
        }

    }

    public boolean isBefore(SimpleDate other) {
        return year < other.year
                || (year == other.year && month < other.month)
                || (year == other.year && month == other.month && day < other.day);
    }

    public boolean isAfter(SimpleDate other) {
        return year > other.year
                || (year == other.year && month > other.month)
                || (year == other.year && month == other.month && day > other.day);
    }

    public boolean isWithin(SimpleDate start, SimpleDate end) {
        return !this.isBefore(start) && !this.isAfter(end);
    }

    public String getRawDate() {
        return rawDate;
    }
}
