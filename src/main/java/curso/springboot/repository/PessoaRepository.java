package curso.springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Pessoa;
import curso.springboot.model.Telefone;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    @Query("select p from Pessoa p where p.nome like %?1% ")
    List<Pessoa> findPessoaByName(String nome);

    void save(Telefone telefone);

    @Query("select p from Pessoa p where p.sexopessoa = ?1 ")
	List<Pessoa> findPessoaBySexo(String sexo);

    @Query("select p from Pessoa p where p.nome like %?1% and p.sexopessoa = ?2")
    List<Pessoa> findPessoaByNameSexo(String nome, String sexopessoa);
}
