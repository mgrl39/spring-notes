package net.xeill.elpuig.springbootcrudintro;

public class Hello
{
    int id;
    String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hello(String message) {
        this.message = message;
        id = 1;
    }
}
