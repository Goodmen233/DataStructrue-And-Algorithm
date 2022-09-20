package com.ccb;

import com.ccb.def.Basic;
import com.ccb.def.Calculate;
import com.ccb.def.MostValue;
import com.ccb.def.PowerOfNum;

public class TestBitWise {
    public static void main(String[] args) {
        System.out.println(Basic.removeLowestOneBit(6));
        System.out.println(Basic.getLowestOneBit(12));
        System.out.println(Basic.getSign(1213322));
        System.out.println(PowerOfNum.two(8));
        System.out.println(PowerOfNum.four(64));
        System.out.println(MostValue.ofMax(125, 54));
        System.out.println(Calculate.add(1,35));
        System.out.println(Calculate.sub(34, 39));
        System.out.println(Calculate.mul(12, 10));
        System.out.println(Calculate.mul0(12, 10));
        System.out.println(Calculate.div(15, 4));
        System.out.println(Calculate.div0(-15, 4));
    }
}
