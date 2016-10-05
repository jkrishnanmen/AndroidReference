package com.example.empressum.jake.database;

/**
 * Created by empressum on 10/8/16.
 */
public class UserSpace {
    private int _id;
    private String name ="gvgv";
    private String pass="tfftfv";
    private String addr="tuftft";
    private String ph="fyfytfytfty";

    public UserSpace() {
    }

    public UserSpace(String name, String pass,String addr,String ph) {
        this.name = name;
        this.pass = pass;
        this.addr=addr;
        this.ph=ph;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPass(String pass) {

        this.pass = pass;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public int get_id() {

        return _id;
    }

    public String getName() {

        return name;
    }

    public String getPass() {

        return pass;
    }

    public String getAddr() {
        return addr;
    }

    public String getPh() {
        return ph;
    }
}
