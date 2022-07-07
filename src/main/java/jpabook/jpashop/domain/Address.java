package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //값 타입은 변경 불가능하게 설계.기본 생성자 만들기, setter 지우기
    protected Address() {
    }

    //생성자에서만 값을 모두 초기화, 변경 불가능한 클래스.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
