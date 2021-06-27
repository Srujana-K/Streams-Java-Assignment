import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentStream {

    private ArrayList<Student> student;
    public StudentStream(ArrayList<Student> student){
        this.student=student;
    }
    private Stream<Student> getStream(){
        return student.stream();
    }
    public void printAllDepartments(){
        Stream<Student> studStream = getStream();
        Stream<String> uniqueDepts = studStream.map(Student :: getEngDept).distinct();
        uniqueDepts.forEach(i->System.out.println(i));
    }
    public void printEnrolledAfter(int year){
        Stream<Student> studStream = getStream();
        Stream<Integer> enrolledAfterYear = studStream.map(Student :: getYearOfEnrollment).filter(y->y>year);
        enrolledAfterYear.forEach(i->System.out.println(i));
    }
    public void printGenderInDept(String gender, String dept){
        Stream<Student> studStream = getStream();
        Stream<Student> genderInDept = studStream.filter(s->s.getGender().equals(gender) && s.getEngDept().equals(dept));
        genderInDept.forEach(s->System.out.println(s.getName()));
    }
    public void countMaleAndFemaleStudents(){
        int count_male = countStudentsByGender("Male");
        int count_female = countStudentsByGender("Female");
        System.out.println("Males count: " + count_male);
        System.out.println("Females count: " + count_female);
    }
    public int countStudentsByGender(String gender){
        Stream<Student> studStream = getStream().filter(s->s.getGender().equals(gender));
        return (int)studStream.count();
    }
    public void averageMaleAndFemaleAge(){
        int count_male = countStudentsByGender("Male");
        int age_male = countAgeByGender("Male");
        double maleAvg = (double) age_male/count_male;
        System.out.println("Males Average: "+maleAvg);

        int count_female = countStudentsByGender("Female");
        int age_female = countAgeByGender("Female");
        double femaleAvg = (double) age_female/count_female;
        System.out.println("Females Average: "+femaleAvg);
    }
    public int countAgeByGender(String gender){
        Stream<Student> studStream = getStream().filter(s->s.getGender().equals(gender));
        Stream<Integer> studAges = studStream.map(Student :: getAge);
        return studAges.reduce(0,Integer :: sum);
    }
    public void printHighestPercentStudents(){
        Student stud = new Student(0,null,0,null,null,0,0);
        Student highestPercentStud = getStream().reduce(stud,(p,q)->(p.getPerTillDate()>q.getPerTillDate())?p:q);
        System.out.println(highestPercentStud.getName());
    }
    public void printDeptCount(){
        System.out.println(getStream().collect(Collectors.groupingBy(Student :: getEngDept, Collectors.counting())));
    }
    public void youngestMaleStudent(String dept){
        Stream<Student> deptStream = getStream().filter(s->s.getEngDept().equals(dept));
        Student youngestStudent = deptStream.min(Comparator.comparing(Student :: getAge)).get();
        System.out.println(youngestStudent.getName());
    }
    public void averagePercentageInDepts(){
        Map<String, Double> deptAvgPercentage = getStream().collect(Collectors.groupingBy(Student :: getEngDept, Collectors.averagingDouble(Student :: getPerTillDate)));
        for(String dept : deptAvgPercentage.keySet()){
            System.out.println(dept + " Average Percentage: " + deptAvgPercentage.get(dept));
        }
    }
    public void printGenderCountInDept(String dept){
        Stream<Student> males = getDeptStream(dept).filter(s->s.getGender().equals("Male"));
        Stream<Student> females = getDeptStream(dept).filter(s->s.getGender().equals("Female"));
        System.out.println("Males: " + males.count() +"\nFemales: " + females.count());
    }
    private Stream<Student> getDeptStream(String dept){
        return getStream().filter(s->s.getEngDept().equals(dept));
    }
}
