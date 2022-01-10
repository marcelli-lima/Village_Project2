package com.example.projeto.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.projeto.config.JDBCConfig;
import com.example.projeto.database.VillageRepository;
import com.example.projeto.model.UserSpringSecurity;
import com.example.projeto.model.transport.BudgetDTO;
import com.example.projeto.model.transport.FilterIdDTO;
import com.example.projeto.model.transport.FilterVillageDTO;
import com.example.projeto.model.transport.VillageDTO;
import com.example.projeto.util.FilterAge;
import com.example.projeto.util.ValidateCPF;
import com.example.projeto.util.ValidateName;
import com.example.projeto.util.ValidatePassword;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.*;

@Service
public class HabitanteService implements UserDetailsService  {

	private VillageRepository villageRepository;
	private Double villageBudget;
	public HabitanteService(VillageRepository villageRepository, Double villageBudget) {
		super();
		this.villageRepository = villageRepository;
		this.villageBudget = villageBudget;
	}
	
	public List<FilterVillageDTO> listHabitantes() throws SQLException {
		List<FilterVillageDTO> habitantes = this.villageRepository.listHabitantes();
		
		if (habitantes.isEmpty()) {
		System.out.println("Não há habitantes");
		}
		return habitantes;
	}
	public List<VillageDTO> getHabitantes() throws SQLException {
		List<VillageDTO> habitantes = this.villageRepository.getHabitantes();
		if (habitantes.isEmpty()) {
			System.out.println("Não há habitantes");
		}
		return habitantes;
	}
	
    public void validate (VillageDTO habitante) throws SQLException, ParseException {
        if (habitante == null) {
            throw new IllegalArgumentException("Não existe habitantes");
        }
        if (!ValidateCPF.isValidCPF(null)) {
        	 throw new IllegalArgumentException("CPF invalido");
        }
        if (!ValidatePassword.isValidatePassword(null)) {
        	 throw new IllegalArgumentException("Senha inválida precisa ter 8 caracteres sendo pelo menos 1 letra maiúscula, 1 numero e 1 caracter especial.");
        }
        if (!ValidateName.isValidateName(null)) {
        	 throw new IllegalArgumentException("Nome invalido");
        }
        }
         
        

	   public Double getBudget() {
	        return this.villageBudget;
	    }

	    public BigDecimal getHabitantesIncome() throws SQLException {

	        return getHabitantes().stream()
	        		.map(VillageDTO::getIncome).reduce(BigDecimal.ZERO, BigDecimal::add);

	    }

	    public FilterIdDTO getHabitanteById(Integer id) throws SQLException {
	        if (id == null) {
	            throw new IllegalArgumentException("O id está nulo");
	        }
	        
	        Connection connection = new JDBCConfig().getConnection();

	        PreparedStatement pStmt = connection.prepareStatement("select * from habitantes where id=?");
	        pStmt.setInt(1, id);
	        pStmt.execute();

	        ResultSet rs = pStmt.getResultSet();

	        if (rs.next()) {
	           return this.villageRepository.getFilterIdDTO(rs);
	        }

	        pStmt.close();
	        connection.close();

	        throw new NoSuchElementException("Não há");
	    }

	    public BudgetDTO getDescription() throws SQLException {

	    	BudgetDTO report = new BudgetDTO();

	        BigDecimal budget = new BigDecimal(getBudget());
	        report.setBudget(budget);

	        BigDecimal diff = budget.subtract(getHabitantesIncome());
	        report.setDifference(diff);

	        report.setTotal(getHabitantesIncome());

	        Optional<VillageDTO> maxIncome = getHabitantes().stream().max(Comparator.comparing(VillageDTO::getIncome));
	        
	        
	        return report;
	    }


	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        VillageDTO villageDTO = null;
	        try {
	        	villageDTO = getHabitante(username);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if (villageDTO == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new UserSpringSecurity();
	    }
	    
	    public VillageDTO getHabitante(String username) throws SQLException {
	        return villageRepository.getHabitante(username);
	    }
	    
	    public static UserSpringSecurity authenticated() {
	        try {
	            return new UserSpringSecurity(
	                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
	                    null, new ArrayList<>());
	        } catch (Exception e) {
	            return null;
	        }
	    }
	    public void updateUser(String email, String password){
	        villageRepository.updateUser(email, password);
	    }

	    public Boolean deleteUser(String id) throws SQLException {
	        return villageRepository.deleteUser(id);
	    }


	    public List<FilterVillageDTO> filteredMonthList(String month) throws SQLException {
	        if (month == null || month.equals("")) {
	            throw new IllegalArgumentException("O mês está vazio");
	        }

	        return villageRepository.filteredMonthList(month);
	    }

	    public List<VillageDTO> filteredAgeList(String age) throws SQLException, ParseException {
	        if (age == null || age.equals("")) {
	            throw new IllegalArgumentException("A idade está vazia");
	        }

	        return villageRepository.filteredAgeList(age);
	    }

		public Boolean create(VillageDTO resident) {
			return null;
		}

	}  