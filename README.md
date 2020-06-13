# Втора лабораториска вежба по Софтверско инженерство
## Александра Стефановска, бр. на индекс 171037
### Група на код:
Ја добив групата на код 1

### Control Flow Graph

![171037](https://user-images.githubusercontent.com/62511297/84208046-12d46a00-aab3-11ea-9681-477fea92a605.jpg)

### Цикломатска комплексност
Цикломатската комплексност ја одредуваме така што ги броиме регионите во графот. 
Може да заклучиме дека цикломатската комплексност е 8 и имаме толку независни патеки.

### Тест случаи според критериумот Every Path Test
    
    public class SILab2Test {
    private final User user(String username, String password, String email){
        return user(username,password,email);
    }
    private List<String> createList(String... allUsers) {
        return new ArrayList<>(Arrays.asList(allUsers));
    }
    @Test
    void TestEveryPath (){
        // 1 2 16 17 18
        assertEquals(false, SILab2.function(null,createList("a,b,c")));
        // 1 2 3 15 16 17 18
        assertEquals(false, SILab2.function(user(null,null,"a"), createList("a,b,c")));
        // 1 2 3 4 5.1 (5.2 6 7 8 9 10 11 5.3 5.2) 12 13 14 18 True
        assertEquals(true, SILab2.function(user("alek","pass","a@gmail.com"),createList("a,b,c,alek")));
        // 1 2 3 4 5.1 5.2 11 12 14 18 False - cant happen
        // 1 2 3 4 5.1 (5.2 6 7 8 10 11 5.3 5.2) 12 14 18 False mixed (bez 9)
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),createList("a,b,c,alek")));
        // 1 2 3 4 5.1 (5.2 6 8 9 10 11 5.3 5.2) 12 14 18 False mixed (bez 7)
        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),createList("a,b,c,alek")));

        // 1 2 3 4 5.1 (5.2 6 8 10 11 5.3 5.2) 12 14 18 False mixed
        assertEquals(false, SILab2.function(user("alek","pass","agmailcom"),createList("a,b,c,alek")));
    }
    }
    
### Тест случаи според критериумот Multiple Conditions Test 
    
    public class SILab2Test {
    private final User user(String username, String password, String email){
        return user(username,password,email);
    }
    private List<String> createList(String... allUsers) {
        return new ArrayList<>(Arrays.asList(allUsers));
    }
    @Test
    void multipleConditionsTest() {
        //if (user.getUsername()!=null && user.getEmail()!=null && !allUsers.contains(user.getUsername())) { //3
        //F && X && X
        //T && F && X
        //T && T && F

        assertEquals(false, SILab2.function(user(null,"pass","a@gmail.com"),createList("a,b,c")));
        assertEquals(false, SILab2.function(user("alek","pass",null),createList("a,b,c")));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmail.com"),createList("a,b,c")));

        //if (atChar && user.getEmail().charAt(i)=='.') { //8
        //F && X
        //T && F

        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),createList("a,b,c,alek")));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),createList("a,b,c,alek")));

        //if (atChar && dotChar) { //12
        //F && X
        //T && F

        assertEquals(false, SILab2.function(user("alek","pass","agmail.com"),createList("a,b,c,alek")));
        assertEquals(false, SILab2.function(user("alek","pass","a@gmailcom"),createList("a,b,c,alek")));
    }
    }
    
### Објаснување на напишаните unit tests

#### Објаснување на тестовите од TestEveryPath:

Направени се тестови за сите можни патеки, во овој случај има вкупно 7.
* Тест1: како влез за user имаме null, што не е дозволено и веднаш враќа false.
* Тест2: на влез за username имаме null, и понатака не се прави проверка на останатите барања и враќа false.
* Тест3: овој тест враќа true бидејќи username, password имаат вредност, email ги содржи потребните барања ("@", ".")и притоа во allUsers го има конкретниот user.
* Тест4: не може да се случи бидејќи претходно се проверува дали emailot е null, така што сигурно барем еднаш ќе го измине for.
* Тест5: овој тест враќа false бидејќи username, password имаат вредност, но email не го содржи потребното барање ("."). Иако во allUsers го има конкретниот user.
* Тест6: овој тест враќа false бидејќи username, password имаат вредност, но email не го содржи потребното барање ("@"). Иако во allUsers го има конкретниот user.
* Тест7: овој тест враќа false бидејќи username, password имаат вредност, но email не ги содржи потребните барања ("@","."). Иако во allUsers го има конкретниот user.

#### Објаснување на тестовите од multipleConditionsTest:

Прво имаме тест за првиот multiple condition услов кој се состои од 2 логички оператори && и ги тестираме следниве 3 тест случаи:
* F && X && X - доколку првиот дел од условот не е исполнет, понатаму не се проверуваат останатите.
* T && F && X - иако првиот дел од условот е исполнет, односно имаме име на корисник, вториот дел не е, односно мејлот е null. Третиот дел не се прегледува.
* T && T && F - иако првиот и вториот дел од условот се исполнети, односно имаме има на корисник и мејл, корисникот го нема во листата на сите корисници.

Вториот multiple condition се состои од еден логички оператор && и ги тестираме следниве 2 случаи:
* F && X - кога првиот дел од условот е неточен тогаш вториот дел не се ни прегледува. (мејлот не содржи "@")
* T && F - случај кога првиот дел е точен, а вториот неточен. (мејлот содржи "@", но не и ".")

Третиот multiple condition се состои од еден логички оператор && и ги тестираме следниве 2 случаи:
* F && X - кога првиот дел од условот е неточен тогаш вториот дел не се ни прегледува. (мејлот не содржи "@")
* T && F - случај кога првиот дел е точен, а вториот неточен. (мејлот содржи "@", но не и ".")
