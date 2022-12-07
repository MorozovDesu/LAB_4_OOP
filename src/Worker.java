public class Worker extends Cadres {
    public Worker(){

    }
    public Worker(String name, int age,String job) {
        super(name, age, job);
    }
    @Override
    public void SayHello() {
        System.out.println("Hello i am Worker");
    }


}
