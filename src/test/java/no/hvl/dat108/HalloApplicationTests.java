package no.hvl.dat108;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.assertj.core.api.InstanceOfAssertFactories.MATCHER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class AppMainTests {

	private @Autowired PaameldingController controller;
	private @Autowired MockMvc mockMvc;

	private LinkedMultiValueMap<String, String> testRequestParams;

	@BeforeEach
	void setupTests() {
		testRequestParams = new LinkedMultiValueMap<>();
		testRequestParams.add("mobil", "65738261");
		testRequestParams.add("passord", "Test123!");
		testRequestParams.add("passord_re", "Test123!");
		testRequestParams.add("fornavn", "Test");
		testRequestParams.add("etternavn", "Testesen");
		testRequestParams.add("kjonn", "kvinne");
	}

	@Test
	void testGetPaameldingOK() throws Exception {
		// Standard test for å sjekke
		mockMvc.perform(get("/paamelding"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPostPaameldingMobil() throws Exception {
		// Gyldig mobil
		testRequestParams.set("mobil", "87654321");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1));

		// Tomt mobilnummer
		testRequestParams.set("mobil", "");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// For kort mobilnummer
		testRequestParams.set("mobil", "123");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Bokstaver i mobilnummer
		testRequestParams.set("mobil", "abcdefgh");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Mobilnummer er ikke unikt
		testRequestParams.set("mobil", "87654321");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));
	}

	@Test
	void testPostPaameldingPassord() throws Exception {
		// Gyldig passord
		testRequestParams.set("passord", "Test123!");
		testRequestParams.set("passord_re", "Test123!");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1));

		// Tomt passord
		testRequestParams.set("passord", "");
		testRequestParams.set("passord_re", "");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// For kort passord
		testRequestParams.set("passord", "Test1!");
		testRequestParams.set("passord_re", "Test1!");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Ugyldig passord
		testRequestParams.set("passord", "langtmenikkesikkertpassord");
		testRequestParams.set("passord_re", "langtmenikkesikkertpassord");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Ulike passord
		testRequestParams.set("passord", "Jeg_er_normal1!");
		testRequestParams.set("passord_re", "Jeg_er_unik1!");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));
	}

	@Test
	void testPostPaameldingNavn() throws Exception {
		testRequestParams.set("mobil", "74619462");
		// Gyldig navn
		testRequestParams.set("fornavn", "Navn navn");
		testRequestParams.set("etternavn", "Navnesen");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1));

		// Tomt navn
		testRequestParams.set("fornavn", "");
		testRequestParams.set("etternavn", "");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// For kort navn
		testRequestParams.set("fornavn", "Å");
		testRequestParams.set("etternavn", "Å");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Ugyldig navn
		testRequestParams.set("fornavn", "123_../'¨|");
		testRequestParams.set("etternavn", "123_../'¨|");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));
	}

	@Test
	void testPostPaameldingKjonn() throws Exception {
		testRequestParams.set("mobil", "17561947");
		// Gyldig kjønn
		testRequestParams.set("kjonn", "kvinne");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1));

		// Gyldig kjønn
		testRequestParams.set("mobil", "68274612");
		testRequestParams.set("kjonn", "mann");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeCount(1));

		// Tomt kjønn
		testRequestParams.set("kjonn", "");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));

		// Ugyldig kjønn
		testRequestParams.set("kjonn", "");
		mockMvc.perform(post("/paamelding")
				.params(testRequestParams))
				.andExpect(MockMvcResultMatchers.flash().attributeExists("errors"));
	}

}
