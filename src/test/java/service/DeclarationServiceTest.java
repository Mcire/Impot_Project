package service;

import com.groupeisi.ges_impot.service.DeclarationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class DeclarationServiceTest {

    @Autowired
    private DeclarationService declarationService;

    @BeforeEach
    void setUp() {
        log.info("Demarrage des tests");
    }

    @AfterEach
    void tearDown() {
        log.info("Fin des tests");
    }

    @Test
    void getDeclarations() {
    }

    @Test
    void getDeclaration() {
    }

    @Test
    void createDeclaration() {
    }

    @Test
    void updateDeclaration() {
    }

    @Test
    void deleteDeclaration() {
    }
}