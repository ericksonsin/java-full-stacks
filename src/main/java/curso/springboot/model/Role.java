package curso.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L; // teste git

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    private String nomeRole;

      @Override
    public String getAuthority() { // ROLE ADMIN, ROLE_GERENTE, ROLE _SECRETARIO ...
        return this.nomeRole;
    }


    public String getNomeRole() { 
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }

  

}
