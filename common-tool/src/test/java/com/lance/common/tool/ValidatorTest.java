package com.lance.common.tool;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2022/5/5
 */
class ValidatorTest {

    @Test
    void test() {
        Player player = new Player();
        player.setUsername("Lance");
        player.setPassword("123456");
        player.setAge(28);
        player.setHeight(170);
        player.setGrade(95);

        Validator.of(player)
                .nonNull(Player::getUsername)
                .nonNull(Player::getPassword)
                .gt(Player::getAge, 21)
                .equalsInt(Player::getHeight, 170)
                .validate(Player::getGrade, g -> (g >= 60));
    }

    @Data
    public static class Player {
        private String username;
        private String password;
        private int age;
        private int height;
        private int grade;
    }

}