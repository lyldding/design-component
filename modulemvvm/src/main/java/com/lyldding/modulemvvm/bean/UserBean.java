package com.lyldding.modulemvvm.bean;

/**
 * @author https://github.com/lyldding
 * @date 2019/10/15
 */
public class UserBean {
    private String name;
    private String age;

    private UserBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static final class UserBeanBuilder {
        private String name;
        private String age;

        private UserBeanBuilder() {
        }

        public static UserBeanBuilder newBuilder() {
            return new UserBeanBuilder();
        }

        public UserBeanBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBeanBuilder setAge(String age) {
            this.age = age;
            return this;
        }

        public UserBean build() {
            UserBean userBean = new UserBean();
            userBean.age = this.age;
            userBean.name = this.name;
            return userBean;
        }
    }
}
