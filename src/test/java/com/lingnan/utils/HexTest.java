package com.lingnan.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2018/5/12.
 */
public class HexTest {

    @Test
    public void test(){

        String text = "这篇文章，将有实质的进展。先把大体的需求整理了一份用例图，自认为粒度做的已经很细了，再细就没法搞了。我还是坚信一个原则：自己业余搞的东西千万不要";
        System.out.println(text.length());
        text = Hex.encode(text.getBytes());
        System.out.println(text.length());

    }
}
