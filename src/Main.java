import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Comparator<Person> byFamily = Comparator.comparing(Person::getFamily);
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long teenagers = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + teenagers);
        List<String> conscripts;
        conscripts = persons.stream()
                .filter(value -> Sex.MAN.equals(value.getSex()))
                .filter(value -> value.getAge() > 17 && value.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников = " + conscripts.size());

        List<Person> workers;
        workers = persons.stream()
                .filter(value -> Education.HIGHER.equals(value.getEducation()))
                .filter(value -> (Sex.WOMAN.equals(value.getSex()) && value.getAge() < 60) || (Sex.MAN.equals(value.getSex()) && value.getAge() < 65))
                .sorted(byFamily)
                .collect(Collectors.toList());
        System.out.println("Всего трудоспособного населения = " + workers.size());
    }
}