package Objects;

public class User {
    private int id;
    private String name;
    private String egn;
    private String gsm;
    private String description;
    private String adress;

    public User(int id, String name, String egn, String gsm, String description, String adress) {
        this.id = id;
        this.name = name;
        this.egn = egn;
        this.gsm = gsm;
        this.description = description;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
