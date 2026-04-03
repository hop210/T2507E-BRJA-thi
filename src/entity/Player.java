package entity;

public class Player {
    private Integer playerId;
    private Integer nationalId;
    private String playerName;
    private Integer highScore;
    private Integer level;
    private String nationalName;

    public Player() {
    }

    public Player(Integer highScore, Integer level, Integer nationalId, String nationalName, Integer playerId, String playerName) {
        this.highScore = highScore;
        this.level = level;
        this.nationalId = nationalId;
        this.nationalName = nationalName;
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
