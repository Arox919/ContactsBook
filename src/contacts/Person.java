package contacts;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Person extends Contact {
    String name;
    String surname;
    LocalDate birthDate;
    String gender;

    public Person(boolean person) {
        super(person);
    }


    @Override
    String[] abbleToChange() {
        String string[] = {"name", "surname","birthDate", "gender", "phoneNumber"};
        return string;
    }

    @Override
    void changeValue(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirthDate(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }
    }

    @Override
    String ValueOfField(String field) {
        switch ( field ) {
            case "name":
                return getName();
            case "surname":
                return getSurname();
            case "birth":
                return birthDate.toString();
            case "gender":
                return getGender();
            case "number":
                return getPhoneNumber();
            default:
                return "";
        }
    }

    public void printBirthDate() {
        if(birthDate == null)
            System.out.println("[no data]");
        else
            System.out.println(birthDate);
    }

    public void printGender() {
        if(gender == null)
            System.out.println("[no data]");
        else
            System.out.println(gender);
    }

    public Person setPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the person:");
        setName(scanner.nextLine());
        System.out.println("Enter the surname of the person:");
        setSurname(scanner.nextLine());
        System.out.println("Enter the birth date: ");
        setBirthDate(scanner.nextLine());
        System.out.println("Enter the gender(M,F): ");
        setGender(scanner.nextLine());
        System.out.println("Enter the number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.println("A record created!");
        setTimeLastEdit();
        setTimeCreated();
        return this;
    }

    @Override
    public void basicInfo(){
        System.out.println(name+" "+surname);
    }

    @Override
    public void fullInfo()
    {
        System.out.println("Name: "+name);
        System.out.println("Surname: "+surname);
        System.out.print("Birth date: ");
        printBirthDate();
        System.out.print("Gender: ");
        printGender();
        System.out.println("Number: "+phoneNumber);
        System.out.println("Time created: "+ timeCreated);
        System.out.println("Time last edit: "+ timeLastEdit);
    }





    public boolean isValidGender(String gender)
    {
        if(gender.equals("M")||gender.equals("F"))
            return true;
        else
            return false;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender) {
        if(isValidGender(gender))
            this.gender = gender;
        else
            System.out.println("Bad gender!");
    }

    public static boolean isValidBirthDate(String date)
    {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public void setBirthDate(String birthDate)
    {
        if(isValidBirthDate(birthDate))
            this.birthDate = LocalDate.parse(birthDate);
        else
            System.out.println("Bad birth date!");
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}



