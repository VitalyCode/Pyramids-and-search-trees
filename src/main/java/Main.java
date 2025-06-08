import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
// Заполнение дерева
        // Названия переменных указывают на место заполняемого узла
        // например, rl - повернуть на право, затем налево
        // После заполнения дерево выводится на консоль, можете ориентироваться на него

        Tree ll = new Tree("Александа");
        Tree lr = new Tree("Владимир");
        Tree l = new Tree("Борис");
        l.setLeft(ll);
        l.setRight(lr);

        Tree rl = new Tree("Иннокентий");
        Tree rr = new Tree("Пантелеймон");
        Tree r = new Tree("Константин");
        r.setLeft(rl);
        r.setRight(rr);

        Tree root = new Tree("Зоя");
        root.setLeft(l);
        root.setRight(r);

        System.out.println(root); // Выведем дерево в консоль

        System.out.println("Проверка поиска по дереву:");
        System.out.println(root.contains("Иннокентий")); // true
        System.out.println(root.contains("Борис")); // true
        System.out.println(root.contains("Анна")); // false

        /* Ваше задание (нужно раскомментировать)*/
        System.out.println("Проверка на пирамидальность по длине имени");

        System.out.println(root.isNamePyramid()); // true

        // Меняем имя в одном из узлов на Павел
        // Пирамидальность должна нарушиться
        // А из-за того что имя на ту же букву,
        // в данном случае свойства дерева поиска сохрнаяются
        rr.setName("Павел");
        System.out.println(root.isNamePyramid()); // false

    }
}

// Дерево
class Tree {
    private String name; // Значение
    private Tree left; // Левая ветка
    private Tree right; // Правая ветка

    public Tree(String name) {
        this.name = name;
    }

    // Проверка на наличия элемента
    public boolean contains(String query) {
        if (query.compareTo(name) < 0) {
            return left != null && left.contains(query);
        } else if (query.compareTo(name) > 0) {
            return right != null && right.contains(query);
        } else {
            return name.equals(query);
        }
    }

    // Свойство для чтения поля
    public String getName() {
        return name;
    }

    // Свойство для установки поля
    public void setName(String name) {
        this.name = name;
    }

    // Свойство для установки левой части
    public void setLeft(Tree left) {
        this.left = left;
    }

    // Свойство для чтения левой части
    public Tree getLeft() {
        return left;
    }

    // Свойство для установки правой части
    public void setRight(Tree right) {
        this.right = right;
    }

    // Свойство для чтения правой части
    public Tree getRight() {
        return right;
    }

    // Раскомментируйте и реализуйте этот метод
    public boolean isNamePyramid() {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && name.length() > left.name.length()) {
            return false;
        }

        if (right != null && name.length() > right.name.length()) {
            return false;
        }

        boolean leftPyramid = (left == null) || left.isNamePyramid();
        boolean rightPyramid = (right == null) || right.isNamePyramid();

        return leftPyramid && rightPyramid;
    }

    // Вывод дерева
    @Override
    public String toString() {
        String[] leftLines = (left != null ? left.toString() : "").split("\n");
        String[] rightLines = (right != null ? right.toString() : "").split("\n");

        int maxLeftSize = Arrays.stream(leftLines)
                .map(String::length)
                .max(Comparator.naturalOrder())
                .orElse(0);

        List<String> lines = new ArrayList<>();
        lines.add(" ".repeat(maxLeftSize + 1) + name);
        for (int i = 0; i < Math.max(leftLines.length, rightLines.length); i++) {
            String prefix = i < leftLines.length ? leftLines[i] : "";
            lines.add(
                    prefix +
                            " ".repeat(maxLeftSize + name.length() + 1 * 2 - prefix.length()) +
                            (i < rightLines.length ? rightLines[i] : "")
            );
        }

        return lines.stream().collect(Collectors.joining("\n"));
    }
}
