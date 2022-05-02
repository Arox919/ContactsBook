package contacts;

import java.util.Scanner;

public class Organization extends Contact {
    String name;
    String address;

    @Override
    String[] abbleToChange() {
        String string[] = {"name", "address", "phonenumber"};
        return string;
    }

    @Override
    void changeValue(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "address":
                setAddress(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }
    }

    String ValueOfField(String field) {
        switch (field) {
            case "name":
                return getName();
            case "address":
                return getAddress();
            case "number":
                return getPhoneNumber();
            default: return "";
        }
    }

    public Organization(boolean person)
    {
        super(person);
    }

    public Organization setOrganization() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the organization name: ");
        setName(scanner.nextLine());
        System.out.println("Enter the address: ");
        setAddress(scanner.nextLine());
        System.out.println("Enter the number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.println("A record created!");
        setTimeLastEdit();
        setTimeCreated();
        return this;
    }

    @Override
    void basicInfo(){
        System.out.println(name);
    }

    @Override
    void fullInfo()
    {
        System.out.println("Organization name: "+name);
        System.out.println("Address: "+address);
        System.out.println("Number: "+phoneNumber);
        System.out.println("Time created: "+ timeCreated);
        System.out.println("Time last edit: "+ timeLastEdit);   
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }
}
