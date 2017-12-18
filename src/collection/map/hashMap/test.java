package collection.map.hashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class test{
	
	public static final List<Integer> prices = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String,String>(256,0.75f);
		for(int i=0;i<10;i++){
			map.put(String.valueOf(i), String.valueOf(i));
		}
		Spliterator<Entry<String, String>> splitIter = map.entrySet().spliterator();
		Stream<Entry<String,String>> stream = StreamSupport.stream(splitIter.trySplit(), true);
		stream.forEach(System.out::println);
//		while(iter.hasNext()){
//			Entry<String, String> entry = iter.next();
//		}
//		while(splitIter.){
//			Entry<String, String> entry = iter.next();
//		}
//		for(splitIter.)
//		HashMapSpliterator<String, String> hashMapSpliterator = HashMapSpliterator(HashMap<K,V> m, int origin,
//                int fence, int est,
//                int expectedModCount) {
//		for(Entry<String, String> en){
//			
//		}
//		for(Entry<String, String> entry : map.entrySet()) {  
//			map.replace(entry.getKey(), entry.getKey(),entry.getKey()+"aaa");
//			System.out.println(entry.getKey()+"  "+entry.getValue());
//        }
		
//		 List<String>  arrs = new ArrayList<>();
//		 arrs.add("a");
//		 arrs.add("b"); 
//		 arrs.add("c");
//		 arrs.add("d");
//		 arrs.add("e");
//		 arrs.add("f");
//		 arrs.add("h");
//		 arrs.add("i");
//		 arrs.add("j");
//		 Spliterator<String> a =  arrs.spliterator();
//		 Stream<String> intStream = StreamSupport.stream(a.trySplit(), false);
//		 intStream.forEach(System.out::println);
	}
	
	public int maxProfit(final int length) {
	    int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
	    for(int i = 1; i < length; i++) {
	        int priceWhenCut = maxProfit(i) + maxProfit(length - i);
	        if(profit < priceWhenCut) profit = priceWhenCut;
	    }
	    return profit;
	}
	
	public int maxProfit1(final int rodLenth) {
	    return callMemoized(
	        (final Function<Integer, Integer> func, final Integer length) -> {
		        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
		        for(int i = 1; i < length; i++) {
		            int priceWhenCut = func.apply(i) + func.apply(length - i);
		            if(profit < priceWhenCut) profit = priceWhenCut;
		        }
		        return profit;
	        }, rodLenth);
	}
	
	public static <T, R> R callMemoized(final BiFunction<Function<T,R>, T, R> function, final T input) {
	        Function<T, R> memoized = new Function<T, R>() {
	            private final Map<T, R> store = new HashMap<>();
	            public R apply(final T input) {
	                return store.computeIfAbsent(input, key -> function.apply(this, key));
	            }
	        };
	        return memoized.apply(input);
	    }
}