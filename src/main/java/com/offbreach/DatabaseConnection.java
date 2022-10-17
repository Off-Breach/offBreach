package com.offbreach;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Slf4j
public class DatabaseConnection {
    
    Connection connection = new Connection();
    JdbcTemplate template = connection.getDataSource();
    HardwareData hwData = new HardwareData();
    private String emailFuncionario;
    private String senhaFuncionario;

    public void setConnection(String email, String senha) {
        this.emailFuncionario = email;
        this.senhaFuncionario = senha;
    }

    public User getUsuario() {
        List<User> resultUser = new ArrayList<>();
        String selectUser = String.format("SELECT * FROM funcionario "
                + "WHERE email = '%s' AND senha = '%s'",
                this.emailFuncionario, this.senhaFuncionario);
        try {
            resultUser = template.query(selectUser, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Nenhum usuário cadastrado");
        }
        return resultUser.get(0);

    }

    public String getEmail() {
        String result = "";
        String select = String.format("SELECT emailFuncio FROM gerente "
                + "WHERE emailGerente = '%s' AND senhaGerente = '%s'",
                this.emailFuncionario, this.senhaFuncionario);

        try {
            result = template.queryForObject(select, String.class);
        } catch (EmptyResultDataAccessException exception) {
            select = String.format("SELECT emailFuncionario FROM funcionario "
                    + "WHERE emailFuncionario = '%s' AND senhaFuncionario = '%s'",
                    this.emailFuncionario, this.senhaFuncionario);
            try {
                result = template.queryForObject(select, String.class);
            } catch (EmptyResultDataAccessException e) {
                log.error("Email não cadastrado");
            }
        }
        return result;
    }

    public String getSenha() {
        String result = null;
        String select = String.format("SELECT senhaGerente FROM gerente "
                + "WHERE emailGerente = '%s' AND senhaGerente = '%s'",
                this.emailFuncionario, this.senhaFuncionario);
        try {
            result = template.queryForObject(select, String.class);
        } catch (EmptyResultDataAccessException exception) {
            select = String.format("SELECT senhaFuncionario FROM funcionario "
                    + "WHERE emailFuncionario = '%s' AND senhaFuncionario = '%s'",
                    this.emailFuncionario, this.senhaFuncionario);
            try {
                result = template.queryForObject(select, String.class);
            } catch (EmptyResultDataAccessException e) {
                log.error("Senha incorreta!");

            }
        }
        return result;
    }

    public String getNome() {
        String result = null;
        String select = String.format("SELECT nomeGerente FROM gerente "
                + "WHERE emailGerente = '%s'",
                this.emailFuncionario);
        try {
            result = template.queryForObject(select, String.class);
        } catch (EmptyResultDataAccessException exception) {
            select = String.format("SELECT nomeFuncionario FROM funcionario "
                    + "WHERE emailFuncionario = '%s' AND senhaFuncionario = '%s'",
                    this.emailFuncionario, this.emailFuncionario);
            try {
                result = template.queryForObject(select, String.class);
            } catch (EmptyResultDataAccessException e) {
                log.error("Nome não encontrado!");
            }
        }
        return result;
    }

    public String getGerente() {
        String result = null;
        String select = String.format("SELECT idGerente FROM gerente "
                + "WHERE emailGerente = '%s'",
                this.emailFuncionario);
        try {
            result = template.queryForObject(select, String.class);
        } catch (EmptyResultDataAccessException exception) {
            if (this.getFuncionario() != null) {
                log.info("\nLogando como funcionário");
            } else {
                log.error("Gerente não encontrado!");
            }
        }
        return result;
    }

    public String getFuncionario() {
        String result = null;
        String select = String.format("SELECT idFuncionario FROM funcionario "
                + "WHERE emailFuncionario = '%s'", this.emailFuncionario);
        try {
            result = template.queryForObject(select, String.class);
        } catch (EmptyResultDataAccessException exception) {
            if (this.getGerente() != null) {
                log.info("\nLogando como gerente");
            } else {
                log.error("Funcionario não encontrado");
            }
        }
        return result;
    }

    public Boolean verifyHostname() {
        String hostname = hwData.getHostname();
        Boolean result;
        String select = String.format("SELECT hostname FROM maquina "
                + "WHERE hostname = '%s'",
                hostname);
        try {
            template.queryForObject(select, String.class);
            result = false;
        } catch (EmptyResultDataAccessException exception) {
            result = true;
        }
        return result;
    }

    public String getMachineId() {
        String hostname = hwData.getHostname();
        String result = null;
        String select = String.format("SELECT idMaquina FROM maquina "
                + "WHERE hostname = '%s'",
                hostname);
        try {
            result = template.queryForObject(select, String.class);
            log.info("Máquina encontrada com sucesso!");
        } catch (EmptyResultDataAccessException exception) {
            log.error("Maquina não encontrada");
        }
        return result;
    }

    public void saveHardwareData() {
        String fkGerente = this.getGerente();
        hwData.setHostname();
        String hostname = hwData.getHostname();
        String memoriaTotal = hwData.getMemoryData().getTotal().toString();
        String sistema = hwData.getSistema().getSistemaOperacional();
        String insert = String.format("INSERT INTO maquina(hostname, memoriaTotal, sistemaOperacional,"
                + "fkGerente)"
                + "VALUES ('%s', %s, '%s', %s)",
                hostname, memoriaTotal, sistema, fkGerente);
        if (this.verifyHostname() == true) {
            try {
                template.update(insert);
                log.info("\nMáquina inserida com sucesso\n");
            } catch (DataAccessException error) {
                log.error("Erro ao inserir máquina no banco");
            }
        } else {
            log.warn("\nMáquina já cadastrada\n");
        }
    }

    public void saveCpuAndMemoryDataInLoop(User funcionario) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String temperatura = hwData.getTemperatura();
        Double usoCpu = hwData.getProcessador().getUso();
        String usoRam = hwData.getMemoryData().getEmUso().toString();
        String hora = formatter.format(date);
        String fkMaquina = this.getMachineId();
        String fkFuncionario = this.getFuncionario();

        String insertCpu = String.format("INSERT INTO cpu(temperatura, uso, hora,"
                + "fkMaquina, fkFuncionario)"
                + "VALUES ('%s', %s, '%s', %s, %s)",
                temperatura, usoCpu, hora, fkMaquina, fkFuncionario);

        String insertMemory = String.format("INSERT INTO memoriaRam(uso, horario, "
                + "fkMaquina, fkFuncionario)"
                + "VALUES (%s, '%s', %s, %s)",
                usoRam, hora, fkMaquina, fkFuncionario);

        try {
            template.update(insertCpu);
            template.update(insertMemory);
            log.info("\nDados inseridos com sucesso\n");
        } catch (DataAccessException error) {
            log.error("\nErro ao inserir dados no banco\n");
        }
    }
}
