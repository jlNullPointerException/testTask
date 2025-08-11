package api.randomuser.objects;

import java.util.Date;

public class Dob {
    private Date date;
    private Integer age;

    public Dob() {
    }

    public Dob(Date date, Integer age) {
        this.date = date;
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public Integer getAge() {
        return age;
    }
}
