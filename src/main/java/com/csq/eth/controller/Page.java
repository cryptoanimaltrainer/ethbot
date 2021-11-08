package com.csq.eth.controller;

import com.csq.eth.bean.*;
import com.csq.eth.runnable.NewCoinRunnable;
import com.csq.eth.runnable.TransactionRunnable;
import com.csq.eth.util.EthUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class Page {
    Web3j web3j;
    public static Map<String, Account> accountMap = new ConcurrentHashMap<>();
    Map<Long, Plan> planMap = new ConcurrentHashMap<>();
    public Page(){
        web3j = Web3j.build(new HttpService("http://8.134.77.157/rpc"));
        new Thread(new TransactionRunnable(web3j)).start();
    }
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody AccountDto accountDto){
        try {
            Account account = new Account(accountDto.getAddress(),accountDto.getPrivateKey());
            accountMap.put(account.getAddress(),account);
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/makeNewCoinPlan")
    @ResponseBody
    public String makeNewCoinPlan(@RequestBody PlanDto planDto) throws IOException, ParseException {
        try {
            NewCoinPlan plan = new NewCoinPlan(planDto);
            long now = System.currentTimeMillis();
            try {
                planDto.setTokenName(EthUtils.getTokenName(web3j,planDto.getTokenAddress().trim(),planDto.getAddress()));
            }catch (Exception e){
            }
            planDto.setDateTime(planDto.getDate()+" "+planDto.getTime());
            planDto.setId(now);
            new Thread(new NewCoinRunnable(web3j,plan)).start();
            planMap.put(now,plan);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    @RequestMapping("/getNewCoinPlanList")
    @ResponseBody
    public List<PlanDto> makeNewCoinPlan() {
        try {
            List<PlanDto> result = new ArrayList<>();
            planMap.forEach((key,value) -> {
                NewCoinPlan newCoinPlan = (NewCoinPlan) value;
                PlanDto planDto = newCoinPlan.getPlanDto();
                planDto.setStatus(newCoinPlan.getStatus());
                result.add(planDto);
            });
            return result;
        }catch (Exception e){
            return null;
        }
    }
    @RequestMapping("/getLogById")
    @ResponseBody
    public String getLogById(@RequestBody String id){
        return planMap.get(Long.parseLong(id.trim().replace("=",""))).getLog();
    }
    @RequestMapping("/deletePlan")
    @ResponseBody
    public String deletePlan(@RequestBody String id){
        long l = Long.parseLong(id.trim().replace("=", ""));
        Plan plan = planMap.get(l);
        if (plan.ifFinish()) {
            planMap.remove(l);
        }else {
            plan.completePlan();
        }
        return "success";
    }
    @RequestMapping("/getAddressList")
    @ResponseBody
    public List<String> getAddressList(){
        try {
            List<String> addressList = new ArrayList<>();
            accountMap.values().forEach(account -> {
                addressList.add(account.getAddress());
            });
            return addressList;
        }catch (Exception e){
            return null;
        }
    }
}
