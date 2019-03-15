package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.dominio.Aluno;

public interface AlunoRepositorio extends JpaRepository<Aluno, Integer> {

}
