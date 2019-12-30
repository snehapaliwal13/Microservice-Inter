package com.restMicroInter.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restMicroInter.demo.model.User;

@Service
public class UserService {

	public HashMap<Integer, Integer> getTotalBalanceForUser(List<User> users){

		HashMap<Integer, Integer> resultMap=new HashMap<Integer, Integer>();
		for(User user:users) {
			int totalBalance=0;
			String accountDetails=user.getAccountDetails();
			if(null!=accountDetails) {
				String[] accountType=accountDetails.split(";");
				for(String str1 : accountType) {
					String[] str2=str1.split(":");
					totalBalance=totalBalance+Integer.parseInt(str2[1]);
				}
			}
			resultMap.put(user.getUserId(), new Integer(totalBalance));
		}
		return resultMap;
	}

}
