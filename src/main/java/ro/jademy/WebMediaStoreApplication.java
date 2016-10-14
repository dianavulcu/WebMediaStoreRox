package ro.jademy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebMediaStoreApplication {

	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>();
       l1.addAll(Arrays.asList(new Integer[]{1,2,5}));
       System.out.println(Arrays.toString(l1.toArray()));
       
       List<Integer> l2 = new ArrayList<>();
       l2.addAll(Arrays.asList(new Integer[]{6,2,3}));
       System.out.println(Arrays.toString(l2.toArray()));
      l1.addAll(l2);
      System.out.println(Arrays.toString(l1.toArray()));
	}
	
	
}
