package com.springboot.advanced_jpa.data.repository;


import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 제네릭 안에 들어가있는 것은 대상 엔티티와 대상 엔티티의 @Id 필드 타입이다.

    // find...By
    Optional<Product> findByNumber(Long number);

    List<Product> findAllByName(String name);

    Product queryByNumber(Long number);


    // exist...By
    boolean existsByNumber(Long number);

    // count...By
    long countByName(String name);


    // delete...By, remove...By
    void deleteByNumber(Long number);

    long removeByName(String name);

    // First<number>, Top<number>
    List<Product> findFirst5ByName(String name);

    List<Product> findTop10ByName(String name);

    /* 쿼리 메서드의 조건자 키워드 */

    // Is -> 값의 일치를 조건으로 사용하나 생략되는 경우가 많다. Equals와 동일한 기능이다.
    // findByNumber와 동일한 역할
    Product findByNumberIs(Long number);

    Product findByNumberEquals(Long number);

    // IsNot -> 값의 불일치를 조건으로 사용한다. Is는 생략 가능하다.
    Product findByNumberIsNot(Long number);

    Product findByNumberNot(Long number);

    // (Is)Null, (Is)NotNull -> 값이 null인지를 검사하는 조건자이다.
    List<Product> findByUpdatedAtNull();

    List<Product> findByUpdatedAtIsNull();

    List<Product> findByUpdatedAtNotNull();

    List<Product> findByUpdatedAtIsNotNull();

    // And, Or -> 여러 조건을 묶을 때 사용한다.
    Product findByNumberAndName(Long number, String name);

    Product findByNumberOrName(Long number, String name);

    // (Is)GreaterThan, (Is)LessThan, (Is)Between -> 숫자나 날짜 컬럼을 비교 연산에 사용할 수 있는 조건자입니다.
    // GreaterThan, LessThan은 대상에 초과/미만 개념이고, 경곗값을 포함하려면 Equal 키워드를 추가하면 된다.

    List<Product> findByPriceIsGreaterThan(Integer price);

    List<Product> findByPriceIsGreaterThanEqual(Integer price);

    List<Product> findByPriceIsLessThan(Integer price);

    List<Product> findByPriceIsLessThanEqual(Integer price);

    List<Product> findByPriceBetween(Integer rowPrice, Integer highPrice);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith -> 컬럽값에서 일부 일치 여부를 확인하는 조건자 키워드입니다.
    // Sql로 따지면 ' % ' 키워드와 동일한 역할입니다.
    List<Product> findByNameLike(String name);

    List<Product> findByNameIsLike(String name);

    List<Product> findByNameContains(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameIsContaining(String name);

    List<Product> findByNameStartsWith(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameIsEndingWith(String name);

    /* 정렬 처리하기 */
    // Asc : 오름차순, Desc : 내림차순
    List<Product> findByNameOrderByNumberAsc(String name);

    List<Product> findByNameOrderByNumberDesc(String name);

    // 여러 정렬 기준 사용하기, And를 붙이지 않음
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    //    // 매개변수를 활용한 정렬 방법
    List<Product> findByName(String name, Sort sort);

    //    /* 페이징 처리하기 */
    Page<Product> findByName(String name, Pageable pageable);

    /* @Query 어노테이션을 활용한 메소드 작성 */
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    List<Product> findByName(String name);


    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByNameParam(@Param("name") String name);


    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);

}
