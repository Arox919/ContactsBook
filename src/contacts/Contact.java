package contacts;


import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact {
    String phoneNumber;
    LocalDateTime timeCreated;
    LocalDateTime timeLastEdit;
    final Boolean isPerson;


    abstract String[] abbleToChange();
    abstract void changeValue(String field, String value);
    abstract String ValueOfField(String field);
    abstract void fullInfo();
    abstract void basicInfo();

    public Contact(boolean person) {
        if(person == true)
            isPerson = true;
        else
            isPerson = false;
    }


    public void setTimeCreated()
    {
        this.timeCreated = LocalDateTime.now();
    }

    public void setTimeLastEdit()
    {
        this.timeLastEdit = LocalDateTime.now();
    }

    public void setTimeLastEdit(LocalDateTime datetime)
    {
        this.timeLastEdit = datetime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (isValidMobileNo(phoneNumber))
        {
            this.phoneNumber = phoneNumber;
            return true;
        }
        else
        {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
            return false;
        }
    }

    public  boolean hasNumber() {
        if(phoneNumber == null)
            return false;
        else
            return true;
    }

    public static boolean isValidMobileNo(String number)
    {
        String[] groups = number.split("\\s+|-");

        if(groups.length == 0) return false;

        if(!groups[0].matches("\\+?(\\p{Alnum}+|\\(\\p{Alnum}+\\))")) {
            return false;
        }

        int numOfParenthGroup = 0;
        if(groups[0].matches("\\+?\\(\\p{Alnum}+\\)")) {
            numOfParenthGroup++;
        }


        Pattern pattern = Pattern.compile("\\p{Alnum}{2,}");
        Pattern patternParenthesis = Pattern.compile("\\(\\p{Alnum}{2,}\\)");
        for(int i = 1; i < groups.length; i++) {
            Matcher matcherP = patternParenthesis.matcher(groups[i]);
            Matcher matcher = pattern.matcher(groups[i]);

            if(matcherP.matches()) {
                numOfParenthGroup++;
                if(i > 1) return false;
                if(numOfParenthGroup > 1) return false;
            }
            else if(!matcher.matches()) {
                return false;
            }
        }
        return true;
    }
}
