package com.qing.func;

/**
 * @className: Person
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/6/14
 **/
public class Person {

    private  int id;

    private  String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void create(Integer id,String name,PersonCallback personCallback){
        Person person = new Person(id,name);
        personCallback.callback(person);
    }

    public static void main(String[] args) {
        Person.create(1,"lucy",new PersonCallback(){

            @Override
            public void callback(Person person) {
                System.out.println("去注册");
            }
        });
        Person.create(2, "tom", new PersonCallback() {
            @Override
            public void callback(Person person) {
                System.out.println("去登录");
            }
        });
    }
}
