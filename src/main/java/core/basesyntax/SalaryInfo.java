package core.basesyntax;


import java.lang.ArrayIndexOutOfBoundsException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * names:
     * Сергей
     * Андрей
     * София
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
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        HashMap<String, Integer> salary = new HashMap<String, Integer>(names.length);
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        for (String name : names) {
            salary.put(name, 0);
        }
        try {
            Date end = format.parse(dateTo);
            Date start = format.parse(dateFrom);
            if (start.after(end)) {
                return null;
            }
            for (int i = 0; i < data.length; i++) {
                String[] splitString;
                Date date;
                splitString = data[i].split(" ");
                date = format.parse(splitString[0]);
                if (!date.after(end) && !date.before(start)) {
                    salary.put(splitString[1], salary.get(splitString[1])
                            + Integer.parseInt(splitString[2]) * Integer.parseInt(splitString[3]));
                }
            }
        } catch (ParseException exc1) {
            exc1.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException exc2) {
            exc2.printStackTrace();
        }
        StringBuilder returnString = new StringBuilder("Отчёт за период "
                .concat(dateFrom)
                .concat(" - ")
                .concat(dateTo + '\n'));
        for (int i = 0; i < names.length; i++) {
            returnString.append(names[i] + " - " + salary.get(names[i]) + '\n');
        }
        return returnString.toString();
    }
}
