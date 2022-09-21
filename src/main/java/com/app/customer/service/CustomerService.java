package com.app.customer.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
public class CustomerService {
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public HashMap<String, HashMap<String, String>> calcForCustomers(ArrayList<HashMap<String, String>> ar) {
		HashMap<String, HashMap<String, String>> out = new HashMap<>();
		Map<String, List<HashMap<String, String>>> byCustomerData =
			    ar.stream().collect(Collectors.groupingBy(w -> w.get("customerId")));
		for (Map.Entry<String, List<HashMap<String, String>>> set :byCustomerData.entrySet()) {
			Map<String, List<HashMap<String, String>>> filtered =
				    set.getValue().stream().collect(Collectors.groupingBy(w -> w.get("date").substring(5, 7)));
			
			HashMap <String, String> hm = new HashMap<>();
			for(Map.Entry<String, List<HashMap<String, String>>> subSet :filtered.entrySet()) {				
				List<Integer> amountsPerMonth = subSet.getValue().stream().map(e -> Integer.parseInt(e.get("amount"))).collect(Collectors.toList());
				int sum = amountsPerMonth.stream()
				.map(x-> (x-100))
				.filter(x -> x > 0)
				.map(x -> x * 2)
				.reduce(0, (a, b) -> a + b);
				
				sum += amountsPerMonth.stream()
						.map(x-> (x-100))
						.filter(x -> x > 0)
						.map(x -> x * 2)
						.filter(x -> x/2 > 50)
						.map(x -> (x-50) * 1)
						.reduce(0, (a, b) -> a + b);
				
				sum += amountsPerMonth.stream()
						.filter(x -> x < 100)
						.map(x -> (x-50) * 1)
						.reduce(0, (a, b) -> a + b);
				
				
				System.out.println(sum);
				
				hm.put(subSet.getKey(), String.valueOf(sum));
				
			}
			out.put(set.getKey(), hm);
			
		}
		
		return out;
    	
    }


}
