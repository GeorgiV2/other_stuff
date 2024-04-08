package Objects;

import java.sql.Date;

public class Rent {
    private int id_media;
    private int id_user;
    private String nameUser;
    private Date date;

    public Rent(int id_media, int id_user, String nameUser, Date date) {
        this.id_media = id_media;
        this.id_user = id_user;
        this.nameUser = nameUser;
        this.date = date;
    }

    public int getId_media() {
        return id_media;
    }

    public void setId_media(int id_media) {
        this.id_media = id_media;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
