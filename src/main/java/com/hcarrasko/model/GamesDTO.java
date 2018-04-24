package com.hcarrasko.model;

import java.util.List;

public class GamesDTO {

    private List<Games> games;

    public List<Games> getGames() {
        return games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }
}

class Games{
    private String url;
    private long move_by;
    private long last_activity;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getMove_by() {
        return move_by;
    }

    public void setMove_by(long move_by) {
        this.move_by = move_by;
    }

    public long getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(long last_activity) {
        this.last_activity = last_activity;
    }
}
