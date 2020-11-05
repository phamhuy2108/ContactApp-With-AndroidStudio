package com.example.doan_mobile;

public class BeforeAndAfter implements Comparable<BeforeAndAfter> {
    public String phonebefore;
    public String phoneafter;
    public String networkname;

//    public BeforeAndAfter (String name,String phonenumber,int sex,int id){
//        this.name = name;
//        this.phonenumber = phonenumber;
//        this.sex = sex;
//        this.id = id;
//    }
public BeforeAndAfter (){

}
    public BeforeAndAfter (String phonebefore,String phoneafter,String networkname){
        this.phonebefore = phonebefore;
        this.phoneafter = phoneafter;
        this.networkname = networkname;
    }

    @Override
    public String toString() {
        return "From" + " " +
                  phonebefore + " " +
                "To " + phoneafter + " " +
                "(Networkname: " + networkname + " )";
    }

    @Override
    public int compareTo(BeforeAndAfter beforeAndAfter) {
        return phonebefore.compareToIgnoreCase(beforeAndAfter.phonebefore);
    }
}
