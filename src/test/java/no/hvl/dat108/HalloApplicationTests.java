package no.hvl.dat108;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class AppMainTests {

	@Autowired
	private PaameldingController controller;
	@Autowired
	private MockMvc mockMvc;

	private LinkedMultiValueMap<String, String> testRequestParams;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@BeforeEach
	void setupTests() {
		testRequestParams = new LinkedMultiValueMap<>();
		testRequestParams.add("mobil", "12345678");
		testRequestParams.add("passord", "test123");
		testRequestParams.add("passord_re", "test123");
		testRequestParams.add("fornavn", "Test");
		testRequestParams.add("etternavn", "Testesen");
		testRequestParams.add("kjonn", "kvinne");
	}

	@Test
	void testGetPaameldingOK() throws Exception {
		mockMvc.perform(get("/paamelding")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPostPaameldingTomt() throws Exception {
		mockMvc.perform(post("/paamelding")).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	void testPostPaameldingMobil() throws Exception {
		// Gyldig telefonnummer
		testRequestParams.set("mobil", "12345678");
		mockMvc.perform(post("/paamelding").params(testRequestParams)).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
		// Tomt mobilnummer
		testRequestParams.set("mobil", "");
		mockMvc.perform(post("/paamelding").params(testRequestParams)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
		// For kort mobilnummer
		testRequestParams.set("mobil", "123");
		mockMvc.perform(post("/paamelding").params(testRequestParams)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
		// Bokstaver i mobilnummer
		testRequestParams.set("mobil", "abcdefgh");
		mockMvc.perform(post("/paamelding").params(testRequestParams)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
