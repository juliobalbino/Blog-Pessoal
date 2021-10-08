package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    public Usuario usuario1;
    public Usuario usuario2;
    public Usuario usuario3;
    public Usuario usuario4;
    public Usuario usuarioErro = new Usuario();

    @Autowired
    private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    @BeforeEach
    public void start() {

        usuario1 = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278");
        usuario2 = new Usuario(0L, "João da Silva", "joao@email.com.br", "134s65278");
        usuario3 = new Usuario(0L, "João da Silva", "joao@email.com.br", "134s@65278");
        usuario4 = new Usuario(0L, "João da Silva", "joao@email.com.br", "138");

    }

    @Test
    @DisplayName("✔ Valida Atributos Não Nulos")
    void testValidaAtributos() {

        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario1);

        System.out.println(violacao.toString());

        assertTrue(violacao.isEmpty());
    }

    @Test
    @DisplayName("✔ Valida Senha com regex")
    void testValidaSenha() {

        String patternnumero = "(?=.*[0-9]).{8,}";
        String patternNumeroLetra = "(?=.*[0-9])(?=.*[a-z]).{8,}";
        String patternNumeroLetraCaracter = "(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}";
        String patternNumeroLetraCaracterFalso = "(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}";

        assertTrue(usuario1.getSenha().matches(patternnumero));
        assertTrue(usuario2.getSenha().matches(patternNumeroLetra));
        assertTrue(usuario3.getSenha().matches(patternNumeroLetraCaracter));
        assertFalse(usuario4.getSenha().matches(patternNumeroLetraCaracterFalso));

    }

    @Test
    @DisplayName("✖ Não Valida Atributos Nulos")
    void  testNaoValidaAtributos() {

        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
        System.out.println(violacao.toString());

        assertFalse(violacao.isEmpty());
    }

}