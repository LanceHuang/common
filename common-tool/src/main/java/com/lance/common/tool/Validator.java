package com.lance.common.tool;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 校验器
 * <p>
 * Assert适合单元测试，Optional适合简单的参数校验。为了在业务代码中更可读，改用Validator。
 * 这个类不同于Hibernate validator，Hibernate validator将所有校验条件都放在成员变量。
 * 对于同一个类，在不同的环境下有不同的条件，Hibernate validator显然无法满足。
 *
 * @author Lance
 * @see java.util.Optional
 * @since 2022/5/5
 */
public class Validator<T> {

    private static final Validator<?> EMPTY = new Validator<>();

    private final T value;

    private Validator() {
        this.value = null;
    }

    public Validator(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> Validator<T> of(T value) {
        return new Validator<>(value);
    }

    public static <T> Validator<T> ofNullable(T value) {
        return value == null ? empty() : new Validator<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Validator<T> empty() {
        return (Validator<T>) EMPTY;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    public Validator<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    public <U> Validator<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Validator.ofNullable(mapper.apply(value));
        }
    }

    public <U> Validator<U> flatMap(Function<? super T, Validator<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public Validator<T> equalsInt(Function<T, Integer> function, int cmp) {
        if (!Objects.equals(function.apply(this.value), cmp)) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Validator<T> gt(Function<T, Integer> function, int cmp) {
        if (function.apply(this.value) <= cmp) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Validator<T> ge(Function<T, Integer> function, int cmp) {
        if (function.apply(this.value) < cmp) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Validator<T> lt(Function<T, Integer> function, int cmp) {
        if (function.apply(this.value) >= cmp) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Validator<T> le(Function<T, Integer> function, int cmp) {
        if (function.apply(this.value) > cmp) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public Validator<T> nonNull(Function<T, Object> function) {
        if (function.apply(this.value) == null) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    /**
     * 若上面提供的接口都不满足，可自定义条件
     */
    public <V> Validator<T> validate(Function<T, V> function, Predicate<V> predicate) {
        if (!predicate.test(function.apply(this.value))) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public static void between(int num, int a, int b) {
        if (a > num || num > b) {
            throw new IllegalArgumentException();
        }
    }


    public static void mustNull(Object obj) {
        if (obj != null) {
            throw new IllegalArgumentException();
        }
    }

    public static void nonNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void mustTrue(boolean value) {
        if (!value) {
            throw new IllegalArgumentException();
        }
    }

    public static void mustFalse(boolean value) {
        if (value) {
            throw new IllegalArgumentException();
        }
    }

    public static void gt(int num1, int num2) {
        if (num1 <= num2) {
            throw new IllegalArgumentException();
        }
    }

    public static void ge(int num1, int num2) {
        if (num1 < num2) {
            throw new IllegalArgumentException();
        }
    }

    public static void lt(int num1, int num2) {
        if (num1 > num2) {
            throw new IllegalArgumentException();
        }
    }

    public static void le(int num1, int num2) {
        if (num1 < num2) {
            throw new IllegalArgumentException();
        }
    }

    public static void equals(int num1, int num2) {
        if (!Objects.equals(num1, num2)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEquals(int num1, int num2) {
        if (Objects.equals(num1, num2)) {
            throw new IllegalArgumentException();
        }
    }

    public static void equals(Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEquals(Object obj1, Object obj2) {
        if (Objects.equals(obj1, obj2)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(String str) {
        if (null == str || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
