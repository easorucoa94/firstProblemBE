package com.truextend.firstProblem;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truextend.firstProblem.entities.ClassEntity;
import com.truextend.firstProblem.services.ClassService;
import com.truextend.firstProblem.services.impl.ClassServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ClassTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ClassService classService = new ClassServiceImpl();

	@Test
	public void addClassTest() throws Exception {
		ClassEntity classEntity = new ClassEntity(null, "MAT-101", "Basic maths", "Math classes for beginners");
		ObjectMapper objectMapper = new ObjectMapper();
		String classObjectAsJson = objectMapper.writeValueAsString(classEntity);
		mvc.perform(post("/class").content(classObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.sclassCode", is("MAT-101")))
				.andExpect(jsonPath("$.sclassTitle", is("Basic maths")));
	}

	@Test
	public void deleteClassTest() throws Exception {
		mvc.perform(delete("/class/50")).andExpect(status().isBadRequest());
	}

	@Test
	public void updateClassTest() throws Exception {
		ClassEntity classEntity = new ClassEntity(1L, "MEC-102", "Blueprints planning", "Learn how to draw blueprints");
		ObjectMapper objectMapper = new ObjectMapper();
		String classObjectAsJson = objectMapper.writeValueAsString(classEntity);
		mvc.perform(put("/class/1").content(classObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.sclassCode", is("MEC-102")))
				.andExpect(jsonPath("$.sclassTitle", is("Blueprints planning")));
	}

	@Test
	public void findAllClassesTest() throws Exception {
		mvc.perform(get("/class")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.notNullValue()))
				.andExpect(jsonPath("$[0].sclassCode", is("MEC-102")));
	}

	@Test
	public void filterAllClassesByCodeTest() throws Exception {
		ClassEntity firstClassEntity = new ClassEntity(1L, "MEC-102", "Blueprints planning",
				"Learn how to draw blueprints");
		classService.save(firstClassEntity);
		ClassEntity secondClassEntity = new ClassEntity(2L, "MAT-101", "Basic maths", "Introduction class to maths");
		classService.save(secondClassEntity);
		ClassEntity thirdClassEntity = new ClassEntity(3L, "MEC-101", "Introduction to drawing",
				"First drawing module");
		classService.save(thirdClassEntity);

		HashMap<String, Object> searchFilter = new HashMap<String, Object>();
		searchFilter.put("sclassCode", "MEC");
		searchFilter.put("sclassTitle", "");
		searchFilter.put("sclassDescription", "");
		searchFilter.put("classFilteredStudents", new ArrayList<String>());
		ObjectMapper objectMapper = new ObjectMapper();
		String searchFilterObjectAsJson = objectMapper.writeValueAsString(searchFilter);
		mvc.perform(post("/class/search").content(searchFilterObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.notNullValue()))
				.andExpect(jsonPath("$[0].sclassCode", is("MEC-102")));
	}
}
