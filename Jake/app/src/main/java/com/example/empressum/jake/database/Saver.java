package com.example.empressum.jake.database;

/**
 * Created by empressum on 11/8/16.
 */
public class Saver {
    private String name, pass,address,phone;

    public Saver(){
    }
    public Saver(String name,String pass,String address,String phone){
        this.name=name;
        this.pass=pass;
        this.address=address;
        this.phone=phone;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
