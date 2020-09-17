package ru.javalab.socketsshop.database.ORM;

public class Buy {
    private int idWho, idWhat;

    public Buy(int idWho, int idWhat) {
        this.idWho = idWho;
        this.idWhat = idWhat;
    }

    public int getIdWho() {
        return idWho;
    }

    public void setIdWho(int idWho) {
        this.idWho = idWho;
    }

    public int getIdWhat() {
        return idWhat;
    }

    public void setIdWhat(int idWhat) {
        this.idWhat = idWhat;
    }
}
