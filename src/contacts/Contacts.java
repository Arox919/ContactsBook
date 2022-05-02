package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;



public class Contacts{
    List<Contact> contactsBook = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    public void getBasicInfo()
    {
        for (int i = 0; i < contactsBook.size(); i++)
        {
            System.out.print(i + 1 + ". ");
            if (contactsBook.get(i).isPerson) {
                Person p = (Person) contactsBook.get(i);
                p.basicInfo();
            } else {
                Organization o = (Organization) contactsBook.get(i);
                o.basicInfo();
            }
        }
    }

    public void list()
    {
        for (int i = 0; i < contactsBook.size(); i++) {
            System.out.print(i+1+". ");
            contactsBook.get(i).basicInfo();
        }
    }

    public void getInfo()
    {
        for (int i = 0; i < contactsBook.size(); i++) {
            getBasicInfo();

            System.out.println("Enter index to show info: ");

            int index;
            try{
                 index = scanner.nextInt() - 1;
            } catch(InputMismatchException e) {
                System.out.println("Wrong field number");
                return;
            }

            if(contactsBook.get(index).isPerson)
            {
                Person p = (Person) contactsBook.get(index);
                p.fullInfo();
            }
            else
            {
                Organization o = (Organization) contactsBook.get(index);
                o.fullInfo();
            }
        }
    }

    public void countContactsBook()
    {
        System.out.println("The Phone Book has "+contactsBook.size()+" records.");
    }

    public void addContact()
    {
        System.out.println("Enter the type (person, organization): ");
        String tmp = scanner.nextLine();

        switch (tmp)
        {
            case "person" : contactsBook.add(new Person(true).setPerson());
            break;
            case "organization" : contactsBook.add(new Organization(false).setOrganization());
            break;
            default : System.out.println("Wrong type name");
                return;
        }
    }

    public void editRecord(int record)
    {
        String field, value;
        if (record > contactsBook.size()) {
            System.out.println("Contact book don't contain record with this number");
            return;
        }

        String fields[] = contactsBook.get(record).abbleToChange();

        System.out.print("Select a field: ( ");
        for (String s : fields) {
            System.out.print(s + " ");
        }
        System.out.print("): ");

        field = scanner.nextLine();

        for (int i = 0; i < fields.length; i++)
        {
            if (field.equals(fields[i]))
                break;
            if(i == fields.length-1)
            {
                System.out.println("Wrong field name!");
                System.out.println("Saved");
                return;
            }
        }
        System.out.print("Enter "+field+": ");
        value = scanner.nextLine();
        contactsBook.get(record).changeValue(field,value);
        System.out.println("Saved");
        contactsBook.get(record).fullInfo();
    }

    public void editContactsBook() {
        if (contactsBook.isEmpty()) {
            System.out.println("No records to edit!");
        }

        int record;
        String field, value;
        getBasicInfo();
        System.out.println("Select record to edit: ");
        try {
            record = scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Wrong field number");
            return;
        }

        if (record > contactsBook.size()) {
            System.out.println("Contact book don't contain record with this number");
            return;
        }

        String fields[] = contactsBook.get(record).abbleToChange();

        System.out.print("Select a field: ( ");
        for (String s : fields) {
            System.out.print(s + " ");
        }
        System.out.println("): ");

        field = scanner.nextLine();

        for (int i = 0; i < fields.length; i++)
        {
            if (field.equals(fields[i]))
                break;
            if(i == fields.length-1)
            {
                System.out.println("Wrong field name!");
                System.out.println("Saved");
                return;
            }
        }
        System.out.println("Enter "+field+": ");
        value = scanner.nextLine();
        contactsBook.get(record).changeValue(field,value);
        System.out.println("Saved");
        contactsBook.get(record).fullInfo();
    }



    public void removeContact() {
        if (contactsBook.isEmpty())
        {
            System.out.println("No records to remove!");
            return;
        }

        getBasicInfo();
        int record;

        System.out.println("Select record to remove: ");
        try{
            record = scanner.nextInt() - 1;
        } catch(InputMismatchException e) {
            System.out.println("Wrong field number");
            return;
        }

        try {
            contactsBook.remove(record);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Wrong record number");
        }
        System.out.println(("the record removed!"));
    }

