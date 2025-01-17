package structures.resources;

import structures.Keyword;

import java.util.List;

/**
 * Created by midas on 30/12/2016.
 */
public class Resources {
    public List<Keyword> keywords;
    public List<Email> mails;
    public List<GoogleResource> soarticles;
    public List<GoogleResource> wikiarticles;

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<GoogleResource> getSoarticles() {
        return soarticles;
    }

    public void setSoarticles(List<GoogleResource> soarticles) {
        this.soarticles = soarticles;
    }

    public List<GoogleResource> getWikiarticles() {
        return wikiarticles;
    }

    public void setWikiarticles(List<GoogleResource> wikiarticles) {
        this.wikiarticles = wikiarticles;
    }

    public List<Email> getMails() {
        return mails;
    }

    public void setMails(List<Email> mails) {
        this.mails = mails;
    }
}
