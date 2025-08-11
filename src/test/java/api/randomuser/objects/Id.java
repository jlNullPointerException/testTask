package api.randomuser.objects;

public class Id {
    private String name;
    private String value;

    public Id() {
    }

    public Id(String date, String age) {
        this.name = date;
        this.value = age;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
