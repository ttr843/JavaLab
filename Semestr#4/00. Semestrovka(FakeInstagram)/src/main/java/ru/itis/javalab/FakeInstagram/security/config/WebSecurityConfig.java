package ru.itis.javalab.FakeInstagram.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@Profile("mvc")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(@Qualifier(value = "MyUserDetails") UserDetailsServiceImpl userDetailsService,
                             @Qualifier(value = "passwordEncoder")
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/confirm").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/addPost").authenticated()
                .antMatchers("/editProfile").authenticated()
                .antMatchers("/post/**").authenticated()
                .antMatchers("/files/**").authenticated()
                .antMatchers("/chat").authenticated()
                .antMatchers("/messages").permitAll()
                .antMatchers("/search").authenticated()
                .antMatchers("/subscribe").authenticated()
                .antMatchers("/subscriptions").authenticated()
                .antMatchers("/deleteSub").authenticated()
                .antMatchers("/news").authenticated()
                .antMatchers("/addToFavorites").authenticated()
                .antMatchers("/favorites").authenticated();



        http.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
