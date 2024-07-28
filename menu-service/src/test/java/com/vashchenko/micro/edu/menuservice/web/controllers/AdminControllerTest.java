package com.vashchenko.micro.edu.menuservice.web.controllers;

import com.vashchenko.micro.edu.menuservice.model.entity.Dish;
import com.vashchenko.micro.edu.menuservice.model.mapping.DishMapper;
import com.vashchenko.micro.edu.menuservice.repository.MyBatisDishRepository;
import com.vashchenko.micro.edu.menuservice.web.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DishMapper dishMapper;

    @MockBean
    private MyBatisDishRepository myBatisDishRepository;

    @MockBean
    private JwtService jwtService;
    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"User"})
    void testCreateDishUnauthorized() throws Exception {
        doAnswer(invocation -> {
            Dish dishInMethod = invocation.getArgument(0);
            dishInMethod.setId(1L);
            return null;
        }).when(myBatisDishRepository).save(any(Dish.class));
        doAnswer(invocation -> {
            return null;
        }).when(jwtService).authenticate(any(String.class));
        mockMvc.perform(post("/dishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Pizza\",\"description\":\"Delicious pizza\",\"price\":9.99,\"weight\":500}")
                )
                .andExpect(status().isForbidden());
        verify(myBatisDishRepository, times(0)).save(any(Dish.class));
    }

    //TODO solve problem of forbidden request
//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    void testCreateDishAuthorized() throws Exception {
//        Dish dish = Dish.builder()
//                .description("descr")
//                .price(9.99)
//                .weight(500)
//                .name("Kola")
//                .build();
//
//        doAnswer(invocation -> {
//            Dish dishInMethod = invocation.getArgument(0);
//            dishInMethod.setId(1L);
//            return null;
//        }).when(dishRepository).save(any(Dish.class));
//        doAnswer(invocation -> {
//            return null;
//        }).when(jwtService).authenticate(any(String.class));
//        mockMvc.perform(post("/dishes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"Pizza\",\"description\":\"Delicious pizza\",\"price\":9.99,\"weight\":500}")
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.createdId").value(1));
//        verify(dishRepository, times(1)).save(any(Dish.class));
//    }
}