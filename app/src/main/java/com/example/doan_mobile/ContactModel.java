package com.example.doan_mobile;

public class ContactModel implements Comparable<ContactModel> {
    public String name;
    public String phonenumber;
    public int id;
    public int sex;
//    public boolean isChecked;
//    private  boolean active;

    public ContactModel (String name,String phonenumber,int sex,int id){
        this.name = name;
        this.phonenumber = phonenumber;
        this.sex = sex;
        this.id = id;
    }
    public ContactModel (String name,String phonenumber,int sex){
        this.name = name;
        this.phonenumber = phonenumber;
        this.sex = sex;
    }
//    public boolean isActive() {
//        return true;
//    }
//    public boolean isChecked() {
//        return true;
//    }
//    public void setActive(boolean active) {
//        this.active = active;
//    }
////
//    public void setChecked(boolean checked) {
//        isChecked=checked;
//    }
@Override
public String toString() {
    return "Contact{" +
            "name='" + name + '\'' +
            ", phone='" + phonenumber + '\'' +
            ", sex='" + sex + '\'' +
            '}';
}
    @Override
    public int compareTo(ContactModel contactModel) {
        return name.compareToIgnoreCase(contactModel.name);
    }
}

