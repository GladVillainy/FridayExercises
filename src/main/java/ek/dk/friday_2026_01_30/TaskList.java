package ek.dk.friday_2026_01_30;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class TaskList<T extends Task> implements  Iterable<T> {
    Collection<T> tasks = new ArrayList<>();

    public void addTask(T task){
        tasks.add(task);
    }

    public void filterTaskByKeywords(String keyWord){
  
    }

    public void sortTaskByDueDate(){

    }

    public void dueToday(){

    }

    public void overdue(){

    }


    public void printAllTask(){

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
