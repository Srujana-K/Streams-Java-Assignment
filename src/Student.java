public class Student {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String engDept;
    private int yearOfEnrollment;
    private double perTillDate;

    public Student(int id, String name, int age, String gender, String engDept, int yearOfEnrollment, double perTillDate){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.engDept=engDept;
        this.yearOfEnrollment=yearOfEnrollment;
        this.perTillDate=perTillDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getEngDept() {
        return engDept;
    }

    public int getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public double getPerTillDate() {
        return perTillDate;
    }

}
