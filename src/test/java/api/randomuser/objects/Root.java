package api.randomuser.objects;

import java.util.List;

public class Root {
    private List<Results> results;
    private Info info;

    public Root() {
    }

    public Root(List<Results> results, Info info) {
        this.results = results;
        this.info = info;
    }

    public List<Results> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }
}
