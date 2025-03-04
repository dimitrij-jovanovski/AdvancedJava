import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


class Student {
    String id;
    List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    public static Student create(String line) {
        String[] parts = line.split("\\s+");
        String id = parts[0];
        List<Integer> grades = Arrays.stream(parts).skip(1).map(Integer::parseInt).collect(Collectors.toList());
        return new Student(id, grades);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", grades=" + grades +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { //Test for String,Integer
            List<Rule<String, Integer>> rules = new ArrayList<>();

            /*
            TODO: Add a rule where if the string contains the string "NP", the result would be index of the first occurrence of the string "NP"
            * */
            Predicate<String> containsNP = s -> s.contains("NP");
            Function<String, Integer> transformation = s -> {
                String[] split = s.split("NP");
                return split[0].length();
            };
            rules.add(new Rule<>(containsNP, transformation));
            //---------------------------------------------------------------------

            /*
            TODO: Add a rule where if the string starts with the string "NP", the result would be length of the string
            * */
            Predicate<String> startswithNP = s -> s.startsWith("NP");
            Function<String, Integer> transform = String::length;
            rules.add(new Rule<>(startswithNP, transform));
            //---------------------------------------------------------------------


            List<String> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(sc.nextLine());
            }

            RuleProcessor.process(inputs, rules);


        } else { //Test for Student, Double
            List<Rule<Student, Double>> rules = new ArrayList<>();

            //TODO Add a rule where if the student has at least 3 grades, the result would be the max grade of the student

            Predicate<Student> has3grades = student -> student.grades.size() >= 3;
            Function<Student, Double> transformation = student -> student.grades.stream().max(Integer::compareTo).get().doubleValue();
            Rule<Student, Double> rule = new Rule<>(has3grades, transformation);
            rules.add(rule);
            //---------------------------------------------------------------------


            //TODO Add a rule where if the student has an ID that starts with 20, the result would be the average grade of the student
            //If the student doesn't have any grades, the average is 5.0

            Predicate<Student> ID = student -> student.id.startsWith("20");
            Function<Student, Double> transform = student -> {
                if(student.grades.size() == 0) return 5.0;
                else return student.grades.stream().collect(Collectors.averagingDouble(Double::valueOf));
            };
            Rule<Student, Double> rule2 = new Rule<>(ID, transform);
            rules.add(rule2);
            //---------------------------------------------------------------------


            List<Student> students = new ArrayList<>();
            while (sc.hasNext()){
                students.add(Student.create(sc.nextLine()));
            }

            RuleProcessor.process(students, rules);
        }
    }
}
