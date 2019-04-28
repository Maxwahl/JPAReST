package at.htl.TestManagementReloaded.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;
@Entity
public class Teacher extends Person{

    private String persNr;

    private String ownClass;
    @OneToMany(mappedBy = "holder",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JsonbTransient
    private Set<Test> tests;
    public Teacher(String firstName, String lastName, String persNr, String ownClass) {
        super(firstName, lastName);
        this.persNr = persNr;
        this.ownClass = ownClass;
    }

    public Teacher() {
    }

    public String getPersNr() {
        return persNr;
    }

    public void setPersNr(String persNr) {
        this.persNr = persNr;
    }

    public String getOwnClass() {
        return ownClass;
    }

    public void setOwnClass(String ownClass) {
        this.ownClass = ownClass;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
