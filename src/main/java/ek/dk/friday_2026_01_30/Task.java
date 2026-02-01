package ek.dk.friday_2026_01_30;

import java.time.LocalDate;

public class Task {
    String title;
    String description;
    LocalDate duedate;

    public Task(String title, String description, LocalDate duedate) {
        this.title = title;
        this.description = description;
        this.duedate = duedate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return
                "Task\n" +
                        "  Title       : " + title + "\n" +
                        "  Description : %s%n" + description + "\n" +
                        "  Due date    : %s" + duedate + "\n";
    }
}
