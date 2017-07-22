package Donor;


public class CheckRegistrationData
{
    private CheckRegistrationData()
    {
        // this constructor has been kept private because we don't need to make a object
        // to call its methods
    }

    // this ERROR variable will detect where user has made an error in input data.
    // This value will trace that error and ask user to correct it.
    // If error value is 1 then we'll say that User has caused an Error in Name
    // for ERROR = 2, we'll say that user has made an error in Age
    public static int ERROR = 0;

    // this is an static method
    //it can be called from anywhere Like CheckRegistrationData.dataValidity(....param....);

    public static boolean dataValidity(String name , String age , String phone , String password , String bloodGroup , String location)
    {
        // Integer is a class in Java
        // parseInt method tries to convert a string into int
        // and returns the result.
        int AgeNum = Integer.parseInt(age);

        if(name.isEmpty() || name.contains("Name") || invalidDataCheck.hasNums(name))
        {
            ERROR = 1;
            return false;
        }

        else if(age.isEmpty() || invalidDataCheck.hasAlphabets(age))
        {
            ERROR = 2;
            return false;
        }

        else if(phone.isEmpty() || phone.length() < 11 || !phone.startsWith("01")
                || invalidDataCheck.hasAlphabets(phone) )
        {
            ERROR = 3;
            return false;
        }
        else if(password.isEmpty())
        {
            ERROR = 4;
            return false;
        }
        else if(bloodGroup.contains("Select Blood Group"))
        {
            ERROR = 5;
            return false;
        }

        else if(location.isEmpty() || invalidDataCheck.hasNums(location))
        {
            ERROR = 6;
            return false;
        }
        // checking if age is less than 18 0r greater than 60
        else if(AgeNum < 18 && AgeNum > 60)
        {
            ERROR = 7;
            return false;
        }
        
        return true;
    }

}
