package com.labdaschool.javapiggybank.controler;


import com.labdaschool.javapiggybank.model.Currency;
import com.labdaschool.javapiggybank.repo.CurrencyRepro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CurrencyRepro currepo;

    private List<Currency> findMoney(List<Currency> myList, CheckMoney tester)
    {
        List<Currency> tempList = new ArrayList<>();

        for ( Currency c : myList)
        {
            if(tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }
    //http://localhost:2019/total
    @GetMapping(value = "/total", produces={"application/json"})
    public ResponseEntity <?> listAllTotals()
    {
        List<Currency> myList = new ArrayList<>();
        currepo.findAll().iterator().forEachRemaining(myList ::add);

        double totals = 0;
        for(Currency c : myList)
        {
            totals = totals + (c.getQuantity() * c.getValue());

        }



        System.out.println( "The piggy bank holds"+ " " +totals);
        return new ResponseEntity<>(myList, HttpStatus.OK);

    }
    //http://localhost:2019/money/{amount}
    @GetMapping(value ="/money/{amount}", produces = {"application/json"})
    public ResponseEntity<?> listAllMoney (@PathVariable double amount)
    {
        List<Currency> myList = new ArrayList<>();
        currepo.findAll().iterator().forEachRemaining(myList::add);

        double initalMoney = 0;
        for(Currency c : myList)
        {
            initalMoney = initalMoney + (c.getQuantity() * c.getValue());
        }

        if(amount > initalMoney)
        {
            System.out.println("You don't have enough in the piggy bank!");
        }

        if(amount <= initalMoney)
        {
            int dollars = (int) amount;
            double cents = (amount - dollars) * 100;
            int intCents = (int) cents;

            int dimes = intCents / 10;
            intCents = intCents - (dimes * 10);
            int nickles = intCents / 5;
            intCents = intCents - (nickles * 5);
            int pennies = intCents / 1;

            for(Currency c : myList)
            {
                if (c.getName().contains("dollar"))
                {
                    int initalQuantity = c.getQuantity();

                    if (initalQuantity >= dollars)
                    {
                        c.setQuantity(c.getQuantity() - dollars);
                        dollars = 0;
                    }

                    if (initalQuantity < dollars)
                    {
                        c.setQuantity(0);
                        dollars = dollars - initalQuantity;
                    }

                }

                if (c.getName().contains("quarter"))
                {
                    int initalQuantity = c.getQuantity();
                    int quarters = intCents / 25;
                    intCents = intCents - (quarters * 25);
                    if (initalQuantity >= quarters)
                    {
                        c.setQuantity(c.getQuantity() - quarters);
                        quarters = 0;
                    }

                    if (initalQuantity < quarters)
                    {
                        c.setQuantity(0);
                        quarters = quarters - initalQuantity;
                    }

                }

                if (c.getName().contains("ime"))
                {
                    int initalQuantity = c.getQuantity();
                    if (initalQuantity >= dimes)
                    {
                        c.setQuantity(c.getQuantity() - dimes);
                        dimes = 0;
                    }

                    if (initalQuantity < dimes)
                    {
                        c.setQuantity(0);
                        dimes = dimes - initalQuantity;
                    }

                }

                if (c.getName().contains("nickle"))
                {
                    int initalQuantity = c.getQuantity();
                    if (initalQuantity >= nickles)
                    {
                        c.setQuantity(c.getQuantity() - nickles);
                        nickles = 0;
                    }

                    if (initalQuantity < nickles)
                    {
                        c.setQuantity(0);
                        nickles = nickles - initalQuantity;
                    }
                }

                if (c.getName().contains("pennie"))
                {
                    int initalQuantity = c.getQuantity();

                    if (initalQuantity >= pennies)
                    {
                        c.setQuantity(c.getQuantity() - pennies);
                        pennies = 0;
                    }

                    if (initalQuantity < pennies)
                    {
                        c.setQuantity(0);
                        pennies = pennies - initalQuantity;
                    }

                }
            }}


        for(Currency c: myList)
        {
            if (c.getQuantity() > 1 || c.getQuantity() == 0)
            {
                if (c.getNameplural()
                        .contains("dollar"))
                {
                    System.out.println("$" + c.getQuantity());
                } else
                {
                    System.out.println(c.getQuantity() + " " + c.getNameplural());
                }
            }

            if (c.getQuantity() == 1)
            {
                if(c.getName().contains("dollar"))
                {
                    System.out.println("$" + c.getQuantity());
                }
                else
                {
                    System.out.println(c.getQuantity() + " " + c.getName());
                }
            }}

        System.out.println("The piggy bank holds " +initalMoney);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
