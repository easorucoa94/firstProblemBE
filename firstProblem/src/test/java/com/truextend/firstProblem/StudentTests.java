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
import com.truextend.firstProblem.entities.StudentEntity;
import com.truextend.firstProblem.services.StudentService;
import com.truextend.firstProblem.services.impl.StudentServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class StudentTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private StudentService studentService = new StudentServiceImpl();

	@Test
	public void addStudentTest() throws Exception {
		StudentEntity studentEntity = new StudentEntity(null, "Eduardo", "Soruco");
		ObjectMapper objectMapper = new ObjectMapper();
		String studentObjectAsJson = objectMapper.writeValueAsString(studentEntity);
		mvc.perform(post("/student").content(studentObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.sstudentFirstName", is("Eduardo")))
				.andExpect(jsonPath("$.sstudentLastName", is("Soruco")));
	}

	@Test
	public void deleteStudentTest() throws Exception {
		mvc.perform(delete("/student/10")).andExpect(status().isBadRequest());
	}

	@Test
	public void updateStudentTest() throws Exception {
		StudentEntity studentEntity = new StudentEntity(1L, "Alan", "Saavedra");
		ObjectMapper objectMapper = new ObjectMapper();
		String studentObjectAsJson = objectMapper.writeValueAsString(studentEntity);
		mvc.perform(put("/student/1").content(studentObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.sstudentFirstName", is("Alan")))
				.andExpect(jsonPath("$.sstudentLastName", is("Saavedra")));
	}

	@Test
	public void findAllStudentsTest() throws Exception {
		mvc.perform(get("/student")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.notNullValue()))
				.andExpect(jsonPath("$[0].sstudentFirstName", is("Carlos")))
				.andExpect(jsonPath("$[0].sstudentLastName", is("Castro")));
	}

	@Test
	public void filterAllStudentsByNameTest() throws Exception {
		StudentEntity firstStudentEntity = new StudentEntity(1L, "Carlos", "Castro");
		studentService.save(firstStudentEntity);
		StudentEntity secondStudentEntity = new StudentEntity(2L, "Eduardo", "Soruco");
		studentService.save(secondStudentEntity);
		StudentEntity thirdStudentEntity = new StudentEntity(3L, "Carlos", "Sandoval");
		studentService.save(thirdStudentEntity);

		HashMap<String, Object> searchFilter = new HashMap<String, Object>();
		searchFilter.put("sstudentFirstName", "Carlos");
		searchFilter.put("sstudentLastName", "");
		searchFilter.put("studentFilteredClasses", new ArrayList<String>());
		ObjectMapper objectMapper = new ObjectMapper();
		String searchFilterObjectAsJson = objectMapper.writeValueAsString(searchFilter);
		mvc.perform(post("/student/search").content(searchFilterObjectAsJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].sstudentLastName", is("Castro")))
				.andExpect(jsonPath("$[1].sstudentLastName", is("Sandoval")));
	}
}
