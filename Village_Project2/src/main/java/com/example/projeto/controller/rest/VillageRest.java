package com.example.projeto.controller.rest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.transport.BudgetDTO;
import com.example.projeto.model.transport.FilterIdDTO;
import com.example.projeto.model.transport.FilterVillageDTO;
import com.example.projeto.model.transport.VillageDTO;
import com.example.projeto.service.HabitanteService;

@RestController
@RequestMapping("village")
public class VillageRest {

	private final HabitanteService habitanteService;

    public VillageRest(HabitanteService habitanteService) {
        this.habitanteService = habitanteService;
    }
    
    @GetMapping("/{id}")
    public FilterIdDTO getResidentById(@PathVariable Integer id) throws SQLException {
        return habitanteService.getHabitanteById(id);
    }

    @GetMapping("/")
    public List<FilterVillageDTO> listResidents() throws SQLException {
        return habitanteService.listHabitantes();
    }

    @GetMapping("/filter-birth")
    public List<FilterVillageDTO> listMonth(@RequestParam("month") String month) throws SQLException {
        return habitanteService.filteredMonthList(month);
    }

    
    @GetMapping("/age")
    public List<VillageDTO> listAge(@RequestParam("age") String age) throws SQLException, ParseException {
        return habitanteService.filteredAgeList(age);
    }

    
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createNewResident(@RequestBody VillageDTO resident) throws SQLException, ParseException {
        Boolean response = this.habitanteService.create(resident);
        if (response == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteResident(@RequestParam("id") String id) throws SQLException {
        Boolean response = this.habitanteService.deleteUser(id);
        if (response == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/description")
    public BudgetDTO getDescription() throws SQLException {
        return habitanteService.getDescription();
    }


    @GetMapping("/income")
    public BigDecimal getResidentsCost() throws SQLException {
        return habitanteService.getHabitantesIncome();
    }

    @GetMapping("/budget")
    public Double getBudget() {
        return habitanteService.getBudget();
    }
}
