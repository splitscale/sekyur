package com.splitscale.shield;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.validator.UserRequestValidator;

public class UserRequestValidatorTest {
  UserRequest userRequest;

  @Before
  public void setUp() {
    userRequest = new UserRequest("username", "Fordastore123");
  }

  @BeforeEach
  public void reset() {
    userRequest.setUsername("username");
    userRequest.setPassword("Fordastore123");
  }

  @Test
  @DisplayName("should return true if password is clean")
  public void shouldAcceptNormalPassword() {
    assertTrue(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password is under a specified length")
  public void shouldBeAtMost8Characters() {
    userRequest.setPassword("Forda12");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password does not contain lowercase letter")
  public void testIfPasswordDoesNotContainLowerCase() {
    userRequest.setPassword("FORDASTORE123");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password does not contain uppercase letter")
  public void testIfPasswordDoesNotContainUppercase() {
    userRequest.setPassword("fordastore123");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password does not contain a number")
  public void testIfPasswordDoesNotContainANumber() {
    userRequest.setPassword("Fordastore");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password is html code")
  public void testIfPasswordIsHtml() {
    userRequest.setPassword("<b>Jerome</b>");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if password is sql injection")
  public void testIfPasswordIsSqlInjection() {
    userRequest.setPassword("'; DROP TABLE users --");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return true if username is clean")
  public void shouldAcceptNormalUsername() {
    assertTrue(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if username is empty")
  public void testIfUsernameIsEmpty() {
    userRequest.setUsername(" ");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if username has space")
  public void testIfUsernameHasSpace() {
    userRequest.setUsername("naruto uzumaki");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if username is html code")
  public void testIfUsernameIsHtml() {
    userRequest.setUsername("<b>Jerome</b>");

    assertFalse(UserRequestValidator.validate(userRequest));
  }

  @Test
  @DisplayName("should return false if username is sql injection")
  public void testIfUsernameIsSqlInjection() {
    userRequest.setUsername("'; DROP TABLE users --");

    assertFalse(UserRequestValidator.validate(userRequest));
  }
}