package com.poc.db.nosql.documents;

public class Score implements Comparable<Score> {
    private String type;
    private Double score;

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Double getScore() {
	return score;
    }

    public void setScore(Double score) {
	this.score = score;
    }

    @Override
    public int compareTo(Score score) {
	return (this.score < score.getScore()) ? -1 : (this.score > score
		.getScore()) ? 1 : 0;
    }

}
