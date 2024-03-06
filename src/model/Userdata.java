package model;

public class Userdata {
    private int idUser;
    private String userName;

    public Userdata(int idUser,String userName){
        this.idUser = idUser;
        this.userName = userName;
    }

    public String getUsername(){
        return userName;
    }

    public int getIdUser(){
        return idUser;
    }

}
