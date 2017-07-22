package Donor;

public class invalidDataCheck
{

    // This method checks if there is any number in the String.
    // Like a Name cannot contain a number.
    public static boolean hasNums(String str)
    {
        char[] number = {'1' , '2' , '3' , '4' , '5' ,'6' , '7' , '8' ,'9' ,'0'};
        // Converting the given string into a character array
        char[] charArray = str.toCharArray();

        for(int i = 0 ; i < str.length() ; i++)
            for(int j = 0 ; j < number.length ; j++)
            {
                if(charArray[i] == number[j])
                {
                    return true;
                }

            }
        return false;

    }

    // this method checks if a string (Number input) has an alphabet
    // Age is taken input as a string , but we need to check if age string contains
    // anything else rather than Number. Age = 12A $invalid input

    public static boolean hasAlphabets(String str)
    {
        int flag = 0;
        char charArray[] = str.toCharArray();

        for(int i = 0 ; i < charArray.length ; i++)
        {
            // Java has a Built in Character Class
            // isDigit is used to check if the given character is digit or not.
            // if it's not a digit then input is invalid
            if( Character.isLetter(charArray[i]) )
            {
                return true;
            }
        }
        return false;

    }
    // This method is used to detect if a string has invalid characters
    // Numbers and Alphabets are only valid characters
    public static boolean hasInvalidMarks(String str)
    {

        char charArray[] = str.toCharArray();

        for(int i = 0 ; i < charArray.length ; i++)
        {
            if(!Character.isLetterOrDigit(charArray[i]))
            {
                return true;
            }
        }


        return false;
    }
}
