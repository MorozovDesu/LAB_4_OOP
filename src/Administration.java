public class Administration extends Cadres {

    public Administration(){

    }
    public Administration(String name, int age, String job) {
        super(name, age, job);
    }

    @Override
    public void SayHello() {
        System.out.println("Hello i am Administration");
    }
}
