package uk.co.mettle.backendtest.model;

import javax.persistence.*;

@Entity
@Table(name="user_feature")
public class UserFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO didn't bother with constraints for simplicity

    @Column(name = "feature_id")
    private long featureId;

    @Column(name = "user_id")
    private long userId;

    public UserFeature() {}

    public UserFeature(long featureId, long userId) {
        this.featureId = featureId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(long featureId) {
        this.featureId = featureId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
