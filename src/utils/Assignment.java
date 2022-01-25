package utils;

public class Assignment {
    
    String title;
    String description;
    String dueDate;
    boolean isCompleted;

    public Assignment() {
        this("", "", "", false);
    }

    public Assignment(String title, String description, String dueDate, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

}
