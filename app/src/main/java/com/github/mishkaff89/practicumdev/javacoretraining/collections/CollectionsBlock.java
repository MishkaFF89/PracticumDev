package com.github.mishkaff89.practicumdev.javacoretraining.collections;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */

public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        int sizeShort = Math.min(firstList.size(), secondList.size());
        List<T> newList = new ArrayList<>();
        int countJ = 0;
        int countK = 0;
        for (int i = 0; i < sizeShort; i++) {
            if (firstList.get(countJ).compareTo(secondList.get(countK)) == 1) {
                newList.add(secondList.get(countK));
                countK++;
            } else {
                newList.add(firstList.get(countJ));
                countJ++;
            }
        }
        if (countJ == firstList.size() - 1) {
            for (int i = countK; i < secondList.size(); i++) {
                newList.add(secondList.get(i));
            }
        } else {
            for (int i = countJ; i < firstList.size(); i++) {
                newList.add(firstList.get(i));
            }
        }
        return newList;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                result.add(inputList.get(j));
            }
            result.add(inputList.get(i));

        }
        return result;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList.size() < secondList.size()) {
            for (int i = 0; i < firstList.size(); i++) {
                boolean contained = false;
                for (int j = 0; j < secondList.size(); j++) {
                    if (firstList.get(i) == secondList.get(j)) {
                        contained = true;
                        break;
                    }
                }
                if (!contained) return false;
            }
            return true;
        } else {
            for (int i = 0; i < secondList.size(); i++) {
                boolean contained = false;
                for (int j = 0; j < firstList.size(); j++) {
                    if (firstList.get(i) == secondList.get(j)) {
                        contained = true;
                        break;
                    }
                }
                if (!contained) return false;
            }
            return true;
        }
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        int shift = n % inputList.size();
        if (shift > 0) {
            for (int i = 0; i < shift; i++) {
                T buffer = inputList.get(0);
                inputList.set(0, inputList.get(inputList.size() - 1));
                for (int j = 1; j < inputList.size(); j++) {
                    inputList.set(inputList.size() - j, inputList.get(inputList.size() - j - 1));
                }
                inputList.set(1, buffer);
            }
        } else {
            shift = -shift;
            for (int i = 0; i < shift; i++) {
                T buffer = inputList.get(inputList.size() - 1);
                inputList.set(inputList.size() - 1, inputList.get(0));
                for (int j = 1; j < inputList.size(); j++) {
                    inputList.set(j - 1, inputList.get(j));
                }
                inputList.set(inputList.size() - 2, buffer);
            }
        }
        return inputList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if (a == null || b == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).contains(a)) {
                inputList.set(i, inputList.get(i).replaceAll(a, b));
            }
        }
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    class Student {

        String firstName;
        String secondName;
        String thirdName;
        String birthDay;
        int course;
        int groupId;
        List<Integer> grades;

        public Student(String firstName, String secondName, String thirdName, String birthDay, int course, int groupId, List<Integer> assissments) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.thirdName = thirdName;
            this.birthDay = birthDay;
            this.course = course;
            this.groupId = groupId;
            this.grades = assissments;
        }

    }

    class StudentTest {
        ArrayList<Student> students;

        public StudentTest() {
            students = new ArrayList<>();
            students.addAll(Arrays.asList(
                    new Student("Test1", "Test1", "Test1", "02.04.2004", 1, 11010, Arrays.asList(5, 5, 5, 5, 5)),
                    new Student("Test2", "Test2", "Test2", "23.08.2006", 2, 11010, Arrays.asList(3, 4, 5, 3, 4)),
                    new Student("Test3", "Test3", "Test3", "21.04.2007", 3, 11010, Arrays.asList(4, 5, 4, 5, 3)),
                    new Student("Test4", "Test4", "Test4", "12.02.2005", 4, 11010, Arrays.asList(4, 5, 3, 4, 4)),
                    new Student("Test5", "Test5", "Test5", "08.09.2003", 3, 11010, Arrays.asList(4, 3, 4, 4, 4)))
            );
        }

        public void sortByName() {
            students.sort((a, b) -> a.course > b.course ? 1 : -1);
            students.sort((a, b) -> {
                if (a.course == b.course) {
                    if (a.firstName.compareTo(b.firstName) == 0) {
                        if (a.secondName.compareTo(b.secondName) == 0) {
                            if (a.thirdName.compareTo(b.thirdName) == 0) {
                                return 0;
                            } else return a.secondName.compareTo(b.secondName);
                        } else return a.firstName.compareTo(b.firstName);
                    } else return a.firstName.compareTo(b.firstName);
                } else return 0;
            });
        }

        public Map<Integer, Integer> getGpaByGroup() {
            Map<Integer, List<Integer>> results = new HashMap<>();

            for (int i = 0; i < students.size(); i++) {
                int groupSum = 0;
                for (int j = 0; j < students.size(); j++) {
                    if (!results.containsKey(students.get(j).course)) {
                        results.put(students.get(j).course, students.get(j).grades);
                    } else {
                        List<Integer> currentResults = results.get(students.get(j).course);
                        ArrayList<Integer> newResults = new ArrayList();
                        for (int k = 0; k < currentResults.size(); k++) {
                            newResults.add(currentResults.get(k) + students.get(j).grades.get(k));
                        }
                        results.put(students.get(j).course, newResults);
                    }
                }
            }

            Map<Integer, Integer> gpaByCourse = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> course : results.entrySet()) {
                int sum = 0;
                for (int i = 0; i < course.getValue().size(); i++) {
                    sum += course.getValue().get(i);
                }
                gpaByCourse.put(course.getKey(), sum / course.getValue().size());
            }

            return gpaByCourse;
        }

        public Student getOldest() {
            students.sort((a, b) -> a.birthDay.compareTo(b.birthDay));
            return students.get(students.size() - 1);
        }

        public Student getYoungest() {
            students.sort((a, b) -> a.birthDay.compareTo(b.birthDay));
            return students.get(0);
        }

        public Map<Integer, Student> getBestByGroup() {
            Map<Integer, Student> results = new HashMap<>();

            for (int i = 0; i < students.size(); i++) {
                int groupSum = 0;
                for (int j = 0; j < students.size(); j++) {
                    if (!results.containsKey(students.get(j).course)) {
                        results.put(students.get(j).course, students.get(j));
                    } else {
                        int sumCurrent = 0;
                        int sumNew = 0;
                        for (int k = 0; k < students.get(j).grades.size(); k++) {
                            sumNew += students.get(j).grades.get(k);
                            sumCurrent += results.get(students.get(j).course).grades.get(k);
                        }
                        if (sumNew / students.get(j).grades.size() > sumCurrent / results.get(students.get(j).course).grades.size()) {
                            results.put(students.get(j).course, students.get(j));
                        }
                    }
                }
            }

            return results;
        }
    }
}
