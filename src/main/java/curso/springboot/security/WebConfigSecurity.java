package curso.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    @Override // Configura as solicitações de acesso por Http
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() // Desativa as configurações padrão de memoria do spring
                .authorizeRequests() // Permitir restringir acessos
                .antMatchers(HttpMethod.GET, "/").permitAll()// Qualquer usuario acessa pagina inicial
                .antMatchers(HttpMethod.GET, "/cadastropessoa").hasAnyRole("ADMIN", "USUARIO") 
                .anyRequest().authenticated()
                .and().formLogin().permitAll() // Permite qualquer usuario
                .and().logout() // Mapeia URL de Logout e invalida o usuario autenticado
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }

    @Override // Cria autenticação do usuário com o banco de dados ou em memória
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(implementacaoUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());

        // auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        //         .withUser("alex")
        //         .password("$2a$10$1RLXXn8hW9bCkCyjm2u18uo4H2nElfxEy9z63J9scvDtWIFG2g4Eu")
        //         .roles("ADMIN"); autenticação em memoria
    }

    @Override // Ignora URL especificas
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/materialize/**");
    }

}
