package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = sdf.parse(dateFrom);
        Date date2 = sdf.parse(dateTo);
        if (date1.compareTo(date2) > 0) {
            throw new IllegalDateParametersException();
        }
        StringBuilder res = new StringBuilder();
        res.append("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            //result += names[i] + " - ";
            res.append(names[i] + " - ");
            for (String one : data) {
                String[] temp = one.split(" ");
                Date dateSalary = sdf.parse(temp[0]);
                if (dateSalary.compareTo(date1) >= 0 && dateSalary.compareTo(date2) <= 0) {
                    if (temp[1].equals(names[i])) {
                        salary += (Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]));
                    }
                }
            }
            res.append(salary + "\n");
        }
        String answer = res.substring(0,res.length() - 1);
        return answer;
    }
}
