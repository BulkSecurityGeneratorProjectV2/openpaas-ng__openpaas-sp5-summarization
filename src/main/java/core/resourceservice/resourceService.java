package core.resourceservice;

import core.queryexpansion.BabelExpander;
import core.queryexpansion.QueryExpander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by midas on 12/29/2016.
 */
public class resourceService {
    private List<String> queries;
    private String text;
    private String language;
    private final Set<String> supportedLanguages = new HashSet<>(Arrays.asList("en", "fr"));
    private List<String> expandedQueries;

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        if (!supportedLanguages.contains(language)) {
            return "fr";
        } else {
            return language;
        }
    }

    private void expandQueries() {
        QueryExpander qe = new BabelExpander(text, language);
        List<String> queries = getQueries();

        List<String> result = qe.expandQueries(getText(), queries, getLanguage());
        if (result.isEmpty()) {
            result.addAll(queries);
        }
        this.expandedQueries = result;
    }

    public void setOptions(List<String> queries, String text, String language) {
        this.queries = queries;
        this.text = text;
        this.language = language;
    }

    List<String> getExpandedQueries() {
        if (expandedQueries == null) {
            expandQueries();
        }
        return expandedQueries;
    }

    String callAPI(InputStream stream) {
        String output = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            // Question q = new Gson().fromJson(in, Question.class);
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            output = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
