package hello.jsontemplates;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Beaches {
    @JsonProperty("beaches")
    private List<String> beaches;

    public Beaches() {
        this.beaches = new ArrayList<>();
    }

    public Beaches(List<String> beach) {
        this.beaches = beaches;
    }

    public List<String> getBeach() {
        return beaches;
    }
}
