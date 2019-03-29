package br.com.senac.inicializacao;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Aluno;
import br.com.senac.dominio.Categoria;
import br.com.senac.dominio.Cidade;
import br.com.senac.dominio.Curso;
import br.com.senac.dominio.Endereco;
import br.com.senac.dominio.Estado;
import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.AlunoRepositorio;
import br.com.senac.repositorio.CategoriaRepositorio;
import br.com.senac.repositorio.CidadeRepositorio;
import br.com.senac.repositorio.CursoRepositorio;
import br.com.senac.repositorio.EnderecoRepositorio;
import br.com.senac.repositorio.EstadoRepositorio;
import br.com.senac.repositorio.UsuarioRepositorio;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AlunoRepositorio alunoRepositorio;

	@Autowired
	EstadoRepositorio estadoRepositorio;

	@Autowired
	CidadeRepositorio cidadeRepositorio;

	@Autowired
	EnderecoRepositorio enderecoRepositorio;

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Autowired
	CategoriaRepositorio CategoriaRepositorio;

	@Autowired
	CursoRepositorio CursoRepositorio;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Aluno aluno = new Aluno();
		aluno.setNome("willer");
		aluno.setEmail("willerpaiva10@gmail.com");
		alunoRepositorio.save(aluno);

		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("joão");
		usuario.setSobreNome("Silva");
		usuario.setEmail("joãosilva@gmail.com");
		usuario.setSenha("123456");
		usuarioRepositorio.save(usuario);

		Aluno alunoGravado = alunoRepositorio.findByEmail("willerpaiva10@gmail.com");

		Estado estado1 = new Estado();
		estado1.setNome("Riode Janeiro");

		Estado estado2 = new Estado();
		estado1.setNome("são paulo");

		Cidade cidade1 = new Cidade();
		cidade1.setNome("Angra dos Reis");
		cidade1.setEstado(estado1);

		Cidade cidade2 = new Cidade();
		cidade2.setNome("Cabo Frio");
		cidade2.setEstado(estado1);

		Cidade cidade3 = new Cidade();
		cidade3.setNome("MOgi das Cuzes");
		cidade3.setEstado(estado2);

		estadoRepositorio.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepositorio.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Endereco end1 = new Endereco();
		end1.setRua("Rua dos Andradas");
		end1.setNumero("20");
		end1.setBairro("Centro");
		end1.setComplemento("Bloco B");
		end1.setCep("24472410");
		end1.setCidade(cidade1);
		end1.setAluno(aluno);

		Endereco end2 = new Endereco();
		end1.setRua("Rua dos marrecos");
		end1.setNumero("60");
		end1.setBairro("laje");
		end1.setComplemento("fundos");
		end1.setCep("21582-201");
		end1.setCidade(cidade2);
		end1.setAluno(aluno);

		aluno.getTelefone().addAll(Arrays.asList("232323243", "232323243"));

		alunoRepositorio.save(aluno);
		enderecoRepositorio.saveAll(Arrays.asList(end1, end2));

		Categoria categoria1 = new Categoria(null, "java");
		Categoria categoria2 = new Categoria(null, "mobile");

		CategoriaRepositorio.saveAll(Arrays.asList(categoria1, categoria2));

		Curso curso1 = new Curso(null, "java", "java para iniciante", 200.00);
		Curso curso2 = new Curso(null, "javaII", "java para Intermediario", 400.00);

		curso1.setCategorias(Arrays.asList(categoria1, categoria2));
		curso2.setCategorias(Arrays.asList(categoria1));

		CursoRepositorio.saveAll(Arrays.asList(curso1, curso2));
	}

}
