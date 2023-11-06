package br.edu.ifsp.inventariodoo.domain.entities.user;

public class Place extends User{
    private String registrationId;
    private String name;
    private String email;
    private String phone;

    public Place() {
    }
    //Sem construtor sem registrationId porque a pessoa precisa ter vínculo com a universidade
    public Place(String registrationId, String name, String email, String phone) {
        this.registrationId = registrationId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "registrationId='" + registrationId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
