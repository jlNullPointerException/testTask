package api.randomuser.testData;

public enum Params {
    GENDER_KEY("gender"),
    NATION_KEY("nat"),
    RESULT_KEY("results")
    ;

    private final String displayName;

    Params(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return displayName;
    }
}
