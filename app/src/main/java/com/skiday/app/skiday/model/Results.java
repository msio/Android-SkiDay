package com.skiday.app.skiday.model;

import android.util.Log;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by jan on 15.05.17.
 */

public class Results {


    private static final String TAG = "Results";

    private HashMap<Integer, Person> persons = null;
    private List<MeantimeResult> meantimeResults = null;


    public void init(){
        getPersons().put(1, new Person("Marcel Hirscher", R.mipmap.hirscher, Country.AUSTRIA, 1));
        getPersons().put(2, new Person("Michael Matt", R.mipmap.matt, Country.AUSTRIA, 2));
        getPersons().put(3, new Person("Manfred Mölgg", R.mipmap.moelgg, Country.ITALY, 3));
        getPersons().put(4, new Person("Felix Neureuther", R.mipmap.neureuther, Country.GERMANY, 4));
        getPersons().put(5, new Person("Manuel Feller", R.mipmap.feller, Country.AUSTRIA, 5));
        getPersons().put(6, new Person("Mattias Hargin", R.mipmap.hargin, Country.SWEDEN, 6));
        getPersons().put(7, new Person("David Ryding", R.mipmap.ryding, Country.GB, 7));
        getPersons().put(8, new Person("Fritz Dopfer", R.mipmap.dopfer, Country.GERMANY, 8));
        getPersons().put(9, new Person("Luca Aerni", R.mipmap.aerni, Country.SWITZERLAND, 9));
        getPersons().put(10, new Person("Jonathan Nordbotten", R.mipmap.nordbotten, Country.NORWAY, 10));

        for (int lapNumber = 1; lapNumber <= 2; lapNumber++) {
            for (int id = 1; id <= 10; id++) {
                int offset = 0;
                offset = (int) (Math.random()*1000)%500;
                System.out.println("offset: "+offset);
                getMeantimeResults().add(new MeantimeResult(id, lapNumber, 1, 2000+offset));
                offset = (int) (Math.random()*1000)%500;
                getMeantimeResults().add(new MeantimeResult(id, lapNumber, 2, 3500+offset));
                offset = (int) (Math.random()*1000)%500;
                getMeantimeResults().add(new MeantimeResult(id, lapNumber, 3, 4500+offset));
                offset = (int) (Math.random()*1000)%500;
                getMeantimeResults().add(new MeantimeResult(id, lapNumber, 4, 5300+offset));
            }
        }

        for (Person person : getPersons().values()){
            int lap1Result = 0, lap2Result = 0;

            lap1Result = getMeanTimeResult(person.getId(), 1, 4).getTime();
            lap2Result = getMeanTimeResult(person.getId(), 2, 4).getTime();

            MeantimeResult result = new MeantimeResult(person.getId(), 3, 4, (lap1Result+lap2Result));
            getMeantimeResults().add(result);
        }

    }

    public MeantimeResult getMeanTimeResult(int id, int lapNumber, int meanTime){
        for(MeantimeResult result : getMeantimeResults()){
            if((result.getId() == id) && (result.getLapNumber() == lapNumber) && (result.getMeantimeNumber() == meanTime)){
                return result;
            }
        }
        return null;
    }

    public MeantimeResultLine getLapResult(int id, final int lapNumber){
        return getMeantimeResultLine(id, lapNumber, 4);
    }

    public TreeSet<MeantimeResultLine> getLapResultTable(int lapNumber){
        Comparator<MeantimeResultLine> comparator = new MeantimeResultComparator();
        TreeSet<MeantimeResultLine> resultLines = new TreeSet<>(comparator);

        for(Person person : getPersons().values()){
            resultLines.add(getLapResult(person.getId(), lapNumber));
        }

        return resultLines;
    }

    public static class MeantimeResultComparator implements Comparator<MeantimeResultLine>{
        @Override
        public int compare(MeantimeResultLine o1, MeantimeResultLine o2) {
            return (o1.getTime() - o2.getTime());
        }
    }

    public MeantimeResultLine getMeantimeResultLine(int id, final int lapNumber, final int meantimeNumber){
        Log.i(TAG, "getMeantimeResultLine: "+ id+ " "+ lapNumber+" "+meantimeNumber);

        List<MeantimeResult> meantimeResults = new ArrayList<>();
        int ownId = MainActivity.getOwnId();
        MeantimeResult currentMeantimeResult = null;

        for(MeantimeResult result : getMeantimeResults()){
            if(lapNumber == result.getLapNumber() && meantimeNumber == result.getMeantimeNumber()) {
                meantimeResults.add(result);
            }
            if (result.getId() == id && result.getLapNumber() == lapNumber && result.getMeantimeNumber() == meantimeNumber) {
                currentMeantimeResult = result;
            }
        }

        if (currentMeantimeResult == null){
            Log.e(TAG, "getMeantimeResultLine: currentMeantimeResult is null! "+id + " lap: "+lapNumber + " meantimenumber: "+meantimeNumber);
        }

        MeantimeResult best = null, me = null;
        int relBest = 0;
        int relMe = 0;

        for(MeantimeResult result : meantimeResults){
            if(best == null || (best.getTime() > result.getTime())){
                best = result;
            }
            if(result.getId() == ownId)
                me = result;
        }

        if (best == null){
            Log.e(TAG, "getMeantimeResultLine: Best is null! ");
            System.out.println(meantimeResults.size());
        }

        if (currentMeantimeResult != null && best != null)
            relBest = currentMeantimeResult.getTime() - best.getTime();

        if (currentMeantimeResult != null && me != null)
            relMe = currentMeantimeResult.getTime() - me.getTime();



        int absoluteTimeBest = best.getTime();
        int absoluteTimeMe = me.getTime();
        int absoluteTime = currentMeantimeResult.getTime();

        MeantimeResultLine line = new MeantimeResultLine(absoluteTime, relBest, relMe, absoluteTimeBest, absoluteTimeMe, getPersons().get(best.getId()), getPersons().get(currentMeantimeResult.getId()));

        return line;
    }

    public HashMap<Integer, Person> getPersons() {
        if(persons == null)
            persons = new HashMap<>();
        return persons;
    }

    public List<MeantimeResult> getMeantimeResults() {
        if (meantimeResults == null)
            meantimeResults = new ArrayList<>();
        return meantimeResults;
    }

    private static Results results = null;
    public static Results getResults(){
        if (results == null){
            results = new Results();
            results.init();
        }
        return results;
    }
}
