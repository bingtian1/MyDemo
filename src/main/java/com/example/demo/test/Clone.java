package com.example.demo.test;

public class Clone implements Cloneable{
    public Clone(){
        System.out.println("Clone 创建成功！");
    }
    public Clone clone() throws CloneNotSupportedException {
        System.out.println("原型模式复制成功！");
        return (Clone)super.clone();
    }
}

class CloneTest{
    public static void main(String[] args) throws CloneNotSupportedException {
        Clone clone1 = new Clone();
        Clone clone2 = clone1.clone();
        System.out.println("clone1 == clone2 ? " + (clone1 == clone2));
    }
}
