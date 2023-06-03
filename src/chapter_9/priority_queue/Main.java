package chapter_9.priority_queue;

public class Main {
    public static void main(String[] args) {

        Student ben = new Student("Ben");
        Rating benRating = new Rating(1);

        Student sam = new Student("Sam");
        Rating samRating = new Rating(2);

        PriorityQueue<Rating, Student> pq = new UnsortedPriorityQueue<>();
        pq.insert(benRating, ben);
        pq.insert(samRating, sam);

        System.out.println("Rating: " + pq.min().getKey().getValue());
        System.out.println("Student's Name: " + pq.min().getValue().getName());

        System.out.println(pq.size());
    }
}

class Rating implements Comparable<Rating>
{
    private int value;

    public Rating (int rating) {
        this.value = rating;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Rating rating) {
        if (this.getValue() < rating.getValue()) {
            return -1;
        } else if (this.getValue() == rating.getValue()) {
            return 0;
        } else {
            return 1;
        }
    }
}

class Student
{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}