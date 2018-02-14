package hello.jsontemplates;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Beaches {
    @JsonProperty("beach")
    private List<String> beach;

    public Beaches() {
        this.beach = new ArrayList<>();
    }

    public Beaches(List<String> beach) {
        this.beach = beach;
    }

    public List<String> getBeach() {
        return beach;
    }
}
