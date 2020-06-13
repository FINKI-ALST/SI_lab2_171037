import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SILab2Test {
    private final User user(String username, String password, String email){
        return user(username,password,email);
    }

    @Test
    void TestEveryPath (){
        // 1 2 16 17 18
        assertEquals(false, SILab2.function(null,"a,b,c"));
        // 1 2 3 15 16 17 18
        assertEquals(false, SILab2.function(user(null,null,"a"),"a,b,c"));
        // 1 2 3 4 5.1 (5.2 6 7 8 9 10 11 5.3 5.2) 12 13 14 18 True
        assertEquals(true, SILab2.function(user("alek","pass","a@gmail.com"),"a,b,c,alek"));
        // 1 2 3 4 5.1 5.2 11 12 14 18 False - cant happen
        // 1 2 3 4 5.1 (5.2 6 7 8 10 11 5.3 5.2) 12 14 18 False mixed (bez 9)
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),"a,b,c,alek"));
        // 1 2 3 4 5.1 (5.2 6 8 9 10 11 5.3 5.2) 12 14 18 False mixed (bez 7)
        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),"a,b,c,alek"));

        // 1 2 3 4 5.1 (5.2 6 8 10 11 5.3 5.2) 12 14 18 False mixed
        assertEquals(false, SILab2.function(user("alek","pass","agmailcom"),"a,b,c,alek"));

    }

    @Test
    void multipleConditionsTest() {
        //if (user.getUsername()!=null && user.getEmail()!=null && !allUsers.contains(user.getUsername())) { //3
        //F && X && X
        //T && F && X
        //T && T && F

        assertEquals(false, SILab2.function(user(null,"pass","a@gmail.com"),"a,b,c"));
        assertEquals(false, SILab2.function(user("alek","pass",null),"a,b,c"));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmail.com"),"a,b,c"));

        //if (atChar && user.getEmail().charAt(i)=='.') { //8
        //F && X
        //T && F

        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),"a,b,c,alek"));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),"a,b,c,alek"));

        //if (atChar && dotChar) { //12
        //F && X
        //T && F

        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),"a,b,c,alek"));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),"a,b,c,alek"));
    }
}
