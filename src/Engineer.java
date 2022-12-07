public class Engineer extends Cadres{

    public Engineer(){

    }
    public Engineer(String name, int age, String job) {
        super(name, age, job);
    }

    @Override
    public void SayHello() {
        System.out.println("Hello i am Engineer");
    }
}
