package info.victorchu.jlb.data;

import lombok.Data;

import java.util.List;

@Data
public class Row {
    public String _id;
    public int index;
    public String guid;
    public boolean isActive;
    public String balance;
    public String picture;
    public int age;
    public String eyeColor;
    public String name;
    public String gender;
    public String company;
    public String email;
    public String phone;
    public String address;
    public String about;
    public String registered;
    public double latitude;
    public double longitude;
    public List<String> tags;
    public String greeting;
    public String favoriteFruit;
}