    public void printFounded(List <Integer> recordsIndex)
    {
        int counter = 1;
        System.out.println("Found "+ recordsIndex.size() +" result:");
        for(Integer i : recordsIndex)
        {
            System.out.print(counter+". ");
            contactsBook.get(i).basicInfo();
            counter++;
        }
    }

    public List<Integer> search()
    {
        System.out.println("Enter search query: ");
        String query = scanner.nextLine();
        List<Integer> foundedRecords = searchForRecordsByQuery(query);
        printFounded(foundedRecords);
        return foundedRecords;
    }


    public List<Integer> searchForRecordsByQuery(String query)
    {
        List <Integer> recordsIndex = new ArrayList<>();
        int counter = 0;
        List <String> str = new ArrayList<>();
        String[] splited;
        for (int i = 0 ; i < contactsBook.size() ; i++) {
            if(!(contactsBook.get(i).ValueOfField("name").equals(""))) {
                splited = contactsBook.get(i).ValueOfField("name").split(" ");
                for (String s : splited)
                {
                    str.add(s);
                }
            }
            if(!(contactsBook.get(i).ValueOfField("surname").equals("")))
            {
                splited = contactsBook.get(i).ValueOfField("surname").split(" ");
                for(String s : splited)
                {
                    str.add(s);
                }
            }
            if(!(contactsBook.get(i).ValueOfField("number").equals("")))
            {
                splited = contactsBook.get(i).ValueOfField("number").split(" ");
                for(String s : splited)
                {
                    str.add(s);
                }
            }

            for(String w: str)
            {
                String[] c = w.split("");
                String[] q = query.split("");

                for(int j=0;j<=c.length- q.length;j++)
                {
                    for(int h=0;h<q.length;h++)
                    {
                        if(c[h+j].equalsIgnoreCase(q[h])) {
                            counter++;
                        }
                        else
                            counter = 0;

                        if(counter == q.length) {
                            recordsIndex.add(i);
                            counter = 0;
                            break;
                        }
                    }
                }
            }
        str.clear();
        }
        return recordsIndex;
    }

    public void action()
    {
        String action;
        String state = "menu";
        List<Integer> recordsList = null;
        int record = 0;

        while(true) {

            switch(state)
            {
                case "menu": System.out.println();
                    System.out.print("["+state+"] "+"Enter action(add, list, search, count, exit): ");
                    action = scanner.nextLine();
                    switch(action)
                    {
                        case "add" : addContact();
                            break;
                        case "list" : list();
                            state = "list";
                            break;
                        case "search" : state = "search";
                            recordsList = search();
                            break;
                        case "count" : countContactsBook();
                            break;
                        case "exit" : return;
                        default :  return;
                    }
                    break;
                case "search": System.out.println();
                    System.out.print("["+state+"] "+"Enter action([number], back, again): ");
                    action = scanner.nextLine();
                    switch(action)
                    {
                        case "1" : case "2" : case "3" : case "4" : case "5" : case "6" : case "7" : case "8" :  case "9" : case "10" :
                            contactsBook.get(recordsList.get(Integer.parseInt(action)-1)).fullInfo();
                            record = recordsList.get(Integer.parseInt(action)-1);
                            state="record";
                            break;
                        case "back" : state = "menu";
                            break;
                        case "again" :
                            state = "search";
                            search();
                            break;
                        default :  return;
                    }
                    break;
                case "record": System.out.println();
                    System.out.print("["+state+"] "+"Enter action (edit, delete, menu): ");
                    action = scanner.nextLine();
                    switch(action)
                    {
                        case "edit" :
                            editRecord(record-1);
                            state = "record";
                        break;
                        case "delete" : contactsBook.remove(record-1);
                            state = "menu";
                            break;
                        case "menu" :
                            state = "menu";
                            break;
                        default :  return;
                    }
                    break;
                case "list": System.out.println();
                    System.out.print("["+state+"] "+"Enter action([number], back): ");
                    action = scanner.nextLine();
                    switch(action)
                    {
                        case "1" : case "2" : case "3" : case "4" : case "5" : case "6" : case "7" : case "8" :  case "9" : case "10" :
                            contactsBook.get(Integer.parseInt(action)-1).fullInfo();
                            record = Integer.parseInt(action);
                            state = "record";
                            break;
                        case "back" : state = "menu";
                            break;
                        default :  return;
                    }
                    break;
            }
        }

    }


}
