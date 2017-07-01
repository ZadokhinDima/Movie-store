package DBClasses;

import java.time.LocalDate;

/**
 * Created by dimaz on 27.04.2017.
 */
public class Individual {

    private String lastname, firstname, middlename, adress, fullname;
    private long phone, id;
    private LocalDate birthDate;


    public long getId() {
        return id;
    }

    public Individual(int id, String lastname, String firstname, String middlename, String adress, long phone, LocalDate birthDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.adress = adress;
        this.phone = phone;
        this.birthDate = birthDate;
        this.fullname = lastname + " " + firstname + " " + middlename;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
