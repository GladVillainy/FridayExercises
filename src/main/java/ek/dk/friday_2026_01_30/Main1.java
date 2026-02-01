package ek.dk.friday_2026_01_30;

import java.time.LocalDate;

public class Main1 {
    public static void main(String[] args) {

        Task tk;
        GardenTask gt;
        TaskList tl = new TaskList();


        Task task1 = new Task(
                "Clean room",
                "Remove clothing off the ground",
                LocalDate.of(2026, 2, 19)
        );
        Task task2 = new Task(
                "Go out with Trash",
                "You need to take the trashcan out in the kitchen",
                LocalDate.of(2026,2,1)
        );

        Task task3 = new Task(
                "Go for a run",
                "Run 5km around the block",
                LocalDate.of(2026,1,20)
        );

        Task task4 = new GardenTask(
                "Weed the garden",
                "Remove weeds from in the outside concrete tiles",
                LocalDate.of(2026, 2, 19),
                "Backyard"
        );

        Task task5 = new GardenTask(
                "Mow the lawn",
                "Mow the lawn so the grass isn't so long",
                LocalDate.of(2026,2,1),
                "Frontyard"
        );

        Task task6 = new GardenTask(
                "Trim the hedge",
                "Take the hedge shears and trim it",
                LocalDate.of(2026,1, 9),
                "Backyard"
        );

        tl.addTask(task1);
        tl.addTask(task2);
        tl.addTask(task3);
        tl.addTask(task4);
        tl.addTask(task5);
        tl.addTask(task6);


     // tl.dueToday(); // Passed

     // tl.filterTaskByKeywords("Go for a run"); //Passed

     // tl.overdue(); //Passed

     // tl.printAllTask(); //Passed

     // tl.sortTaskByDueDate(); // passed
    }
}
