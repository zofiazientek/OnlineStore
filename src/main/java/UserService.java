import java.util.ArrayList;
import java.util.List;

public class UserService
{
    private IDataStore<User> dataStore;

    public void setDataStoreDataStore(IDataStore<User> dataStore)
    {
        this.dataStore = dataStore;
    }

    public void createUser(String firstName, String lastName, String eMail)
    {
        if (!validate(firstName,lastName,eMail))
            throw new IllegalArgumentException();
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(firstName);
        newUser.setEMail(eMail);
        dataStore.createItem(newUser);
    }

    public List<User> getAllUsers()
    {
        return (List<User>)dataStore.getAllItems();
    }

    public void updateUser(User user)
    {
        if (!validate(user.getFirstName(),user.getLastName(),user.getEMail()))
            throw new IllegalArgumentException();
        dataStore.updateItem(user);
    }

    public void deleteUser(User user)
    {
        dataStore.deleteItem(user);
    }

    List<User> findUserByFirstName(String firstName)
    {
        List<User> result = new ArrayList<>();
        for(User user : dataStore.getAllItems())
        {
            if (user.getFirstName().equals(firstName))
                result.add(user);
        }
        return result;
    }

    List<User> findUserByLastName(String lastName)
    {
        List<User> result = new ArrayList<>();
        for(User user : dataStore.getAllItems())
        {
            if (user.getLastName().equals(lastName))
                result.add(user);
        }
        return result;
    }

    List<User> findUserByEMail(String eMail)
    {
        List<User> result = new ArrayList<>();
        for(User user : dataStore.getAllItems())
        {
            if (user.getEMail().equals(eMail))
                result.add(user);
        }
        return result;
    }

    private boolean validate(String firstName, String lastName, String eMail)
    {
        boolean correct = true;
        if (firstName.length() > 30 || firstName.length() == 0)
            correct = false;
        if (lastName.length() > 30 || lastName.length() == 0)
            correct = false;
        if (eMail.length() > 100 || eMail.length() == 0)
            correct = false;
        if (!firstName.matches("([a-z]|[A-Z]|[ĄąĆćĘęŁłŃńÓóŚśŹźŻż]| |-)+"))
            correct = false;
        if (!lastName.matches("([a-z]|[A-Z]|[ĄąĆćĘęŁłŃńÓóŚśŹźŻż]| |-)+"))
            correct = false;
        if (!eMail.matches("^[a-zA-Z0-9_]+[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]*@[a-zA-Z0-9.-]+[.][a-zA-Z0-9]+$"))
            correct = false;
        return correct;
    }
}