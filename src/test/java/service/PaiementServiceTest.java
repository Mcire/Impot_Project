package service;

import com.groupeisi.ges_impot.service.PaiementService;
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
class PaiementServiceTest {

    @Autowired
    private PaiementService paiementService;

    @BeforeEach
    void setUp() {
        log.info("Demarrage des tests");
    }

    @AfterEach
    void tearDown() {
        log.info("Fin des tests");
    }

    @Test
    void getPaiements() {
    }

    @Test
    void createPaiement() {
    }

    @Test
    void getPaiement() {
    }

    @Test
    void updatePaiement() {
    }

    @Test
    void deletePaiement() {
    }
}