package ek.dk.friday_2026_01_30;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;


public class TaskList<T extends Task> implements  Iterable<T> {
    Collection<T> tasks = new ArrayList<>();

    public void addTask(T task){
        tasks.add(task);
    }

    public void filterTaskByKeywords(String keyWord){
        Collection<T> tasksByWords = tasks.stream()
                .filter(tasks -> {
                    if (tasks.getDescription().equals(keyWord)
                            || tasks.getTitle().equals(keyWord)){
                        return true;
                }
                return false;
                })
                .toList();
        if(tasksByWords.isEmpty()){
            System.out.println("The keyword do not match any task");
        } else
            tasksByWords.forEach(System.out::println);
    }

    public void sortTaskByDueDate(){
        List<T> sortByDue = tasks.stream()
                .sorted((t1, t2) -> t1.getDuedate().compareTo(t2.getDuedate()))
                .toList();
        sortByDue.forEach(System.out::println);
    }

    public void dueToday(){
        LocalDate today = LocalDate.now();

        List<T> filterbyDue = tasks.stream()
                .filter(t -> t.getDuedate().equals(today))
                .toList();
        filterbyDue.forEach(System.out::println);
    }

    public void overdue(){
        LocalDate today = LocalDate.now();

        List<T> overdued = tasks.stream()
                .filter(t -> today.isAfter(t.getDuedate()))
                .toList();
        overdued.forEach(System.out::println);

    }

    public void printAllTask(){
        tasks.forEach(System.out::println);
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
