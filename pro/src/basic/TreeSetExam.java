package basic;

import java.util.*;
import java.lang.*;
import java.io.*;

public class TreeSetExam {
    public static void main(String[] args) throws Exception {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("apple");
        ts.add("core");
        ts.add("banana");

        // 정렬된 순서로 출력
        for(String s : ts)
            System.out.println(s);

        HashSet<String> hs = new HashSet<>(ts);

        // 정렬되지 않은 채 출력
        for(String s : hs)
            System.out.println(s);


        // For문내에서 요소 삭제
        Iterator<String> iterator = hs.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.length() % 2 == 0) {
                iterator.remove();
            }
        }

        // 삭제 결과 출력
        for(String s : hs)
            System.out.println(s);


    }
}