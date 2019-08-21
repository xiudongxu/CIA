package com.xiudongxu.common.orika;

/**
 * @author dongxu.xiu
 * @since 2019-03-29 下午2:19
 */
public class PersonDTO {
    private String firstName;
    private int age;
    private String sex;

    public PersonDTO(String firstName, int age, String sex) {
        this.firstName = firstName;
        this.age = age;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
