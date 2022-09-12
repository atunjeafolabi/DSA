package chapter_7.positional_list.IterableLinkedPositionalList;

import chapter_7.positional_list.Position;

public class Main {
    public static void main(String[] args){
        LinkedPositionalList<String> queueInSupermarket = new LinkedPositionalList<>();

        queueInSupermarket.addLast("Mike");
        queueInSupermarket.addLast("Adebola");
        queueInSupermarket.addLast("Bayo");
        queueInSupermarket.addLast("Ben");

//        for (Iterator<String> it = queueInSupermarket.iterator(); it.hasNext(); ) {
//            String person = it.next();
//            System.out.println(person);
//        }

        // Iterating through the contents/elements of the list directly
        for (String person : queueInSupermarket ) {
            System.out.println(person);
        }

        System.out.println("\n------------------\n");

        // Iterating though positions then subsequently getting the list contents
        for (Position<String> p : queueInSupermarket.positions()) {
            System.out.println(p.getElement());
        }

         // Insertion sort
        LinkedPositionalList<Integer> numbers = new LinkedPositionalList<>();
        numbers.addFirst(1);
        numbers.addFirst(4);
        numbers.addFirst(9);
        numbers.addFirst(2);
        numbers.addFirst(3);
        LinkedPositionalList.insertionSort(numbers);

        System.out.println("Sorted numbers (first): " + numbers.first().getElement());
        System.out.println("Sorted numbers (last): " + numbers.last().getElement());
    }
}
