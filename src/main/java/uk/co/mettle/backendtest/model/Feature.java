package uk.co.mettle.backendtest.model;

import javax.persistence.*;

@Entity
@Table(name="feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private String enabled;

    @Column(name = "global")
    private String global;

    public Feature() {}

    public Feature(String name, String enabled, String global) {
        this.name = name;
        this.enabled = enabled;
        this.global = global;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getGlobal() {
        return global;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled='" + enabled + '\'' +
                ", global='" + global + '\'' +
                '}';
    }
}
