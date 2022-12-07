abstract class Cadres {
    private String name;
    private int age;
    private String job;
    private Group group;
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public Cadres(){

    }
    public Cadres(String name,int age,String job){
        this.name=name;
        this.age=age;
        this.job=job;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }

    public void Show(){
        System.out.println(getName()+ " " + getAge()+ " " + getJob());
    };
    public abstract void SayHello();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public final void info() {
        System.out.println(this);
    }
    @Override
    public String toString() {
        return "Cadres {name = " + name + (age != 0 ? ", age = " + age : " ") +
                "job = " + job+'}';
    }
}
