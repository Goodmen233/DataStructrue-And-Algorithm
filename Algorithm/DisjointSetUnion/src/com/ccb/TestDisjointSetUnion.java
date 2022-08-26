package com.ccb;

import com.ccb.def.SetUnion;

import java.util.ArrayList;
import java.util.List;

public class TestDisjointSetUnion {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        SetUnion setUnion = new SetUnion(list);
        setUnion.mergeSet(2, 3);
        System.out.println(setUnion.isSameSet(3, 2));
    }
}
