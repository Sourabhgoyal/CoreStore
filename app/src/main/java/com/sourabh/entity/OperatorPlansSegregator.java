package com.sourabh.entity;

import java.util.ArrayList;

/**
 * Created by SOURABH on 8/7/2015.
 */
public class OperatorPlansSegregator {
    ArrayList<OperatorPlan> fullTalktime;
    ArrayList<OperatorPlan> topup;
    ArrayList<OperatorPlan> special;
    ArrayList<OperatorPlan> g2;
    ArrayList<OperatorPlan> g3;
    ArrayList<OperatorPlan> roaming;

    public ArrayList<OperatorPlan> getFullTalktime() {
        return fullTalktime;
    }

    public void setFullTalktime(ArrayList<OperatorPlan> fullTalktime) {
        this.fullTalktime = fullTalktime;
    }

    public ArrayList<OperatorPlan> getTopup() {
        return topup;
    }

    public void setTopup(ArrayList<OperatorPlan> topup) {
        this.topup = topup;
    }

    public ArrayList<OperatorPlan> getSpecial() {
        return special;
    }

    public void setSpecial(ArrayList<OperatorPlan> special) {
        this.special = special;
    }

    public ArrayList<OperatorPlan> getG2() {
        return g2;
    }

    public void setG2(ArrayList<OperatorPlan> g2) {
        this.g2 = g2;
    }

    public ArrayList<OperatorPlan> getG3() {
        return g3;
    }

    public void setG3(ArrayList<OperatorPlan> g3) {
        this.g3 = g3;
    }

    public ArrayList<OperatorPlan> getRoaming() {
        return roaming;
    }

    public void setRoaming(ArrayList<OperatorPlan> roaming) {
        this.roaming = roaming;
    }

}
