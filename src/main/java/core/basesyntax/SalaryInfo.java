package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     *
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     *
     * names:
     * Сергей
     * Андрей
     * София
     *
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     *
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String inform = "";
        String[] string;

        try {
            if (parseDate(dateFrom).after(parseDate(dateTo))) {
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < names.length; x++) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                string = data[i].split(" ");
                try {
                    if (parseDate(string[0]).after(parseDate(dateTo))) {
                        break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (names[x].equals(string[1])) {
                    salary += Integer.parseInt(string[2]) * Integer.parseInt(string[3]);
                }
            }
            inform += names[x] + " - " + salary + "\n";
        }

        return  "Отчёт за период " + dateFrom + " - " + dateTo + "\n" + inform;
    }

    private Date parseDate(String string) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        return dateFormat.parse(string);
    }

}
