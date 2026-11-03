package com.example.zebra2;

public class pytanie {
    private long idZdjecia;
    private String trescPytania;
    private boolean poprawnaodpowiedz;

    private boolean czyPoprawnie;

    public pytanie(long idZdjecia, String trescPytania, boolean poprawnaodpowiedz) {
        this.idZdjecia = idZdjecia;
        this.trescPytania = trescPytania;
        this.poprawnaodpowiedz = poprawnaodpowiedz;
        this.czyPoprawnie = false;
    }

    public long getIdZdjecia() {
        return idZdjecia;
    }

    public String getTrescPytania() {
        return trescPytania;
    }

    public boolean isPoprawnaodpowiedz() {
        return poprawnaodpowiedz;
    }

    public boolean isCzyPoprawnie() {
        return czyPoprawnie;
    }

    public void setCzyPoprawnie(boolean czyPoprawnie) {
        this.czyPoprawnie = czyPoprawnie;
    }

}
