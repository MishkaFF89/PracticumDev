package com.github.mishkaff89.practicumdev.javacoretraining.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */
public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
    public class Test {
        int a;
        int b;

        public void printValues() {
            System.out.println(a);
            System.out.println(b);
        }

        public void setValues(int first, int second) {
            a = first;
            b = second;
        }

        public int sum() {
            return a + b;
        }

        public int maxValues() {
            if (a > b) {
                return a;
            } else return b;
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */
    class DynamicArray {
        private ArrayList<Integer> arr = new ArrayList<>();
        private int count;


        public void random() {
            for (int i = 0; i < arr.size(); i++) {
                arr.add((int) (Math.random() * 100));
            }
        }

        public void shuffle() {
            for (int i = 0; i < arr.size(); i++) {
                int r = (int) (Math.random() * arr.size());
                int old = arr.get(i);
                arr.set(i, arr.get(r));
                arr.set(r, old);
            }
        }

        public int getUniqueCount() {
            int count = 0;
            for (int i = 0; i < arr.size() - 1; i++) {
                for (int j = i + 1; j < arr.size(); j++) {
                    if (arr.get(i) == arr.get(j)) {
                        count++;
                    }
                }
            }
            return count;
        }

        public void printArr() {
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(arr.get(i));
            }
        }
    }

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */
    class Triangle {
        int a;
        int b;
        int c;

        float ab;
        float ac;
        float bc;

        Triangle(int a, int b, int c, float ab, float ac, float bc) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.ab = ab;
            this.ac = ac;
            this.bc = bc;
        }

        public double area() {
            double p1 = (double) (a + b + c) / 2;
            return Math.sqrt(p1 * (p1 - a) * (p1 - b) * (p1 - c));
        }

        public int perimeter() {
            return a + b + c;
        }

        public double median() {
            return 0.5 * (Math.sqrt(2 * (b * b + c * c) - a * a));
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */
    class Time {
        int hour;
        int minutes;
        int second;

        public void setTime(int hour, int minutes, int second) {
            setHour(hour);
            setMinutes(minutes);
            setSecond(second);
        }

        public void setHour(int hour) {
            if (hour >= 0 && hour < 24) {
                this.hour = hour;
            }
        }

        public void setMinutes(int minutes) {
            if (minutes >= 0 && minutes < 60) {
                this.minutes = minutes;
            }
        }

        public void setSecond(int second) {
            if (second >= 0 && second < 60) {
                this.second = second;
            }
        }

        public void changeTime(int hour, int minutes, int second) {
            int h = this.hour + hour;
            int m = this.minutes + minutes;
            int s = this.second + second;

            if (s >= 60) {
                this.second = s - 60;
                m++;
            } else {
                this.second = s;
            }
            if (m >= 60) {
                this.minutes = m - 60;
                h++;
            } else {
                this.minutes = m;
            }
            if (h >= 24) {
                this.hour = h - 24;
            } else {
                this.hour = h;
            }
        }
    }

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */
    class Caller {
        private int idNumber;
        private String lastName;
        private String firstName;
        private String patronymic;
        private String address;
        private int numberCard;
        private int debit;
        private int credit;
        private int timeInterCityCalls;
        private int timeCityCalls;

        public Caller(int idNumber, String lastName, String firstName, String patronymic, String address, int numberCard,
                      int debit, int credit, int timeInterCityCalls, int timeCityCalls) {
            this.idNumber = idNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.patronymic = patronymic;
            this.address = address;
            this.numberCard = numberCard;
            this.debit = debit;
            this.credit = credit;
            this.timeInterCityCalls = timeInterCityCalls;
            this.timeCityCalls = timeCityCalls;
        }

        public void setIdNumber(int idNumber) {
            this.idNumber = idNumber;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setNumberCard(int numberCard) {
            this.numberCard = numberCard;
        }

        public void setDebit(int debit) {
            this.debit = debit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public void setTimeInterCityCalls(int timeInterCityCalls) {
            this.timeInterCityCalls = timeInterCityCalls;
        }

        public void setTimeCityCalls(int timeCityCalls) {
            this.timeCityCalls = timeCityCalls;
        }

        public int getIdNumber() {
            return idNumber;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public String getAddress() {
            return address;
        }

        public int getNumberCard() {
            return numberCard;
        }

        public int getDebit() {
            return debit;
        }

        public int getCredit() {
            return credit;
        }

        public int getTimeInterCityCalls() {
            return timeInterCityCalls;
        }

        public int getTimeCityCalls() {
            return timeCityCalls;
        }

        public void printCaller() {
            String result = "";
            result += "Id number: " + idNumber + "\n" +
                    "Last name: " + lastName + "\n" +
                    "First name: " + firstName + "\n" +
                    "Patronymic: " + patronymic + "\n" +
                    "Address: " + address + "\n" +
                    "Number card: " + numberCard + "\n" +
                    "Debit: " + debit + "\n" +
                    "Credit: " + credit + "\n" +
                    "Time inter city calls: " + timeInterCityCalls + "\n" +
                    "Time city calls: " + timeCityCalls;
            System.out.println(result);
        }

    }

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */
    class Student {
        Faculty faculty;
        int grade;
        private double average = 0.0;
        private int count = 0;
        private int sumGrade = 0;

        public void registerOnFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

        public void takeExams(Teacher teacher) {
            teacher.setGrade(this);
            count++;
            sumGrade += teacher.setGrade(this);
            average = sumGrade / count;
        }
    }

    class Teacher {
        public int setGrade(Student student) {

            return (int) (Math.random() * 100);
        }
    }

    class Faculty {
        public boolean enrollment(Student student) {
            return student.average >= 60;
        }
    }

    class Exam {
        Student student = new Student();
        Faculty faculty = new Faculty();
        Teacher teacher = new Teacher();
    }


    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */

    class OnlineStore {
        Seller seller = new Seller();
        List<String> list = new ArrayList<String>();
        List<Client> blackList = new ArrayList<Client>();
    }

    class Seller {

        public void addInfo(OnlineStore onlineStore) {
            onlineStore.list.add("Coffee");
        }

        public boolean regSell(Client client, String str, OnlineStore onlineStore) {
            Boolean payment = client.buyProduct(str);
            if (!payment) {
                onlineStore.blackList.add(client);
            }
            return payment;
        }
    }

    class Client {
        public boolean buyProduct(String str) {
            return true;
        }
    }
}
