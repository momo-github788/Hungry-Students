package za.ac.cput.domain;

import android.telephony.mbms.StreamingServiceInfo;

public class PointsHistory {

    private String title;

    private String transactionId;

    private String description;

    private int points;

    private PointsHistory(){
    }

    private PointsHistory(Builder builder){
        this.title = builder.title;
        this.transactionId = builder.transactionId;
        this.description = builder.description;
        this.points = builder.points;
    }

    public PointsHistory(String title, String transactionId, String description, int points){
        this.title = title;
        this.transactionId = transactionId;
        this.description = description;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }


    @Override
    public String toString() {
        return "PointsHistory{" +
                "title='" + title + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                '}';
    }

    public static class Builder {
        private String title;
        private String transactionId;
        private String description;
        private int points;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPoints(int points) {
            this.points = points;
            return this;
        }

        public PointsHistory build(){return new PointsHistory(this);}
    }
}
